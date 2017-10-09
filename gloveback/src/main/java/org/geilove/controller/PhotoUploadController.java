package org.geilove.controller;
//用户上传头像的
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.geilove.pojo.User;
import org.geilove.util.ServerIP;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.geilove.response.CommonRsp;
import org.geilove.service.MainService;
import org.geilove.service.RegisterLoginService;
import org.springframework.web.servlet.ModelAndView;

//这个后期可以改为用用户的uuid和userid命名头像
@Controller
@RequestMapping("/photo")
public class PhotoUploadController {//上传用户头像
    
	@Resource
	private MainService  mainService;
	@Resource
	private RegisterLoginService rlService;

	@RequestMapping(value="/setting.do",method = RequestMethod.GET)
	public String setting(){
		String index="front/setting";
		return index;
	}
	@RequestMapping(value="upload.do",method=RequestMethod.POST)
	@ResponseBody
	public CommonRsp multiUpload(HttpServletRequest request)throws IllegalStateException, IOException{
		//System.out.print("aaa");
		CommonRsp commonRsp=new CommonRsp();

		String ipAndport= ServerIP.getiPPort(); //特别注意正式部署服务器端口是80，而不是8080

		String token=request.getParameter("token");			
		String userPassword=token.substring(0,32); //token是password和userID拼接成的。
		String useridStr=token.substring(32);		
		Long userid=Long.valueOf(useridStr).longValue();
		String passwdTrue=rlService.selectMD5Password(Long.valueOf(userid));
		if(!userPassword.equals(passwdTrue)){
			commonRsp.setRetcode(2001);
			commonRsp.setMsg("用户密码不对，非法");
			return commonRsp;
		}
		 MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request; 
         CommonsMultipartFile orginalFile = (CommonsMultipartFile) multipartRequest.getFile("photo");
         String filename = orginalFile.getOriginalFilename();
         //System.out.println(filename);
		 InputStream is = null;// 附件输入流
         try {
			  DataOutputStream out = new DataOutputStream(new FileOutputStream("/huzhuguanjia/userPhoto/" + filename+".jpg"));
	          is = orginalFile.getInputStream();
	          byte[] b=new byte[is.available()];
	          is.read(b);
	          out.write(b);
	         }
         catch (IOException exception) {
			    exception.printStackTrace();
	        	commonRsp.setMsg("上传头像失败");
	          	commonRsp.setRetcode(2001);
	          	return commonRsp;
	         }
         finally {
			 if (is != null) {
				 is.close();
	         }
         }
        //插入数据库并判断是否成功,注意设置User表的photoUpload为2
		User user=new User();
        user.setUserid(userid);
        //Tips:下面最好换成注释掉的方法，方便以后迁移
        String realiP=ipAndport+"path/userPhoto/"+ filename+".jpg";
        user.setUserphoto(realiP);
        //user.setUserphoto("http://www.geilove.org/path/userPhoto" + filename+".jpg");
        user.setPhotoupload((byte)2); //更新标志设置为2
        //调用user表的更新方法
		try {
			rlService.updateUserSelective(user);
		}catch (Exception e){
			commonRsp.setMsg("上传头像失败更新数据库失败");
			commonRsp.setRetcode(2001);
			return commonRsp;
		}
    	commonRsp.setMsg("发布成功");
    	commonRsp.setRetcode(2000);
    	return commonRsp;
	}

	@RequestMapping(value="jspupload.do")
	@ResponseBody
	public ModelAndView jspmultiUpload(HttpServletRequest request)throws IllegalStateException, IOException{
		//System.out.print("aaa");
		CommonRsp commonRsp=new CommonRsp();

		String ipAndport= ServerIP.getiPPort(); //特别注意正式部署服务器端口是80，而不是8080

		String token=request.getParameter("token");
		String userPassword=token.substring(0,32); //token是password和userID拼接成的。
		String useridStr=token.substring(32);
		Long userid=Long.valueOf(useridStr).longValue();
		String passwdTrue=rlService.selectMD5Password(Long.valueOf(userid));
		if(!userPassword.equals(passwdTrue)){
			Map<String,String> model =new HashMap();
			model.put("result", "您有必要字段为空");
			ModelAndView modelAndView=new ModelAndView("front/addHelpManResult",model);
			return modelAndView;
		}
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		CommonsMultipartFile orginalFile = (CommonsMultipartFile) multipartRequest.getFile("photo");
		String filename = orginalFile.getOriginalFilename();
		//System.out.println(filename);
		InputStream is = null;// 附件输入流
		try {
			DataOutputStream out = new DataOutputStream(new FileOutputStream("/huzhuguanjia/userPhoto/" + filename+".jpg"));
			is = orginalFile.getInputStream();
			byte[] b=new byte[is.available()];
			is.read(b);
			out.write(b);
		}
		catch (IOException exception) {
			exception.printStackTrace();
			Map<String,String> model =new HashMap();
			model.put("result", "您有必要字段为空");
			ModelAndView modelAndView=new ModelAndView("front/addHelpManResult",model);
			return modelAndView;
		}
		finally {
			if (is != null) {
				is.close();
			}
		}
		//插入数据库并判断是否成功,注意设置User表的photoUpload为2
		User user=new User();
		user.setUserid(userid);
		//Tips:下面最好换成注释掉的方法，方便以后迁移
		String realiP=ipAndport+"path/userPhoto/"+ filename+".jpg";
		user.setUserphoto(realiP);
		//user.setUserphoto("http://www.geilove.org/path/userPhoto" + filename+".jpg");
		user.setPhotoupload((byte)2); //更新标志设置为2
		//调用user表的更新方法
		try {
			rlService.updateUserSelective(user);
		}catch (Exception e){
			Map<String,String> model =new HashMap();
			model.put("result", "您有必要字段为空");
			ModelAndView modelAndView=new ModelAndView("front/addHelpManResult",model);
			return modelAndView;
		}
		Map<String,String> model =new HashMap();
		model.put("result", "您有必要字段为空");
		ModelAndView modelAndView=new ModelAndView("front/addHelpManResult",model);
		return modelAndView;
	}
}



























