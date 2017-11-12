package org.geilove.service.impl;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.geilove.dao.MessageMapper;
import org.geilove.dao.UserMapper;
import org.geilove.pojo.Message;
import org.geilove.pojo.RedMoney;
import org.geilove.pojo.ShareUser;
import org.geilove.pojo.User;
import org.geilove.response.CommonRsp;
import org.geilove.service.PhoneService;
import org.geilove.service.RegisterByShareService;
import org.geilove.service.SelRedMoneyService;
import org.geilove.util.Md5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * 为葡萄互助提供手机号注册登录等功能
 */

@Service("phoneSevice")
public class PhoneServiceImpl  implements PhoneService{

    //产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";

    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    static final String accessKeyId = "LTAIwP7C1cCRAcqP";
    static final String accessKeySecret = "YwaXGOPPj1qN3PoTryO0xkqbPXJ9Ob";

    @Resource
    private MessageMapper  messageMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private SelRedMoneyService selRedMoneyService; //用到了另一个service

    class BornRedMoney extends Thread{
        public String  shareUserUUID;
        public String  newUserUUID;
        public  BornRedMoney( String  shareUserUUID, String  newUserUUID){
            this.shareUserUUID=shareUserUUID;
            this.newUserUUID=newUserUUID;
        }
        public  void  run(){
            selRedMoneyService.bornRedMony(shareUserUUID,newUserUUID);
        }
    }
    //获取验证码服务
    @Override
    public  CommonRsp  getVerifyCode(String  phone) throws ClientException {
        //初始化acsClient,暂不支持region化
        CommonRsp commonRsp=new CommonRsp();
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(phone);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("葡萄互助");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_106910015");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为

        //生成4位数字随机数验证码
        Random random = new Random();
        String fourRandom = random.nextInt(10000) + "";
        int randLength = fourRandom.length();
        if(randLength<4){
            for(int i=1; i<=4-randLength; i++)
                fourRandom = "0" + fourRandom  ;
        }

        //request.setTemplateParam("{\"code\":\"134256\"}");
        request.setTemplateParam("{\"code\":\""+fourRandom+"\"}");
        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        // request.setOutId("yourOutId");

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        String code=sendSmsResponse.getCode();
        Message message=new Message();
        message.setMsguuid(UUID.randomUUID().toString());
        message.setPhonenumbers(phone);
        message.setTemplateparam(code);
        message.setSenddate(new Date());
        try {
            int insertTag= messageMapper.insertSelective(message);
            if (insertTag!=1){
                commonRsp.setMsg("验证码入库出现错误");
                commonRsp.setRetcode(2001);
                return commonRsp;
            }
        }catch (Exception e){
            //记录日志
            commonRsp.setMsg("验证码入库出现异常");
            commonRsp.setRetcode(2001);
            return commonRsp;
        }
        commonRsp.setMsg(code);
        commonRsp.setRetcode(2000);
        return commonRsp;

    }




    //检验验证码和手机号是否匹配
    @Override
    public Message checkPhoneCode(String phone, String templateparam){
        Map<String,Object> map=new HashMap();
        map.put("phone",phone);
        map.put("templateparam",templateparam);
        map.put("page",0);
        map.put("pageSize",1); //选择出最新的验证码
        List<Message> message=messageMapper.selectPhoneCode(map);
        if (message==null || message.isEmpty())
            return null;
        //选择出最新的验证码，而且这个验证码还不能超过5分钟
        return  message.get(0);
    }

    //手机号注册,适用于葡萄互助
    @Override
    public CommonRsp phoneRegister(String phone,String userPassword,String verifycode,String shareUserUUID){
        CommonRsp  commonRsp=new CommonRsp();
        if (phone==null ||"".equals(phone) ||phone.length()!=11 || userPassword.length() < 6 || userPassword.length() > 18 ){
            commonRsp.setMsg("手机号或密码长度不对");
            commonRsp.setRetcode(2001);
            return commonRsp;
        }
        if (verifycode==null ||"".equals(verifycode) || verifycode.length()!=4 ){
            commonRsp.setMsg("验证码长度不对");
            commonRsp.setRetcode(2001);
            return commonRsp;
        }

        //正则表达式校验手机号码
        try{
            Boolean B=isPhoneLegal(phone);
            if (B==false){
                commonRsp.setMsg("手机格式不对");
                commonRsp.setRetcode(2001);
                return commonRsp;
            }
        }catch (Exception e){
            commonRsp.setMsg("校验手机格式抛出异常");
            commonRsp.setRetcode(2001);
            return commonRsp;
        }
        //校验密码
        String regPass = "^[0-9a-zA-Z]{5,17}$"; //邮箱密码的正则表达式
        Pattern patternPW = Pattern.compile(regPass);
        Matcher matcherPW = patternPW.matcher(userPassword);
        boolean pwb = matcherPW.matches();
        if (pwb == false) {
            commonRsp.setMsg("密码不符合格式");
            commonRsp.setRetcode(2001);
            return commonRsp;
        }
        // 校验验证码格式，是否为4个数字
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(verifycode);
        if( !isNum.matches() ){
            commonRsp.setMsg("验证码格式不对");
            commonRsp.setRetcode(2001);
            return commonRsp;
        }
        //检验手机号和验证码是否一致

        try{
            Message message = checkPhoneCode(phone,verifycode);
            if (message==null){
                commonRsp.setMsg("验证码和手机号不匹配");
                commonRsp.setRetcode(2001);
                return commonRsp;
            }
        }catch (Exception e){
            commonRsp.setMsg("查询验证码抛出异常");
            commonRsp.setRetcode(2001);
            return commonRsp;
        }

        // 查看手机号是否已经注册
        String passmd5 = Md5Util.getMd5(userPassword); //对密码进行加密
        Map<String,Object> map=new HashMap<>();
        map.put("phone",phone);
        map.put("passmd5",passmd5);
        try{ //用加密后的密码和手机号查询数据库，确定是否已注册
            User user=userMapper.getUserByPhonePass(map);
            if (user!=null){
                commonRsp.setMsg("用户已经注册");
                commonRsp.setRetcode(2001);
                return  commonRsp;
            }
        }catch (Exception e){
            commonRsp.setMsg("查询数据库出现异常");
            commonRsp.setRetcode(2001);
            return  commonRsp;
        }
        // 走到这里说明存手机号和验证码匹配，应该进行注册,需要继续完善
        User userRegister = new User();
        userRegister.setUseruuid(UUID.randomUUID().toString()); //用户的UUID
        userRegister.setUsernickname(phone); //葡萄互助，手机号是默认昵称
        userRegister.setUserphone(phone);
        userRegister.setUserpassword(passmd5);
        userRegister.setUserphoto("http://www.geilove.org/path/geilove.png"); //默认头像地址
        userRegister.setPhotoupload((byte) 1);
        userRegister.setNotsay((byte) 1);
        userRegister.setCertificatetype((byte) 1);
        userRegister.setUsertype((byte) 1);
        userRegister.setNotsay((byte) 1);
        try{
            int registerTag= userMapper.insert(userRegister);
            if (registerTag!=1){
                commonRsp.setMsg("注册失败");
                commonRsp.setRetcode(2001);
                return  commonRsp;
            }
        }catch (Exception e){
            commonRsp.setMsg("注册抛出异常");
            commonRsp.setRetcode(2001);
            return  commonRsp;
        }
        //开启线程，如果是通过分享注册的，就加入红包
        if (shareUserUUID!=null || shareUserUUID.length()>32 ){
            try { //防止抛出异常，无法进行下一步
                new BornRedMoney(shareUserUUID,userRegister.getUseruuid()).start();
            }catch (Exception e){

            }
        }
        commonRsp.setMsg("注册成功");
        commonRsp.setRetcode(2000);
        return commonRsp;
    }




    /************************************手机号校验方法*************************************************/
    /**
     * 大陆号码或香港号码均可
     */
    public static boolean isPhoneLegal(String str)throws PatternSyntaxException {
        return isChinaPhoneLegal(str) || isHKPhoneLegal(str);
    }

    /**
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数
     * 此方法中前三位格式有：
     * 13+任意数
     * 15+除4的任意数
     * 18+除1和4的任意数
     * 17+除9的任意数
     * 147
     */
    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 香港手机号码8位数，5|6|8|9开头+7位任意数
     */
    public static boolean isHKPhoneLegal(String str)throws PatternSyntaxException {
        String regExp = "^(5|6|8|9)\\d{7}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }
}











































