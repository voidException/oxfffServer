package org.geilove.service;

import org.geilove.pojo.Tweet;

import java.util.List;
import java.util.Map;

/**
 * Created by mfhj-dz-001-424 on 17/2/28.
 */
public interface TweetService {

    public List<Tweet> getTweetListbyUseriDs(Map<String,Object> maps); //根据一组userID和最晚发布时间，获取一组推文

    public List<Tweet> getTweetByOtherUserID(Map<String,Object> maps);

    public List<Tweet> getMyselfPublishedTweetByMyiD(Map<String,Object> maps);

    /* 查看自己发布的救助 推文userUUIDTweet tobeuseone */
    public List<Tweet> getUserSelfHelpTweetsService(Map<String,Object> map);

    /* 查看审核通过的需要帮助的推文 */
    public List<Tweet> getHelpTweetListService(Map<String,Object> map);


}
