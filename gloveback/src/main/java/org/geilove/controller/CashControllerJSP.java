package org.geilove.controller;

import org.geilove.pojo.Cash;
import org.geilove.pojo.HelpInfo;
import org.geilove.pojo.Tweet;
import org.geilove.service.CashService;
import org.geilove.service.MainService;
import org.geilove.service.RegisterLoginService;
import org.geilove.service.impl.HelpInfoServiceImpl;
import org.geilove.util.CreateFileUtil;
import org.geilove.util.ServerIP;
import org.geilove.util.TimeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 这个是发布求助项目时的代码，适用于公众号内发布
 */

@Controller
@RequestMapping("/publishInfo/upload")
public class CashControllerJSP {

    @Resource
    private MainService mainService;
    @Resource
    private RegisterLoginService rlService;
    @Resource
    private HelpInfoServiceImpl helpInfoService;

    @Resource
    private CashService cashService;


    @RequestMapping(value="/addman.do",method = RequestMethod.GET)
    public String addHelpMan(){

        String index="front/addHelpMan";
        return index;
    }

    /*******************************************************************************************************************
     ** 1.检查有没有权限  2.是否发表过2次的求助信息 3.tweet表增加。 4cash表增加。5.HelpInfo表增加信息
     *  先获取各种需要的数据，然后紧接着校验，最后再插入或者更新各种表
     * */
    @RequestMapping(value="wapmultiUpload.do")
    @ResponseBody
    public ModelAndView jspmultiUpload(HttpServletRequest request)throws IllegalStateException, IOException {
       // System.out.println("CashControllerJSp");
        HelpInfo helpInfo; //求助信息表
        Cash cash= new Cash();
        Tweet tweet=new Tweet();
        int  helpInfoTimes=0; //用户第一次发布求助信息，默认是0
        Long helpInfoMainID; //helpInfo表的主键，需要根据主键更新
        //生成Tweet 表、Cash表、HelpInfo表的UUID
        String tweetUUID= UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        String cashUUID=UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        String helpeachotheruuid= UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        //从前端获取需要的数据
        String token=request.getParameter("token");
        String shouZhurenName= request.getParameter("shouZhurenName"); //受助人姓名
        String shouZhureniDentityNo= request.getParameter("shouZhureniDentityNo");//受助人身份证号
        String acceptMoneyName= request.getParameter("acceptMoneyName"); //收款人姓名
        String acceptMoneyPhone= request.getParameter("acceptMoneyPhone");//收款人电话
        String cityName=request.getParameter("cityName");//用户所在的城市
        String userName=request.getParameter("userName"); //用户昵称
        String photoUrl=request.getParameter("photoUrl"); //用户的头像地址
        String useruuid=request.getParameter("useruuid"); //根据uuid获取
        String chengnuoContent=request.getParameter("chengnuoContent");//我的承诺
        String targetMoney=request.getParameter("targetMoney");//目标金额
        String moneyTitle=request.getParameter("moneyTitle");//筹款标题
        String content=request.getParameter("content"); //获取推文的内容，详情描述
        String selfIntroduce=request.getParameter("selfintroduce");
        String dateEndStr=request.getParameter("endDate");//结束日期
        String[] promises =request.getParameterValues("chengnuoType");
        String[] proves =request.getParameterValues("prove");
        Date date=new Date();
        Date dateStart=new Date(); //开始日期

        String ipAndport= ServerIP.getiPPort(); //http://172.16.32.52:8080

        /*@***************下面开始数据校验*******************@*/
        if (token==null ||token==""||shouZhurenName==null ||shouZhurenName==""||acceptMoneyName==null ||acceptMoneyName==""||
                acceptMoneyPhone==null ||acceptMoneyPhone==""|| cityName==null||cityName=="" ||userName==null ||userName=="" ||
                photoUrl==null || photoUrl=="" ||useruuid==null ||photoUrl=="" ||chengnuoContent==null ||chengnuoContent=="" ||
                targetMoney==null ||targetMoney=="" ||moneyTitle==null ||moneyTitle=="" ||content==null ||content =="" ||
                dateEndStr==null || dateEndStr=="" ||promises ==null ||proves==null ){

            Map<String,String> model =new HashMap();
            model.put("result", "您有必要字段为空");
            ModelAndView modelAndView=new ModelAndView("front/addHelpManResult",model);
            return modelAndView;
        }
        if (shouZhurenName.length()<2 ||shouZhurenName.length()>8 || shouZhureniDentityNo.length()!=18 || acceptMoneyName.length()<2 ||
                acceptMoneyName.length()>8 ||acceptMoneyPhone.length()!=11 || cityName.length()<2 || cityName.length()>8 || userName.length()<3 ||
                userName.length()>30 || useruuid.length() <32 ||chengnuoContent.length()<30 ||moneyTitle.length()<6 || content.length()<200 ){
            Map<String,String> model =new HashMap();
            model.put("result", "您有必要字段长度不符合");
            ModelAndView modelAndView=new ModelAndView("front/addHelpManResult",model);
            return modelAndView;
        }

        if (token.length()<=32){
            Map<String,String> model =new HashMap();
            model.put("result", "非法操作");
            ModelAndView modelAndView=new ModelAndView("front/addHelpManResult",model);
            return modelAndView;
        }

        String userPassword=token.substring(0,32); //token是password和userID拼接成的。
        String passwdTrue;
        String useridStr;
        Long userid;
        try{
            useridStr=token.substring(32);
            userid=Long.valueOf(useridStr).longValue();
            passwdTrue=rlService.selectMD5Password(Long.valueOf(userid));
        }catch (Exception e){
            Map<String,String> model =new HashMap();
            model.put("result", "权限校验出现异常");
            ModelAndView modelAndView=new ModelAndView("front/addHelpManResult",model);

            return modelAndView;
        }
        // 1.检查有没有权限
        if(!userPassword.equals(passwdTrue)){
            Map<String,String> model =new HashMap();
            model.put("result", "密码不对");
            ModelAndView modelAndView=new ModelAndView("front/addHelpManResult",model);

            return modelAndView;
        }

        //这里要对字段进行验证
        if (useruuid==null){
            Map<String,String> model =new HashMap();
            model.put("result", "用户未登录");
            ModelAndView modelAndView=new ModelAndView("front/addHelpManResult",model);

            return modelAndView;
        }

        try{
            helpInfo= helpInfoService.selectRecord(useruuid);
        }catch (Exception e){

            Map<String,String> model =new HashMap();
            model.put("result", "查询helpInfo抛出异常");
            ModelAndView modelAndView=new ModelAndView("front/addHelpManResult",model);

            return modelAndView;
        }

        if(helpInfo==null){  //如果是null 说明注册的时候出错
            Map<String,String> model =new HashMap();
            model.put("result", "帮助信息中不存在此用户"); //注册的时候出现错误
            ModelAndView modelAndView=new ModelAndView("front/addHelpManResult",model);

            return modelAndView;
        }else {
            helpInfoMainID=helpInfo.getHelpeachotherid();
            if (helpInfo.getCashtwouuid()!=null  ){//达到发布2次求助信息
                Map<String,String> model =new HashMap();
                model.put("result", "超出发布次数");
                ModelAndView modelAndView=new ModelAndView("front/addHelpManResult",model);
                return modelAndView;
            }
            if (helpInfo.getCashoneuuid()!=null ){
                helpInfoTimes=1; //有过一次申请帮助
            }
        }


        //校验是否上传了图片
        List<String> imgPathArray=new ArrayList<String>();
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //判断 request 是否有文件上传,即多部分请求
        if(multipartResolver.isMultipart(request)){
            //转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
            //取得request中的所有文件名
            //System.out.println(multiRequest.getParameterNames().nextElement());
            Iterator<String> iter = multiRequest.getFileNames();
            //在本次上传中，这个部分路径是常量
            String constDirectory="/huzhuguanjia/weiboPhoto"; //tomcat配置的常量路径+weiboPhoto
            String timeDirectory=new TimeUtil().getNyDay(); //每天创建一个文件夹,时间路径
            String directory=constDirectory+timeDirectory+'/'+useridStr+'/';

            while(iter.hasNext()){
                //记录上传过程起始时的时间，用来计算上传时间
                //int pre = (int) System.currentTimeMillis();
                //取得上传文件
                MultipartFile file = multiRequest.getFile(iter.next());
                if(file != null){
                    //取得当前上传文件的文件名称
                    String myFileName = file.getOriginalFilename();
                    //如果名称不为"",说明该文件存在，否则说明该文件不存在
                    if(myFileName.trim() !=""){
                        //System.out.println(myFileName);
                        //重命名上传后的文件名
                        String originfileName = file.getOriginalFilename();
                        String millisFileName=new TimeUtil().getMil().toString();
                        String fileName=millisFileName+originfileName; //文件名，包含时间戳与原始文件名，确保不重复
                        String path = directory+fileName+".png";
                        //这里面方法更安全，待测试
                        if(CreateFileUtil.createDirectory(directory)==1){//目录已经存在或创建成功
                            File localFile = new File(path);
                            file.transferTo(localFile);
                            String needPath=ipAndport+"path/weiboPhoto"+timeDirectory+'/'+useridStr+'/'+fileName+".png"; //测试使用的
                            // String needPath="http://www.geilove.org/path/weiboPhoto"+timeDirectory+'/'+useridStr+'/'+fileName+".png";
                            imgPathArray.add(needPath);
                        }else{
                            Map<String,String> model =new HashMap();
                            model.put("result", "上传文件不能为空");
                            ModelAndView modelAndView=new ModelAndView("front/addHelpManResult",model);
                            return  modelAndView;
                        }
                    }//if
                }//if
                //记录上传该文件后的时间
                //int finaltime = (int) System.currentTimeMillis();
            }//while
        }// if因为是表单，所以一定会执行if里面，while循环发现无图片会跳出if外
        else{//必须有附带的图片上传，否则这是一个无效的求助项目
            Map<String,String> model =new HashMap();
            model.put("result", "未上传相关证明");
            ModelAndView modelAndView=new ModelAndView("front/addHelpManResult",model);
            return  modelAndView;

        }
        //3.tweet表增加。
        tweet.setBackupneight(tweetUUID); //tweet 的uuid
        tweet.setCashuuid(cashUUID); //cash 的uuid
        tweet.setUseridtweet(userid); //发布推文的userid
        tweet.setSourcemsgid(new Long(1));//1代表非转发
        tweet.setBackuptwelve(moneyTitle); //筹款标题
        tweet.setMsgcontent(content); //放入推文内容到tweet中
        tweet.setTagid((byte)1 );
        tweet.setTopic(new Long(1));
        tweet.setBoxtimes(0);
        tweet.setCommenttimes(0);
        tweet.setOk(0);
        tweet.setPublishtime(date);
        tweet.setReportedtimes(0);
        tweet.setPublicsee((byte)1); //1代表可见
        tweet.setDeletetag((byte)1); //1代表未删除
        // tweet.setVideoaddress(null); //推文只限制3张图
        tweet.setTweetbackupsix(0); //默认承诺0，代表承诺A
        //tweet.setBackupneight(tweetUUID); //tweet的UUID
        tweet.setBackupnine(userName); //用户的昵称
        tweet.setBackupten(selfIntroduce); //用户的自我介绍
        tweet.setBackupeleven(photoUrl); //用户的头像地址
        tweet.setCityname(cityName); //用户所在的城市
        tweet.setPromise(null); //如果是救助一个人，则必须有文字
        tweet.setTweetbackupseven(null);
        tweet.setTweetbackupfour(1); //备用4等于1代表是一个普通的推文2代表的是救助
        tweet.setTweetbackupfive(new Long(1)); //2代表党推文是救助时cash表

        for(int i=0;i<imgPathArray.size();i++){
            if(i==0){
                tweet.setVideoaddress(imgPathArray.get(0));
                continue;
            }
            if(i==1){
                tweet.setTweetbackupone(imgPathArray.get(1));
                continue;
            }
            if(i==2){
                tweet.setTweetbackuptwo(imgPathArray.get(2));
                continue;
            }
            if(i==3){
                tweet.setTweetbackupthree(imgPathArray.get(3));
                continue;
            }
            if(i==4){
                tweet.setTweetbackupseven(imgPathArray.get(4));
                continue;
            }
            if(i==5){
                tweet.setBackupeight(imgPathArray.get(5));
                continue;
            }

        }
        try{
            Integer tag=mainService.addTweet(tweet);
            if(tag!=1){
                Map<String,String> model =new HashMap();
                model.put("result", "发布推文信息失败");
                ModelAndView modelAndView=new ModelAndView("front/addHelpManResult",model);
                return  modelAndView;
            }
        }catch( Exception e){
            Map<String,String> model =new HashMap();
            model.put("result", "发布推文信息抛出异常");
            ModelAndView modelAndView=new ModelAndView("front/addHelpManResult",model);
            return modelAndView;
        }

        //4.cash表增加一条信息。
        /*证明信息*/
        cash.setCashuuid(cashUUID);
        cash.setCashtweetuuid(tweetUUID);
        cash.setBehelpuserid(userid); //被帮助人的userid
        cash.setBehelpusernickname(userName);//受助人昵称
        cash.setBehelpusername(shouZhurenName); //受助人名字
        cash.setPromisemiaoshu(chengnuoContent); //承诺
        try {
            Integer targetMoneyInt=Integer.parseInt(targetMoney);
            if (targetMoneyInt>200000){
                throw new Exception("金额过大");
            }
            cash.setTargetcash(targetMoneyInt); //目标金额

        }catch (Exception e){
            Map<String,String> model =new HashMap();
            model.put("result","目标金额有误");
            ModelAndView modelAndView=new ModelAndView("front/addHelpManResult",model);
            return modelAndView;

        }

        cash.setOpentime(dateStart); //账户开启时间
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date closeDate=sdf.parse(dateEndStr);
            //System.out.println(closeDate);
            cash.setClosetime(closeDate);
        }catch (ParseException e){
            //给个默认的下一个月的今天
            System.out.println(e.getMessage());
        }
        cash.setUseruuid(useruuid);
        cash.setCountstate( new Byte("1")); //关闭
        cash.setRealcash(0); //当前实际金额是0
        cash.setGetmoneytag(new Byte("1")); //1 不可以提取善款
        cash.setCashok(new Byte("1")); //没有达到
        cash.setSpendmoney(0); //支出金额
        cash.setSumaffirm(0); //证实的人数
        cash.setSumzhuanfa(0); //转发的人数
        cash.setSumattention(0);
        cash.setSumbackup(0); //支持的次数
        cash.setSummanbackup(0);//支持的人数
        cash.setAcceptmoneyman(acceptMoneyName);
        cash.setBackuptwo(acceptMoneyPhone); //资金接收人的电话
        cash.setBackupone(shouZhureniDentityNo); //受助人身份证号
        //各种证明

        if (proves!=null && "".equals(proves)){
            for (int k=0;k<proves.length;k++){
                System.out.println(proves[k]);
                if ("11".equals(proves[k])){
                    cash.setProveone(11);    //身份证明
                    continue;
                }
                if ("22".equals(proves[k])){
                    cash.setProvetwo(22);    //居委会证明
                    continue;
                }
                if ("33".equals(proves[k])){
                    cash.setProvethree(33);  //医院证明
                    continue;
                }
                if ("44".equals(proves[k])){
                    cash.setProvefour(44);   //贫困证明
                    continue;
                }
                if ("55".equals(proves[k])){
                    cash.setBackupfive(55);  //收款人关系证明
                    continue;
                }
            }
        }else {
            Map<String,String> model =new HashMap();
            model.put("result", "至少有一种证明");
            ModelAndView modelAndView=new ModelAndView("front/addHelpManResult",model);

            return modelAndView;
        }
        //承诺
        if (promises!=null){
            for(int i=0;i<promises.length;i++){
               // System.out.println(promises[i]);
                if ("1".equals(promises[i])){
                    cash.setPromisetype(1);
                    continue;
                }
                if ("2".equals(promises[i])){
                    cash.setPromisetype(2);
                    continue;
                }
                if ("3".equals(promises[i])){
                    cash.setPromisetype(3);
                    continue;
                }
                else {
                    cash.setPromisetype(1); //默认值
                }

            }
        }

        try{
            int cashTag=cashService.cashInsert(cash); //
        }catch (Exception e){
            Map<String,String> model =new HashMap();
            model.put("result", "新增资金记录记录抛出异常");
            ModelAndView modelAndView=new ModelAndView("front/addHelpManResult",model);

            return modelAndView;
        }

        //5.HelpInfo 表增加，在创建用户的时候就增加用户对应该记录
        // 注意检查是第一次还是第二次
        helpInfo.setHelpeachotherid(helpInfoMainID);
        helpInfo.setHelpeachotheruuid(helpeachotheruuid);
        helpInfo.setUserid(userid); //被帮助人的id
        helpInfo.setUseruuid(useruuid); //被帮助用户的uuid
        //这儿需要判定是第一次还是第二次
        if (helpInfoTimes==0){ //第一次
            helpInfo.setTweetoneuuid(tweetUUID);
            helpInfo.setCashoneuuid(cashUUID);
            helpInfo.setPromiseonetype(cash.getPromisetype());
            helpInfo.setPromiseonedesp(cash.getPromisemiaoshu());
        }
        if (helpInfoTimes==1){ //第二次
            helpInfo.setTweettwouuid(tweetUUID);
            helpInfo.setCashtwouuid(cashUUID);  //第二次被帮助
            helpInfo.setPromisetwotype(cash.getPromisetype());
            helpInfo.setPromisetwodesp(cash.getPromisemiaoshu());
        }
        try {
            int helpInfoInsertTag=helpInfoService.updateRecord(helpInfo); //这里应该是更新，因为数据在注册的时候就存在
        }catch (Exception e){
            Map<String,String> model =new HashMap();
            model.put("result", "新增救助信息抛出异常");
            ModelAndView modelAndView=new ModelAndView("front/addHelpManResult",model);
            return modelAndView;
        }

        Map<String,String> model =new HashMap();
        model.put("result", "sucess");
        ModelAndView modelAndView2=new ModelAndView("front/addHelpManResult",model);
        return modelAndView2;

    }
}
