package org.geilove.controller;
//普通用户认证，爱心社青年志愿者认证认证，监督处认证，公益机构认证，都用这个类
//url地址相同，但是传入的参数不同
//认证通过后，更改认证表的相应状态并且更新User表的相应属性信息

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.geilove.pojo.RenZheng;
import org.geilove.service.RenZhengService;
import org.geilove.util.ServerIP;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.geilove.response.CommonRsp;
import org.geilove.service.MainService;
import org.geilove.service.RegisterLoginService;
import org.geilove.util.CreateFileUtil;
import org.geilove.util.TimeUtil;
@Controller
@RequestMapping("/renzheng")
public class RenZhengController {	
    
	@Resource
	private MainService  mainService;
	@Resource
	private RegisterLoginService rlService;
	@Resource
	private RenZhengService renZhengService;
	
	@RequestMapping(value="/author",method=RequestMethod.POST)
	@ResponseBody
	public CommonRsp multiUpload(HttpServletRequest request)throws IllegalStateException, IOException{	
		CommonRsp commonRsp=new CommonRsp();

		String token=request.getParameter("token");		
		String userPassword=token.substring(0,32); //token是password和userID拼接成的。
		String useridStr=token.substring(32); //取得userid部分		
		Long userid=Long.valueOf(useridStr).longValue();  //转换成long类型
		String passwdTrue=rlService.selectMD5Password(Long.valueOf(userid));
		if(!userPassword.equals(passwdTrue)){
			commonRsp.setRetcode(2001);
			commonRsp.setMsg("用户密码不对，非法");
			return commonRsp;
		}
		List<String> imgPathArray=new ArrayList<String>(); //存放图片路径的

		//创建一个通用的多部分解析器  
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
        //判断 request 是否有文件上传,即多部分请求  
        if(multipartResolver.isMultipart(request)){  
            //转换成多部分request    
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
            //取得request中的所有文件名  
            /*System.out.println(multiRequest.getParameterNames().nextElement()); */
            Iterator<String> iter = multiRequest.getFileNames();
            //在本次上传中，这个部分路径是常量,必须到Tomcat目录下启动，才能看到效果
            String constDirectory="/Users/aihaitao/staticImage"; //tomcat配置的常量路径+weiboPhoto
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
                              file.transferTo(localFile);  //把图片存入到磁盘上
                              System.out.println(directory);
                              imgPathArray.add(path);
                        }else{
                        	commonRsp.setMsg("创建磁盘目录失败");
                        	commonRsp.setRetcode(2001);
                        	return commonRsp;
                        }                              
                    }//if  
                }//if  
                //记录上传该文件后的时间  
                //int finaltime = (int) System.currentTimeMillis();   
            }//while             
        }// if因为是表单，所以一定会执行if里面，while循环发现无图片会跳出if外
        else{
			commonRsp.setMsg("没有文件");
			commonRsp.setRetcode(2001);
			return commonRsp;
		}
		//认证的照片地址要放入到picture表中
		//Picture picture=new Picture(); //imgPathArray里的url地址放入picture中
		//User user=new User(); //认证信息就是更新这个
		RenZheng renZheng=new RenZheng(); //要更新认证表RenZheng表
        String tag=request.getParameter("tag"); //取得标识

		if (tag.equals("1")){ //个人认证
			String realName=request.getParameter("realName"); //真实姓名
			String phoneNo=request.getParameter("phoneNo"); //手机号
			String identitycard=request.getParameter("idno"); //身份证号
			String mail=request.getParameter("mail");// 认证用户的邮箱

            renZheng.setRealmanok(1); //1代表实名认证
			renZheng.setIdentitycard(identitycard); //身份证号
			renZheng.setRealname(realName); //真实姓名
			renZheng.setMobile(phoneNo); //手机号
			renZheng.setMail(mail); //邮箱
			for(int i=0;i<imgPathArray.size();i++){
				if (i==0) renZheng.setImg0(imgPathArray.get(0));
				if (i==1) renZheng.setImg1(imgPathArray.get(1));
				if (i==2) renZheng.setImg2(imgPathArray.get(2));
				if (i==3) renZheng.setImg3(imgPathArray.get(3));
				if (i==4) renZheng.setImg4(imgPathArray.get(4));
			}

		}else if(tag.equals("2")){ //爱心社认证，认证不同，更新的字段也是不同的
        	String loveTeamDesp=request.getParameter("selfIntroduce"); //爱心社用户认证的简介，用户填写

			renZheng.setLoveteamok(1); //1代表爱心社用户认证申请中
			renZheng.setLoveteamdesp(loveTeamDesp); //用户填写的认证说明
			renZheng.setSupervisedate(new Date()); //用户的认证时间

			for(int i=0;i<imgPathArray.size();i++){
				if (i==0) renZheng.setImg5(imgPathArray.get(0));
				if (i==1) renZheng.setImg6(imgPathArray.get(1));
				if (i==2) renZheng.setImg7(imgPathArray.get(2));
				if (i==3) renZheng.setImg8(imgPathArray.get(3));
			}
        	
        }else if(tag.equals("3")) { //监督处认证,
			String superviseDesp = request.getParameter("selfIntroduce");

			renZheng.setSuperviseok(1);
			renZheng.setSupervisedesp(superviseDesp);
			renZheng.setSupervisedate(new Date());

			for(int i=0;i<imgPathArray.size();i++){
				if (i==0) renZheng.setImg9(imgPathArray.get(0));
				if (i==1) renZheng.setImg10(imgPathArray.get(1));
				if (i==2) renZheng.setImg11(imgPathArray.get(2));
				if (i==3) renZheng.setImg12(imgPathArray.get(3));
			}


		}else if (tag.equals("4")){ //青年志愿者认证,也就是爱心社认证
			 String loveTeamDesp=request.getParameter("selfIntroduce");
			 renZheng.setVolunteerok(1);
			 renZheng.setVolunteerdesp(loveTeamDesp);
			 renZheng.setVolunteerdate(new Date());

			for(int i=0;i<imgPathArray.size();i++){
				if (i==0) renZheng.setImg5(imgPathArray.get(0));
				if (i==1) renZheng.setImg6(imgPathArray.get(1));
				if (i==2) renZheng.setImg7(imgPathArray.get(2));
				if (i==3) renZheng.setImg8(imgPathArray.get(3));
			}


        }else if(tag.equals("5")){ // 社会公益机构认证
        	//第二版本开通
			String welfareDesp=request.getParameter("welfareDesp"); //机构名称
			String welfareType=request.getParameter("welfareType"); //机构联系电话
			String welfareScale=request.getParameter("welfareScale"); //机构性质
			String welfareConunry=request.getParameter("welfareConunry"); //机构从事的领域
			String welfareAddress=request.getParameter("welfareAddress");//机构的人数规模，转换为数字

			renZheng.setWelfareok(1); //社会公益机构认证
			renZheng.setWelfaredesp(welfareDesp); //公益机构的认证结果
			renZheng.setWelfaretype(welfareType); //公益机构的性质
			renZheng.setWelfarescale(Integer.parseInt(welfareScale)); //公益机构的规模
			renZheng.setWelfareconunry(welfareConunry); //认证机构所属的国家
			renZheng.setWelfareaddress(welfareAddress); //地址
			renZheng.setWelfaredate(new Date()); //认证申请时间

			for(int i=0;i<imgPathArray.size();i++){
				if (i==0) renZheng.setImg13(imgPathArray.get(0));
				if (i==1) renZheng.setImg14(imgPathArray.get(1));
				if (i==2) renZheng.setImg15(imgPathArray.get(2));
				if (i==3) renZheng.setImg16(imgPathArray.get(3));
				if (i==4) renZheng.setImg17(imgPathArray.get(4));
				if (i==5) renZheng.setImg18(imgPathArray.get(5));
				if (i==6) renZheng.setImg19(imgPathArray.get(6));
			}
			commonRsp.setMsg("暂未开通");
			commonRsp.setRetcode(2001);
			return commonRsp;
        }

        /*这里判断是否已有此用户，有就是更新，没有就是插入*/
        RenZheng renZheng2=null;
        try{
			renZheng2=renZhengService.getRenzheng(userid);

		}catch (Exception e){
			commonRsp.setMsg("认证查询出现异常");
			commonRsp.setRetcode(2001);
			return commonRsp;
		}
		if (renZheng2==null){
			//插入
			try{
				int insertTag=renZhengService.insertRecord(renZheng);
				if (insertTag==1){
					commonRsp.setMsg("认证资料提交成功");
					commonRsp.setRetcode(2000);
					return commonRsp;
				}
			}catch (Exception e){
				commonRsp.setMsg("认证资料提交抛出异常");
				commonRsp.setRetcode(2001);
				return commonRsp;
			}

		}
		if (renZheng2!=null){
			//更新
			try{
				int insertTag=renZhengService.updateRecord(renZheng);
				if (insertTag==1){
					commonRsp.setMsg("认证资料更新提交成功");
					commonRsp.setRetcode(2000);
					return commonRsp;
				}
			}catch (Exception e){
				commonRsp.setMsg("认证资料更新提交抛出异常");
				commonRsp.setRetcode(2001);
				return commonRsp;
			}

		}
        //插入数据库并判断是否成功
    	commonRsp.setMsg("认证成功");
    	commonRsp.setRetcode(2000);
    	return commonRsp;
	}//multiUpload
}


























