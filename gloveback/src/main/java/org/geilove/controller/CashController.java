package org.geilove.controller;
import org.geilove.util.ServerIP;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.geilove.pojo.Cash;
import org.geilove.pojo.Tweet;
import org.geilove.requestParam.CashParam;
import org.geilove.response.CashResponse;
import org.geilove.response.CommonRsp;
import org.geilove.service.CashService;
import org.geilove.service.MainService;
import org.geilove.service.RegisterLoginService;
import org.geilove.util.CreateFileUtil;
import org.geilove.util.TimeUtil;

@Controller
@RequestMapping("/cash")
public class CashController {
	@Resource
	private MainService  mainService;
	@Resource
	private RegisterLoginService rlService;
	@Resource
	private CashService cashService;
    /*如果推文是救助信息，这里获取有关救助的说明*/	
	@RequestMapping(value="/getcashrecord",method=RequestMethod.POST)
	public @ResponseBody CashResponse getCashRecord(@RequestBody CashParam cashparam){
		CashResponse rsp=new CashResponse();
		Cash cash=new Cash();
		try{
			cash=cashService.getCashRecord(cashparam.getCashid());  //转换为long类型
		}catch(Exception e){			
		}		
		if(cash!=null){
			rsp.setData(cash);
			rsp.setMsg("needCash信息获取成功");
			rsp.setRetcode(2000);
		}else{
			rsp.setData(null);
			rsp.setMsg("needCash信息获取失败");
			rsp.setRetcode(2001);
		}
		return rsp;
	}
	
	@RequestMapping(value="/addhelpman",method=RequestMethod.POST)
	@ResponseBody
	public CommonRsp addHelpMan(HttpServletRequest request)throws IllegalStateException, IOException{	
		CommonRsp commonRsp=new CommonRsp();

        String ipAndport= ServerIP.getiPPort(); //http://172.16.32.52:8080

		String token=request.getParameter("token");			
		String userPassword=token.substring(0,32); //token是password和userID拼接成的。
		String useridStr=token.substring(32);		
		Long userid=Long.valueOf(useridStr).longValue();
		try{
            String passwdTrue=rlService.selectMD5Password(Long.valueOf(userid));
            if(!userPassword.equals(passwdTrue)){
                commonRsp.setRetcode(2001);
                commonRsp.setMsg("用户密码不对，非法");
                return commonRsp;
            }
        }catch (Exception e){
            commonRsp.setRetcode(2001);
            commonRsp.setMsg("用户身份验证出现异常");
            return commonRsp;
        }

		List<String> imgPathArray=new ArrayList<String>(); 

		//创建一个通用的多部分解析器  
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
        //判断 request 是否有文件上传,即多部分请求  
        if(multipartResolver.isMultipart(request)){  
            //转换成多部分request    
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
            //取得request中的所有文件名  
            //System.out.println(multiRequest.getParameterNames().nextElement()); 
            Iterator<String> iter = multiRequest.getFileNames();
            //在本次上传中，这个部分路径是常量
            String constDirectory="/huzhuguanjia/helpWeiboPhoto"; //tomcat配置的常量路径+weiboPhoto
            String timeDirectory=new TimeUtil().getNyDay(); //每天创建一个文件夹,时间路径
            String directory=constDirectory+timeDirectory+'/'+useridStr+'/';                       
            
            while(iter.hasNext()){               
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
                              //System.out.println(directory);
                              //这里的path指的是配置里的名字,注意path前面没有/，客户端拼接url地址时应该加上
                              String needPath=ipAndport+"path/helpWeiboPhoto"+timeDirectory+'/'+useridStr+'/'+fileName+".png"; //测试使用的
                              //String needPath="http://www.geilove.org/path/helpWeiboPhoto"+timeDirectory+'/'+useridStr+'/'+fileName+".png";
                              imgPathArray.add(needPath);                             
                        }else{
                        	commonRsp.setMsg("创建磁盘目录失败");
                        	commonRsp.setRetcode(2002);
                        	return commonRsp;
                        }                              
                    }//if  
                }//if  
                //记录上传该文件后的时间  
                
            }//while             
        }// if因为是表单，所以一定会执行if里面，while循环发现无图片会跳出if外

		Tweet tweet=new Tweet();
        String tweetUUID = UUID.randomUUID().toString();
        String cashUUID=UUID.randomUUID().toString();
        tweet.setBackupneight(tweetUUID);                   //推文的uuid
        tweet.setCashuuid(cashUUID);                         //cashUUID

		tweet.setUseridtweet(userid);                       //发布推文的userid
		tweet.setSourcemsgid(new Long(0));            //0 是默认值

        String moneyTitle=request.getParameter("moneyTitle");//求助的标题
        tweet.setBackuptwelve(moneyTitle);

        String content=request.getParameter("description");//推文内容，有关困难的详细描述
        tweet.setMsgcontent(content);

        String chengnuoType=request.getParameter("chengnuoType");
        tweet.setTweetbackupsix(new Integer(chengnuoType)); //承诺的类型
        String chengnuoContent=request.getParameter("chengnuoContent"); //承诺的语句，这个要放到推文表
        tweet.setPromise(chengnuoContent);

        String usernickname=request.getParameter("usernickname"); //用户的昵称
        tweet.setBackupnine(usernickname);

        String selfintroduce=request.getParameter("selfintroduce"); //用户的自我介绍
        tweet.setBackupten(selfintroduce);

        String userPhoto=request.getParameter("userPhoto"); //用户的头像地址
        tweet.setBackupeleven(userPhoto);

        tweet.setCashid(new Long(0)); //默认为零，这个字段没有使用

        String cityName=request.getParameter("cityName"); //受助用户所在的城市
        tweet.setCityname(cityName);

        tweet.setTagid((byte)1 );               //1代表这个是原创
        tweet.setTopic(new Long(1));     //从属于的话题 默认是1，没有使用
        tweet.setBoxtimes(0);                   // 收藏的数量
        tweet.setCommenttimes(0);               //被评论的次数，默认是 0
        tweet.setOk(0);                         //赞的次数，默认是0
        Date date=new Date();
        tweet.setPublishtime(date);             //推文发布的时间
        tweet.setReportedtimes(0);              //被举报的次数，默认是0

        tweet.setPublicsee((byte)1);            //1代表可见
        tweet.setDeletetag((byte)1);           //1代表未删除

        for(int i=0;i<imgPathArray.size();i++){
        	if(i==0){
        		tweet.setVideoaddress(imgPathArray.get(0));
        	}
        	if(i==1){
                tweet.setTweetbackupone(imgPathArray.get(1));

        	}
            if(i==2){
                tweet.setTweetbackuptwo(imgPathArray.get(2));

            }
        	if(i==3){
        		tweet.setTweetbackupthree(imgPathArray.get(3));
        	}
            if(i==4){
                tweet.setTweetbackupseven(imgPathArray.get(4));
            }
            if(i==5){
                tweet.setBackupeight(imgPathArray.get(5));
            }

        }  
        try{
        	Integer tag=mainService.addTweet(tweet);
        	if(tag!=1){ 
        		commonRsp.setRetcode(2001);
        		commonRsp.setMsg("救助推文插入数据库失败");
        		return commonRsp;
        	}
        }catch( Exception e){
            commonRsp.setRetcode(2001);
            commonRsp.setMsg("救助信息入库出现异常");
            return commonRsp;

        }

        //把其它的数据插入到cash表中
        Cash cash=new Cash();

        String shouZhurenName=request.getParameter("shouZhurenName");  //受助人姓名
        String shouZhureniDentityNo=request.getParameter("shouZhureniDentityNo");
        String idZhengming=request.getParameter("idZhengming"); //是否有身份证明
        String jwZhengming=request.getParameter("jwZhengming"); //是否用居委会证明
        String yyZhengming=request.getParameter("yyZhengming"); //是否有医院证明
        String pinkunZhengming=request.getParameter("pinkunZhengming"); //贫困证明
        String acceptMoneyPhone=request.getParameter("acceptMoneyPhone"); //收款人联系电话
        String acceptMoneyName=request.getParameter("acceptMoneyName"); // 收款人姓名
        String relationZhengming=request.getParameter("relationZhengming");   //收款人关系证明
        String targetCash=request.getParameter("targetMoney"); //目标金额
        String closetime=request.getParameter("endDate"); //账户关闭的时间
        String  duration=request.getParameter("duration"); //持续的时长

        cash.setCashuuid(cashUUID);              //cash的UUID  /*cash的uuid和tweetuuid相互存入对方*/
        cash.setCashtweetuuid(cashUUID);         //tweetUUID;
        cash.setBehelpusername(usernickname);   //受助人的互助管家昵称，同推文的昵称
        cash.setAcceptmoneyman(acceptMoneyName); //收款人姓名
        cash.setBackuptwo(acceptMoneyPhone);     //资金接收人手机后，方便联系
        cash.setBehelpuserid(userid);           // 受助人的iD
        cash.setBehelpusername(shouZhurenName); //受助人真实姓名
        cash.setBackupone(shouZhureniDentityNo); //受助人的身份证
        cash.setPromisetype(new Integer(chengnuoType)); // 承诺类型
        cash.setPromisemiaoshu(chengnuoContent);       //承诺的内容
        /*各种证明,以下有异常*/
        try{
            if ("false".equals(idZhengming)){
                cash.setProveone(0);
            }else {
                cash.setProveone(1);
            }
            if ("false".equals(jwZhengming)){
                cash.setProvetwo(0);
            }else {
                cash.setProvetwo(1);
            }
            if ("false".equals(yyZhengming)){
                cash.setProvethree(0);
            }else {
                cash.setProvethree(1);
            }
            if ("false".equals(pinkunZhengming)){
               cash.setProvefour(0);
            }else {
               cash.setProvefour(1);
            }
            if ("false".equals(relationZhengming)){
                cash.setProvefive(0);
            }else {
                cash.setProvefive(1);
            }


        }catch (Exception e){
            System.out.println(e);
        }



        cash.setCountstate((byte)2); // 账户开启
        cash.setRealcash(0); //开始的时候实际金额是0
        cash.setGetmoneytag((byte)1); //1 代表还不可以提取钱
        cash.setCashok((byte)1); //1代表不可以提取钱
        cash.setSpendmoney(0); //已经支出的钱

        cash.setCountstate((byte)1);   //账户是关闭状体
        cash.setTargetcash(new Integer(targetCash));  //目标金额。
        cash.setGetmoneytag((byte)1);   //不可以提取
        cash.setCashok((byte)1);   //未达到募捐金额
        cash.setSpendmoney(0);   //支出金额是0
        cash.setSumaffirm(0);    //证人数量是0
        cash.setSumattention(0);  //关注的次数是0
        cash.setSumbackup(0);     //支持的次数为0
        cash.setSummanbackup(0);  //支持的人数为0

        cash.setCreatedate(new Date());  //创立的时间
        cash.setSumdays(new Integer(duration)); //持续的时长

        try {
            DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            Date closeDate=null;
            closeDate=format1.parse(closetime);
            cash.setClosetime(closeDate);  //字符串转时间类型，此处抛出异常

        }catch ( Exception e){
            System.out.println(e);
        }

        cash.setProgressstate(0);   //进度状态，0未审核
        cash.setCashcityname(cityName); //用户所在的城市
        cash.setBackupfive(0);   //进度更新的次数


        try{
        	Integer tag=cashService.cashInsert(cash);
        	if(tag!=1){ 
        		commonRsp.setRetcode(2001);
        		commonRsp.setMsg("cash表插入失败");
        		return commonRsp;
        	}
        }catch( Exception e){
            commonRsp.setRetcode(2001);
            commonRsp.setMsg("cash表入库抛出异常");
            return commonRsp;
        }
        
    	commonRsp.setMsg("发布成功");
    	commonRsp.setRetcode(2000);
    	return commonRsp;
	}//multiUpload
}
	
		
