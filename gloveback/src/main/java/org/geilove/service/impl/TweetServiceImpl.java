package org.geilove.service.impl;

import org.geilove.dao.DoubleFansMapper;
import org.geilove.dao.TweetMapper;
import org.geilove.dao.UserMapper;
import org.geilove.pojo.Tweet;
import org.geilove.service.TweetService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by mfhj-dz-001-424 on 17/2/28.
 */

@Service("tweetService")
public class TweetServiceImpl implements TweetService {
    @Resource
    private TweetMapper tweetMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private DoubleFansMapper doubleFansMapper;

    public List<Tweet> getTweetListbyUseriDs(Map<String,Object> map){////根据一组userID和最晚发布时间，获取一组推文
        List<Tweet> lsTweet=new ArrayList<Tweet>();

        lsTweet=tweetMapper.findByUserIdsLoadMore(map); //查看别人发布的推文
        return lsTweet;
    }

    public List<Tweet> getTweetByOtherUserID(Map<String,Object> map){
        List<Tweet> lsTweet=new ArrayList<Tweet>();
        lsTweet=tweetMapper.selectByMainKeyLoadMoreSHe(map);
        return lsTweet;
    }

    public List<Tweet> getMyselfPublishedTweetByMyiD(Map<String,Object> map){ //查看我自己发布的推文
        List<Tweet> lsTweet=new ArrayList<Tweet>();
        lsTweet=tweetMapper.selectByMainKeyLoadMore(map);
        return lsTweet;
    }

    /* 查看自己发布的救助 推文userUUIDTweet tobeuseone */
    public List<Tweet> getUserSelfHelpTweetsService(Map<String,Object> map){
        List<Tweet> lsTweet=tweetMapper.getUserSelfHelpTweets(map);
        return  lsTweet;
    }

    /* 查看审核通过的需要帮助的推文 */
    public List<Tweet> getHelpTweetListService(Map<String,Object> map){
        List<Tweet> lsTweet=tweetMapper.getHelpTweetList(map);
        return  lsTweet;
    }


}
