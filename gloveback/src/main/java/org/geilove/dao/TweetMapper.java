package org.geilove.dao;

import java.util.List;
import java.util.Map;
import org.geilove.pojo.Tweet;
import org.geilove.vo.WeiBo;

public interface TweetMapper {
    int deleteByPrimaryKey(Long tweetid); //根据主键删除推文

    int insert(Tweet record);    //发布 和转发推文的接口

    int insertSelective(Tweet record);

    Tweet selectByPrimaryKey(Long tweetid);

    Tweet selectByTweetUUID(String  tweetuuid);
    
    List< Tweet> selectBySourceMsgIDKey(Map<String, Object> map); //根据推文的id，取得它的转发列表
        
    List<Tweet> findByIdsMap(List<Long> lst); //自定义方法，根据给定的一组不同的tweet的id查找出这组tweet，放在列表中

    int updateByPrimaryKeySelective(Tweet record);

    int updateByPrimaryKey(Tweet record);

    /*查看自己发布的微博*/
    List<Tweet> selectByMainKeyLoadMore(Map<String, Object> map); //自定义方法,根据用户的id，取得一组推文列表,loadMore使用

    /*查看别人发布的推文*/
    List<Tweet> selectByMainKeyLoadMoreSHe(Map<String, Object> map);

    /*用户查看自己主页的推文，自己收听的*/
    List<Tweet> findByUserIdsLoadMore(Map<String,Object>maps); //根据获得的用户的一组userids 取出一组微博,供加载更多用;

    /* 查看自己发布的救助 推文userUUIDTweet tobeuseone */
    List<Tweet> getUserSelfHelpTweets(Map<String,Object> map);


    /* 查看审核通过的需要帮助的推文 */
    List<Tweet> getHelpTweetList(Map<String,Object> map);
    
    
}