package org.geilove.controller;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.geilove.pojo.Cash;
import org.geilove.pojo.HelpInfo;
import org.geilove.service.CashService;
import org.geilove.service.impl.HelpInfoServiceImpl;
import org.geilove.util.ServerIP;
import org.geilove.vo.BaseVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.geilove.vo.UploadDemoVo;
import org.geilove.pojo.Tweet;
import org.geilove.response.CommonRsp;
import org.geilove.service.MainService;
import org.geilove.service.RegisterLoginService;
import org.geilove.util.CreateFileUtil;
import org.geilove.util.TimeUtil;
import org.geilove.util.TokenData;
import org.geilove.util.TestFun;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;
@Controller
@RequestMapping("/demo/upload")
public class FileUploadController {	 //发布推文,带图片的
    
	@Resource
	private MainService  mainService;
	@Resource
	private RegisterLoginService rlService;
	@Resource
    private HelpInfoServiceImpl  helpInfoService;

	@Resource
    private CashService cashService;

	@RequestMapping(value="/multiUpload",method=RequestMethod.POST)
	@ResponseBody
	public CommonRsp multiUpload(HttpServletRequest request)throws IllegalStateException, IOException{
        //System.out.println("multiUpload");
        // 先获取各种数据，然后统一校验，最后对表进行增加和更新
		CommonRsp commonRsp=new CommonRsp();
        String ipAndport= ServerIP.getiPPort(); //http://172.16.32.52:8080


		String token=request.getParameter("token");			
		String userPassword=token.substring(0,32); //token是password和userID拼接成的。
		String useridStr=token.substring(32);		
		Long userid=Long.valueOf(useridStr).longValue();
        String passwdTrue=null;


        try{
            passwdTrue=rlService.selectMD5Password(Long.valueOf(userid));
        }catch (Exception e){
            commonRsp.setRetcode(2001);
            commonRsp.setMsg("发布推文出错");
            return commonRsp;
        }

		if(!userPassword.equals(passwdTrue)){
			commonRsp.setRetcode(2001);
			commonRsp.setMsg("用户密码不对，非法");
			return commonRsp;
		}
		List<String> imgPathArray=new ArrayList<String>();  //这个是图片的URL地址。http://wwww.geilove.org/path/weiboPhoto/.../88.png

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
                        	commonRsp.setMsg("创建磁盘目录失败");
                        	commonRsp.setRetcode(2005);
                        	return commonRsp;
                        }                              
                    }//if  
                }//if  
                //记录上传该文件后的时间  
                //int finaltime = (int) System.currentTimeMillis();   
            }//while             
        }// if因为是表单，所以一定会执行if里面，while循环发现无图片会跳出if外
		Tweet tweet=new Tweet();
		tweet.setUseridtweet(userid); //发布推文的userid
		tweet.setSourcemsgid(new Long(1));//1代表非转发
        String content=request.getParameter("content"); //获取推文的内容
        tweet.setMsgcontent(content); //放入推文内容到tweet中
        tweet.setTagid((byte)1 );
        tweet.setTopic(new Long(1));
        tweet.setBoxtimes(0);
        tweet.setCommenttimes(0);
        tweet.setOk(0);
        Date date=new Date();
        tweet.setPublishtime(date);
        tweet.setReportedtimes(0);
        tweet.setPublicsee((byte)1); //1代表可见
        tweet.setDeletetag((byte)1); //1代表未删除
        tweet.setVideoaddress(null); //推文只限制3张图
        tweet.setTweetbackupsix(0); //默认承诺0，代表无承诺
        String tweetuuid = UUID.randomUUID().toString();
        tweet.setBackupneight(tweetuuid); //tweet的UUID

        tweet.setBackupnine(request.getParameter("userNickname")); //用户的昵称
        tweet.setBackupten(request.getParameter("selfintroduce")); //用户的自我介绍
        tweet.setBackupeleven(request.getParameter("userphoto")); //用户的头像地址
        tweet.setCityname(request.getParameter("cityName")); //用户所在的城市
        //还有个发表用户的uuid由于表中无，暂时没加入
        //tweet.setuserUUIDTweet(request.getParameter("userUUIDTweet"));
        tweet.setPromise(null); //如果是救助一个人，则必须有文字
        tweet.setTweetbackupseven(null);
        tweet.setTweetbackupfour(1); //备用4等于1代表是一个普通的推文2代表的是救助     
        tweet.setTweetbackupfive(new Long(1)); //2代表党推文是救助时cash表
        for(int i=0;i<imgPathArray.size();i++){
        	if(tweet.getTweetbackupone()==null){
        		tweet.setTweetbackupone(imgPathArray.get(i));
        	}else if(tweet.getTweetbackuptwo()==null){
        		tweet.setTweetbackuptwo(imgPathArray.get(i));
        	}else{
        		tweet.setTweetbackupthree(imgPathArray.get(i));
        	}
        }  
        try{
        	Integer tag=mainService.addTweet(tweet);
        	if(tag!=1){ 
        		commonRsp.setRetcode(2001);
        		commonRsp.setMsg("推文插入数据库失败");
        		return commonRsp;
        	}
        }catch( Exception e){
        	System.out.println(e);
        }
        //插入数据库并判断是否成功
    	commonRsp.setMsg("发布成功");
    	commonRsp.setRetcode(2000);
    	return commonRsp;
	}//multiUpload

}


























