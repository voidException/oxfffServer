package org.geilove.controller;

/**
 * Created by mfhj-dz-001-424 on 17/1/22.
 *  分享到微信圈所需要的主要的数据
 *  这个接口基本上是根据tweetid 获取了所有的有关救助信息的数据，也包括tweet本身，
 *  还应该提供一个仅仅根据tweetdid获取所有救助信息数据的接口，这个是由于移动端跟web端不同造成的。
 *  或许应该吧iD换成uuid
 */
import javax.annotation.Resource;

import org.geilove.pojo.Cash;
import org.geilove.pojo.Confirm;
import org.geilove.pojo.Tweet;
import org.geilove.pojo.User;
import org.geilove.response.CashConfirmRsp;
import org.geilove.response.TimeLineRsp;
import org.geilove.service.*;
import org.geilove.vo.CashConfirm;
import org.geilove.vo.TimeLine;
import org.geilove.vo.UserPartProfile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

@Controller
@RequestMapping("/wechatShare")
public class WechatShareController {
    @Resource
    private MainService mainService;
    @Resource
    private CashService cashService;
    @Resource
    private HelpService helpService;
    @Resource
    private ConfirmService confirmService;


    @RequestMapping(value = "/{userUUID}/{tweetUUID}/{cashUUID}",method = RequestMethod.GET)
    public @ResponseBody  Object wechatShareJSP(@PathVariable("userUUID") String userUUID,@PathVariable("tweetUUID") String tweetUUID,@PathVariable("cashUUID") String cashUUID){
         /*  通过推文的id查出这个人的id */
        TimeLineRsp timeLineRsp=new TimeLineRsp();
        TimeLine timeLine=new TimeLine();
        if (userUUID==null ||"".equals(userUUID) || tweetUUID==null ||"".equals(tweetUUID) || cashUUID==null ||"".equals(cashUUID)){
            timeLineRsp.setLp(null);
            timeLineRsp.setMsg("请求参数为空");
            timeLineRsp.setRetcode(2001);
            timeLineRsp.setCashCode(2001);
            timeLineRsp.setCashMsg("请求参数为空");
            timeLineRsp.setTweetCode(2001);
            timeLineRsp.setTweetMsg("请求参数为空");
            timeLineRsp.setConfirmCode(2001);
            timeLineRsp.setConfirmMsg("请求参数为空");
            return  timeLineRsp;
        }
        Tweet tweet=null;
        try{
            tweet =mainService.getTweetByUUID(tweetUUID);
            if (null==tweet || !(tweet  instanceof Tweet)){
                timeLineRsp.setMsg("tweet没有正确获取到");
                timeLineRsp.setRetcode(2001);
                timeLineRsp.setTweetMsg("tweet为空");
                timeLineRsp.setTweetCode(2001);
                timeLineRsp.setCashCode(2001);
                timeLineRsp.setCashMsg("无法进行");
                timeLineRsp.setConfirmCode(2001);
                timeLineRsp.setConfirmMsg("无法进行");
                return timeLineRsp;
            }
            timeLine.setTweet(tweet); //重要
            timeLineRsp.setTweetMsg("tweet数据获取成功");
            timeLineRsp.setTweetCode(2000);
        }catch (Exception e){
            timeLineRsp.setMsg("获取相应的推文出错");
            timeLineRsp.setRetcode(2001);

            timeLineRsp.setTweetMsg("获取tweet抛出异常");
            timeLineRsp.setTweetCode(2001);
            timeLineRsp.setCashCode(2001);
            timeLineRsp.setCashMsg("无法进行");
            timeLineRsp.setConfirmCode(2001);
            timeLineRsp.setConfirmMsg("无法进行");
            return timeLineRsp;
        }
        String cashuuid=tweet.getCashuuid();
        Cash  cash=null;
        try{

            cash=cashService.getCashRecordByUUID(cashuuid); //通过cash的uuid获取
            if (cash==null ||  !(cash  instanceof Cash)){
                timeLineRsp.setMsg("tweet对应的cash为空");
                timeLineRsp.setRetcode(2001);
                timeLineRsp.setCashMsg("tweet对应的cash为空");
                timeLineRsp.setCashCode(2001);
                timeLineRsp.setTweetMsg("推文获取成功");
                timeLineRsp.setTweetCode(2000);
                timeLineRsp.setConfirmCode(2001);
                timeLineRsp.setConfirmMsg("无法进行");
                return   timeLineRsp;
            }
            timeLine.setCash(cash);  //重要
            timeLineRsp.setRetcode(2000); //tweet cash 都存在的时候是2000
            timeLineRsp.setMsg("获取数据成功");
            timeLineRsp.setCashMsg("获取tweet对应的资金信息成功");
            timeLineRsp.setCashCode(2000);

        }catch (Exception  e){

            timeLineRsp.setMsg("获取tweet对应资金信息抛出异常");
            timeLineRsp.setRetcode(2001);
            timeLineRsp.setCashMsg("获取tweet对应资金信息抛出异常");
            timeLineRsp.setCashCode(2001);
            timeLineRsp.setConfirmCode(2001);
            timeLineRsp.setConfirmMsg("无法进行");
            return  timeLineRsp;
        }
        /* 证实列表*/
        /**
        List<Confirm> confirmList=new ArrayList<Confirm>();
        Map<String,Object> map=new HashMap<String,Object>();
        Long tweetid=tweet.getTweetid();
        map.put("id",tweetid); //应该根据表中confirmbackupOne字段获取，即对应的cash的UUID
        map.put("tag", 1);
        map.put("page", 0);
        map.put("pageSize", 8);
        try{
            confirmList=confirmService.getConfirmLists(map);
            if (null==confirmList){
                timeLineRsp.setConfirmMsg("证实人为空");
                timeLineRsp.setConfirmCode(2001);
                timeLine.setConfirmList(null);
                timeLineRsp.setLp(timeLine);
                return  timeLineRsp;
            }
            timeLineRsp.setConfirmCode(2000);
            timeLineRsp.setConfirmMsg("获取证实人列表成功");
            timeLine.setConfirmList(confirmList);
            timeLineRsp.setLp(timeLine);
            return  timeLineRsp;

        }catch (Exception e){
            timeLineRsp.setConfirmMsg("获取证实人信息抛出异常");
            timeLineRsp.setConfirmCode(2001);

        }
         **/
        timeLineRsp.setLp(timeLine);
        timeLineRsp.setRetcode(2000);
        timeLineRsp.setMsg("朋友圈分享页面数据成功获取");
        return timeLineRsp;
    }

}
