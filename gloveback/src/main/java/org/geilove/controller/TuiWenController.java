package org.geilove.controller;

/**
 * Created by mfhj-dz-001-424 on 17/2/28.
 *
 * 这个是最新的推文列表。之前的作废
 */
import org.geilove.requestParam.TuiWenListParam;
import org.geilove.requestParam.TweetListParam;
import org.geilove.service.TweetService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.geilove.pojo.Tweet;
import org.geilove.service.MainService;
import org.geilove.service.RegisterLoginService;
import org.geilove.sqlpojo.OtherPartHelpPojo;
import org.geilove.requestParam.WeiboParam;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.geilove.response.*;
import org.geilove.vov.WeiBo;


@Controller
@RequestMapping("/tuiwen")
public class TuiWenController {
    @Resource
    private MainService mainService;
    @Resource
    private TweetService tweetService;
    @Resource
    private RegisterLoginService rlService;

    @RequestMapping(value="/getTweetByUseriD")
    public  @ResponseBody Object getTweetByUserID(@RequestBody TuiWenListParam param, HttpServletRequest request){
        /*@该接口用于用户查看自己关注人的推文@*/
        TuiWenListRsp  tuiWenListRsp=new TuiWenListRsp();
        List<WeiBo> lsWeiBo=new ArrayList<>();

        if (param==null){
            tuiWenListRsp.setMsg("请求参数为空");
            tuiWenListRsp.setRetcode(2001);
            return  tuiWenListRsp;
        }

        Long userID=param.getUserID();
        Integer  page =param.getPage();
        Integer  pageSize=param.getPageSize();
        String  lastUpdate=param.getLastUpdate();
        Map<String,Object> map=new HashMap<String,Object>();//存放查询的参数，传给Mybatis
        map.put("userID", userID);
        map.put("page", 0);
        map.put("pageSize", 1000);	  //默认用户只能关注1000人
		/*-1.先获取这个人关注的列表集合List<Long>，其实应该获取所有的关注的人*/

        List<Long> lsids=mainService.getWatcherIds(map);

        if(lsids.isEmpty()|| lsids==null){
            tuiWenListRsp.setData(null);
            tuiWenListRsp.setMsg("用户没有关注人");
            tuiWenListRsp.setRetcode(2001);
            return tuiWenListRsp;
        }
		/*0.然后用这个userid集合获取一组推文，*/
        Map<String,Object> maps=new HashMap<String,Object>();
        maps.put("page", page);
        maps.put("pageSize", pageSize); //定义为18条比较合适
        maps.put("lastItemstart", lastUpdate); //上次刷新的获取数据的最后一条的发布时间
        maps.put("list", lsids); //列表参数,发布推文的用户的id

        List<Tweet> tweets;
        try {
            tweets=tweetService.getTweetListbyUseriDs(maps);//首先取得推文，不带转发
            if (tweets==null){
                tuiWenListRsp.setMsg("推文为空");
                tuiWenListRsp.setRetcode(2000);
                return  tuiWenListRsp;
            }


        }catch (Exception e){
            tuiWenListRsp.setMsg("获取推文出现异常");
            tuiWenListRsp.setRetcode(2001);
            return  tuiWenListRsp;
        }
        /*1.接下来for循环，获取一个推文中被转发的推文*/

        for (int i=0;i<tweets.size();i++){
            org.geilove.vov.WeiBo weiBo=new org.geilove.vov.WeiBo();
            weiBo.setTuiwen(tweets.get(i));
            if(tweets.get(i).getTagid()==2){ //有转发的
                Tweet localTweet=new Tweet();
                 try {
                     localTweet =mainService.getTweetByID(tweets.get(i).getSourcemsgid());
                     //System.out.println(tweets.get(i).getSourcemsgid());
                 }catch (Exception e){
                     localTweet.setMsgcontent("获取被转发推文出现异常");
                 }
                weiBo.setZhuanfaTuiwen(localTweet);
            }
            lsWeiBo.add(weiBo); //加入到WeiBo列表中
        }
        tuiWenListRsp.setData(lsWeiBo);
        tuiWenListRsp.setMsg("获取数据成功");
        tuiWenListRsp.setRetcode(2000);
        return tuiWenListRsp;
    }


    @RequestMapping(value="/getTweetByOtherUseriD.do")
    public  @ResponseBody Object getTweetByOtherUserID(@RequestBody TuiWenListParam param, HttpServletRequest request){
        /*@ 该接口用于从别人的主页，查看对方的推文@*/
        TuiWenListRsp  tuiWenListRsp=new TuiWenListRsp();
        List<WeiBo> lsWeiBo=new ArrayList<>();

        if (param==null){
            tuiWenListRsp.setMsg("请求参数为空");
            tuiWenListRsp.setRetcode(2001);
            return  tuiWenListRsp;
        }

        Long userID=param.getUserID();
        Integer  page =param.getPage();
        Integer  pageSize=param.getPageSize();
        String  lastUpdate=param.getLastUpdate();
        Map<String,Object> map=new HashMap<String,Object>();//存放查询的参数，传给Mybatis
        map.put("userID", userID);
        map.put("page", page);
        map.put("pageSize", 6);
        map.put("lastItemstart", lastUpdate); //上次刷新的获取数据的最后一条的发布时间

        List<Tweet> tweets;
        try {
            tweets=tweetService.getTweetByOtherUserID(map);//首先取得推文，不带转发
            if (tweets==null){
                tuiWenListRsp.setMsg("推文为空");
                tuiWenListRsp.setRetcode(2000);
                return  tuiWenListRsp;
            }

        }catch (Exception e){
            tuiWenListRsp.setMsg("获取推文出现异常");
            tuiWenListRsp.setRetcode(2001);
            return  tuiWenListRsp;
        }

        for (int i=0;i<tweets.size();i++){
            org.geilove.vov.WeiBo weiBo=new WeiBo();
            weiBo.setTuiwen(tweets.get(i));
            if(tweets.get(i).getTagid()==2){ //有转发的
                Tweet localTweet=new Tweet();
                try {
                    localTweet =mainService.getTweetByID(tweets.get(i).getSourcemsgid());
                }catch (Exception e){
                    localTweet.setMsgcontent("获取被转发推文出现异常");
                }
                weiBo.setZhuanfaTuiwen(localTweet);
            }
            lsWeiBo.add(weiBo); //加入到WeiBo列表中
        }
        tuiWenListRsp.setData(lsWeiBo);
        tuiWenListRsp.setMsg("获取数据成功");
        tuiWenListRsp.setRetcode(2000);

        return tuiWenListRsp;
    }

    @RequestMapping(value="/getMyselfPublishedTweetByMyiD")
    public  @ResponseBody Object getMyselfPublishedTweetByMyiD(@RequestBody TuiWenListParam param, HttpServletRequest request){
        /*@ 该接口用于查看我自己发布的推文@*/
        //System.out.println("getMyselfPublishedTweetByMyiD");
        TuiWenListRsp  tuiWenListRsp=new TuiWenListRsp();
        List<WeiBo> lsWeiBo=new ArrayList<>();

        if (param==null){
            tuiWenListRsp.setMsg("请求参数为空");
            tuiWenListRsp.setRetcode(2001);
            return  tuiWenListRsp;
        }

        Long userID=param.getUserID();
        Integer  page =param.getPage();
        Integer  pageSize=param.getPageSize();
        String  lastUpdate=param.getLastUpdate();
        Map<String,Object> map=new HashMap<String,Object>();//存放查询的参数，传给Mybatis
        map.put("userID", userID);
        map.put("page", page);
        map.put("pageSize", 5);
        map.put("lastItemstart", lastUpdate); //上次刷新的获取数据的最后一条的发布时间

        List<Tweet> tweets;
        try {
            tweets=tweetService.getTweetByOtherUserID(map);//首先取得推文，不带转发
            if (tweets==null||tweets.size()==0){
                tuiWenListRsp.setMsg("推文为空");
                tuiWenListRsp.setRetcode(2001);
                return  tuiWenListRsp;
            }

        }catch (Exception e){
            tuiWenListRsp.setMsg("获取推文出现异常");
            tuiWenListRsp.setRetcode(2001);
            return  tuiWenListRsp;
        }

        for (int i=0;i<tweets.size();i++){
            org.geilove.vov.WeiBo weiBo=new WeiBo();
            weiBo.setTuiwen(tweets.get(i));
            if(tweets.get(i).getTagid()==2){ //有转发的
                Tweet localTweet=new Tweet();
                try {
                    localTweet =mainService.getTweetByID(tweets.get(i).getSourcemsgid());
                }catch (Exception e){
                    localTweet.setMsgcontent("获取被转发推文出现异常");
                }
                weiBo.setZhuanfaTuiwen(localTweet);
            }
            lsWeiBo.add(weiBo); //加入到WeiBo列表中
        }
        tuiWenListRsp.setData(lsWeiBo);
        tuiWenListRsp.setMsg("获取数据成功");
        tuiWenListRsp.setRetcode(2000);

        return tuiWenListRsp;
    }


}


























