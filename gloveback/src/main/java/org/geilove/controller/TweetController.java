/*
 * @author aihaitao
 * 这个用来显示首页，但不同于个人主页，它没有粉丝数推文数量等信息。
*/
package org.geilove.controller;
import org.geilove.service.TweetService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.geilove.pojo.Tweet;
import org.geilove.requestParam.DeleteTweetByKeyParam;
import org.geilove.requestParam.PublishTweetParam;
import org.geilove.service.MainService;
import org.geilove.service.RegisterLoginService;
import org.geilove.sqlpojo.OtherPartHelpPojo;
import org.geilove.requestParam.TweetListParam;
import org.geilove.requestParam.WeiBoListParam;
import org.geilove.requestParam.ZhuangfaListParam;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.geilove.response.*;
import org.geilove.vo.WeiBo;
import org.geilove.vo.Tuiwen;
/*
 * 这个用来提供有关推文的操作
*/
@Controller
@RequestMapping("/weibos")
public class TweetController {
	@Resource
	private MainService mainService;
	@Resource
	private RegisterLoginService rlService;
	@Resource
	private TweetService tweetService;

	/*这个是删除一条推文，因此需要用户名、密码、推文的ID，要授权才能删除，只做逻辑删除*/
	@RequestMapping(value="/deleteTweetByID")
	public @ResponseBody CommonRsp deleteTweetByID(@RequestBody DeleteTweetByKeyParam delTweetParam){
		//这里先检查用户名和密码是否存在并匹配
		// 然后调用Service，将删除标志更新为2
		CommonRsp commonRsp=new CommonRsp();
		String token=delTweetParam.getToken();
		if(token.length()<33){
			commonRsp.setMsg("凭证不合法");
			commonRsp.setRetcode(2001);
			return commonRsp;
		}
		String userPassword=token.substring(0,32); //token是password和userID拼接成的。
		String useridStr=token.substring(32); //取得userid部分		
		Long userid=Long.valueOf(useridStr).longValue();  //转换成long类型
		//Long userid=Long.parseLong(useridstr);
		String passwdTrue=rlService.selectMD5Password(Long.valueOf(userid));
		//System.out.println(passwdTrue);
		if(!userPassword.equals(passwdTrue)){
			commonRsp.setRetcode(2001);
			commonRsp.setMsg("用户密码不对，非法");
			return commonRsp;
		}
		//这里应该查询下，是不是有这条信息，以及信息是不是用户的
		Tweet tweet=new Tweet();
		tweet.setDeletetag((byte) 2);
		tweet.setMsgcontent("此信息已被用户删除");
		tweet.setTweetid(delTweetParam.getTweetid());//根据这个推文的ID更新
		Integer updateTag=0;
		try{
			 updateTag=mainService.updateTweetByKeySelective(tweet);
		}catch(Exception e){
			System.out.println(e);
		}

		if(updateTag==1){
			commonRsp.setMsg("删除推文成功");
			commonRsp.setRetcode(2000);
		}else{
			commonRsp.setMsg("删除失败");
			commonRsp.setRetcode(2001);
		}
		return commonRsp;		
	}

	@RequestMapping("/zhuanfaTweet")
	public @ResponseBody CommonRsp addComment(HttpServletRequest request){
		CommonRsp commonRsp=new CommonRsp();
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
			commonRsp.setMsg("身份验证出现异常");
			return commonRsp;
		}

		String tweetContent=request.getParameter("content");//转发时输入的内容
		String sourceMsgID=request.getParameter("sourceMsgID");
		Tweet tweet=new Tweet();
		tweet.setUseridtweet(userid); //转发推文人的id
		tweet.setSourcemsgid(Long.valueOf(sourceMsgID).longValue());
		tweet.setTagid((byte)2); //2代表是转发的
		tweet.setMsgcontent(tweetContent); //转发时输入的内容
		tweet.setPublishtime(new Date());		
		tweet.setTopic(new Long(1)); //话题，默认是1
		tweet.setPublicsee((byte)1);
		tweet.setDeletetag((byte)1);
		//这里插入一条推文，待实现
		try{
			int insertCode=mainService.addTweet(tweet);
			if(insertCode!=1){
				commonRsp.setMsg("发布推文出错了");
				commonRsp.setRetcode(2001);
			}
		}catch(Exception e){	
			commonRsp.setMsg("发布推文抛出异常");
			commonRsp.setRetcode(2001);
			return commonRsp;
		}
			
		commonRsp.setRetcode(2000);
		commonRsp.setMsg("转发成功了");
		return commonRsp;
	}

	//查看自己发布的求助信息的tweet,
	@RequestMapping(value = "/{userUUID}/helpselflist.do",method =RequestMethod.GET)
	public @ResponseBody TweetsRsp helpSelfList( @PathVariable("userUUID") String userUUID,HttpServletRequest request){
		TweetsRsp tweetRsp=new TweetsRsp();
		String userUUIDTweet=userUUID;
		Map<String,Object> map=new HashMap<String,Object>();

		map.put("userUUIDTweet",userUUIDTweet);
		//map.put("userIDTweet",1);
		map.put("helpif","yes");
		map.put("page",0);
		map.put("pageSize",2);

		List<Tweet> listTweet;
		try {
			listTweet=tweetService.getUserSelfHelpTweetsService(map);
			if (listTweet==null || listTweet.isEmpty()){
				tweetRsp.setLp(null);
				tweetRsp.setMsg("没有项目");
				tweetRsp.setRetcode(2001);
				return  tweetRsp;
			}

		}catch (Exception e){
			tweetRsp.setLp(null);
			tweetRsp.setMsg("服务异常");
			tweetRsp.setRetcode(2001);
			return  tweetRsp;
		}

		tweetRsp.setLp(listTweet);
		tweetRsp.setMsg("成功");
		tweetRsp.setRetcode(2000);
		return  tweetRsp;
	}

	//查看所有求助推文

	@RequestMapping(value="/helpTweetlist.do",method= RequestMethod.GET)
	@ResponseBody
	public TweetsRsp helpTweetlist(HttpServletRequest request) throws Exception{

		TweetsRsp tuiwenListRsp=new TweetsRsp();
		Map<String,Object> map=new HashMap<String,Object>();

		String lastUpdate=request.getParameter("lastUpdate");
		String helpType=request.getParameter("helpType");
		map.put("helpType",helpType); //类型
		map.put("helpif","yes");
		map.put("lastUpdate",lastUpdate); //时间
		map.put("page",0);
		map.put("pageSize",10);


		List<Tweet> listTweet;
		try {
			listTweet=tweetService.getHelpTweetListService(map);
			if (listTweet==null){
				tuiwenListRsp.setLp(null);
				tuiwenListRsp.setMsg("没有项目");
				tuiwenListRsp.setRetcode(2001);
				return  tuiwenListRsp;
			}

		}catch (Exception e){
			tuiwenListRsp.setLp(null);
			tuiwenListRsp.setMsg("服务异常");
			tuiwenListRsp.setRetcode(2001);
			return  tuiwenListRsp;
		}

		tuiwenListRsp.setLp(listTweet);
		tuiwenListRsp.setMsg("成功获取数据");
		tuiwenListRsp.setRetcode(2000);
		return  tuiwenListRsp;
	}




}
























