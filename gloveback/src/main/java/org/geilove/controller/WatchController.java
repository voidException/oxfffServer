package org.geilove.controller;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.geilove.requestParam.QueryWatchIfParam;
import org.geilove.response.QueryIfWatchRsp;
import org.geilove.sqlpojo.PartWatchPojo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.geilove.pojo.DoubleFans;
import org.geilove.requestParam.CancelWatchParam;
import org.geilove.requestParam.FollowParam;

import org.geilove.response.CommonRsp;
import org.geilove.service.RegisterLoginService;
import org.geilove.service.RelationService;
import org.geilove.service.WatchService;
/*
 * 这个用来提供关注一个人，取消关注一个人的接口,我的粉丝列表，我关注的列表功能
*/
@Controller
@RequestMapping("/watch")
public class WatchController {
	@Resource
	private WatchService watchService;	
	@Resource
	private RegisterLoginService rlService;
	@Resource
	private RelationService relationService;
	
	@RequestMapping(value="/dowatch",method=RequestMethod.POST)
	public @ResponseBody CommonRsp doWatch(@RequestBody FollowParam followParam ){//关注一个人
		CommonRsp commonRsp=new CommonRsp();
		String token=followParam.getToken(); //获取登录凭证
		String userPassword=token.substring(0,32); //token是password和userID拼接成的。
		String useridStr=token.substring(32);		
		Long userid=Long.valueOf(useridStr).longValue();		
		String passwdTrue=rlService.selectMD5Password(Long.valueOf(userid));
		
		if(!userPassword.equals(passwdTrue)){
			commonRsp.setRetcode(2001);
			commonRsp.setMsg("用户验证失败，非法");
			return commonRsp;
		}
		DoubleFans  dbfans=new DoubleFans();
		dbfans.setUseridfollowe(userid);
	    dbfans.setUseridbefocus(followParam.getUserIDBeFocus());
	    dbfans.setPaytag(followParam.getPaytag());
	    Date date=new Date();
	    dbfans.setPaydate(date);
	    dbfans.setGroupid((byte)1);
	    dbfans.setSpecialfollow((byte)1);
	    dbfans.setDoublefans((byte)1);
		//应该先查询下是否有关注
		Map<String,Object> map= new HashMap<String,Object>();
		map.put("taUserid",followParam.getUserIDBeFocus());
		map.put("myUserid",userid);
		PartWatchPojo partWatchPojo ;
		try{
			partWatchPojo=watchService.watchMayNot(map);
		}catch (Exception e){
			commonRsp.setMsg("关注时预查询出错了");
			commonRsp.setRetcode(2001);
			return  commonRsp;
		}
		if (partWatchPojo!=null){
			commonRsp.setMsg("已经关注");
			commonRsp.setRetcode(2001);
			return commonRsp;
		}

	    try{
	    	 Integer tag=watchService.doWatch(dbfans);
	    }catch(Exception e){
			commonRsp.setMsg("关注失败");
			commonRsp.setRetcode(2001);
			return commonRsp;
	    }	
	    commonRsp.setMsg("关注成功");
	    commonRsp.setRetcode(2000);
		return commonRsp;
	}
    //关注人列表在peoplelistcontroller中
    //取消关注一个人
	@RequestMapping(value="/cancelwatch",method=RequestMethod.POST)
	public @ResponseBody CommonRsp getWatchList(@RequestBody CancelWatchParam cancelParam ){ 
		CommonRsp commonRsp=new CommonRsp();
		String token=cancelParam.getToken();
		String userPassword=token.substring(0,32); //token是password和userID拼接成的。
		String useridStr=token.substring(32);		
		Long userid=Long.valueOf(useridStr).longValue();		
		String passwdTrue=rlService.selectMD5Password(Long.valueOf(userid));
		//System.out.println("lll");
		if(!userPassword.equals(passwdTrue)){
			commonRsp.setRetcode(2001);
			commonRsp.setMsg("用户验证失败，非法");
			return commonRsp;
		}

		Long canceluserid=cancelParam.getBeCancel();
		//查询关注 和被关注列表
		//根据这组数据，选出id列表
		//用id列表到user表查询数据
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("userIDFollowe", userid);
		map.put("userIDBeFocus",canceluserid); //
		try{
			Integer returnTag=relationService.unWatchManService(map);

		}catch(Exception e){
			commonRsp.setMsg("取消关注失败");
			commonRsp.setRetcode(2001);
			return commonRsp;
		}
		commonRsp.setMsg("取消关注成功");
		commonRsp.setRetcode(2000);
		return commonRsp;
	}
	 //这里是查询是否关注一个人，传入两个人的userid，进行查询。
	@RequestMapping(value="/querywatchif",method=RequestMethod.POST)
	public @ResponseBody QueryIfWatchRsp queryWatchIf(@RequestBody QueryWatchIfParam param ){

		QueryIfWatchRsp commonRsp=new QueryIfWatchRsp();
		Long taUserid=param.getTaUserid();
		Long myUserid=param.getMyUserid();

		if (taUserid.equals(null) || myUserid.equals(null)){
			commonRsp.setMsg("参数为空");
			commonRsp.setRetcode(2001);
			commonRsp.setPaytag((byte) 3); //3是随便设置的，前端用不到
			commonRsp.setDoublefans((byte)0); //0代表没有关注
		}
		Map<String,Object> map= new HashMap<>();
		map.put("taUserid",taUserid);
		map.put("myUserid",myUserid);
		PartWatchPojo partWatchPojo ;
		//System.out.print("收到请求");
		//写service
		try{
			partWatchPojo=watchService.watchMayNot(map);
		}catch (Exception e){
			commonRsp.setMsg("出错了");
			commonRsp.setRetcode(2001);
			return  commonRsp;
		}
		if (partWatchPojo!=null){
			commonRsp.setMsg("成功");
			commonRsp.setRetcode(2000); //返回这个代表成功
			commonRsp.setDoublefans(partWatchPojo.getDoublefans());
			commonRsp.setPaytag(partWatchPojo.getPaytag());
			return  commonRsp;
		}else { //数据为空
			commonRsp.setMsg("数据为空");
			commonRsp.setRetcode(2001); //返回这个代表成功
			return  commonRsp;
		}

	}


}














