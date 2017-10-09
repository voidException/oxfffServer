package org.geilove.service.impl;
/*
 * 
*/
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import  org.geilove.pojo.Tweet;
import  org.geilove.service.MainService;
import  org.geilove.sqlpojo.OtherPartHelpPojo;
import  org.geilove.pojo.Tweet;
import  org.geilove.dao.TweetMapper;
import  org.geilove.dao.UserMapper;
import  org.geilove.dao.DoubleFansMapper;
import  org.springframework.stereotype.Service;
import org.geilove.vo.IwatchPeopleVo;
import org.geilove.vo.PeopleListVo;
import  org.geilove.vo.WeiBo;
import org.geilove.vo.PeopleListVo;
@Service("tweetListService")
public class MainServiceImpl implements MainService {
	
	@Resource 
	private TweetMapper tweetMapper;
	
	@Resource
	private UserMapper userMapper;
	
	@Resource
	private DoubleFansMapper doubleFansMapper;
         /* 先根据用户id，按照时间标签获取tweet，然后遍历tweet，如果是转发的，就请求数据库，
	      * 获取原tweet，合成一块返回,这里还要加上@带来的超链接。
	     */

	@Override
	public List<Tweet> getZhuanfaTweetList(Map<String,Object> map){
		List<Tweet> tweets=tweetMapper.selectBySourceMsgIDKey(map);  //根据推文的id，去得它的转发列表	
		return tweets;	
	}
	
	@Override
	public List<Tweet> getTweetByDiffIDs(List<Long> lst){
		List<Tweet> tweetsByIDs=tweetMapper.findByIdsMap(lst);
		return tweetsByIDs;
	}
	
	@Override
	public Integer updateTweetByKeySelective(Tweet record){
		Integer updateTag=tweetMapper.updateByPrimaryKeySelective(record);
		return updateTag;
	} 
	
	@Override
	public List<OtherPartHelpPojo> getProfileByUserIDs(List<Long> useridList){
		List<OtherPartHelpPojo> userPhotos=new ArrayList<OtherPartHelpPojo>();
		userPhotos=userMapper.selectUserPartProfile(useridList); //通过用户表获取列表
		return userPhotos; 
	}
	
	@Override
	public Integer addTweet(Tweet tweet){    //发布一条推文
		int  response=tweetMapper.insert(tweet);
		return response;
	}
	@Override
	public List<Long> getWatcherIds(Map<String,Object> map){ //获取用户所关注的id
		List<Long> lsids=new ArrayList<Long>();
		lsids=doubleFansMapper.getWatchids(map);
		return lsids;
	}
	@Override
	public List<PeopleListVo> getWatcherIdsListMen(Map<String,Object> map){//获取我关注的人的ids和关注的时间
		List<PeopleListVo> lsids;
		lsids=doubleFansMapper.getWatchidsListMenLoadMore(map);
		return lsids;
	} 
	@Override
	public List<IwatchPeopleVo> getMyFansids(Map<String,Object> map){ //获取我的粉丝的ids和关注我的时间
		List<IwatchPeopleVo> lsids=new ArrayList<IwatchPeopleVo>();
		lsids=doubleFansMapper.getMyFansidsLoadMore(map);
		return lsids;
	}
	
	//这个是用户主页的查询所关注人的weibo接口。 参数应该为map类型
	@Deprecated
	public List<Tweet> getWeiBoList(Map<String,Object>maps){
		List<Tweet> lsTweet=new ArrayList<Tweet>();
		lsTweet=tweetMapper.findByUserIdsLoadMore(maps);
		return lsTweet;
	}

	@Override
	public Tweet getTweetByID(Long tweetid){
		Tweet tweet=null;
		tweet=tweetMapper.selectByPrimaryKey(tweetid);
		return  tweet;
	}

	@Override
	public Tweet  getTweetByUUID(String  tweetuuid){
		Tweet tweet=null;
		tweet=tweetMapper.selectByTweetUUID(tweetuuid);
		return  tweet;
	}
	
}












