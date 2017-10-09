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
@RequestMapping("/timelinetweet")
public class TimeLineTweetController {
    @Resource
    private MainService mainService;
    @Resource
    private CashService cashService;
    @Resource
    private HelpService helpService;
    @Resource
    private ConfirmService confirmService;

    //该接口可以废弃，使用根据uuid获取一条推文 和 根据cashuuid获取资金和证实情况组合代替，分享到朋友圈时要传输这两个参数
   // @RequestMapping("/info/{useriD}/{tweetiD}/{cashiD}") //用这个也行
    @RequestMapping(value = "/info/{tweetiD}",method = RequestMethod.GET)
    public @ResponseBody  Object getInfoByTweetID(@PathVariable("tweetiD") Long tweetid){
         /*  通过推文的id查出这个人的id */
        TimeLineRsp timeLineRsp=new TimeLineRsp();
        TimeLine timeLine=new TimeLine();
        if (tweetid==null ||tweetid==0){
            timeLineRsp.setLp(null);
            timeLineRsp.setMsg("请求参数为空");
            timeLineRsp.setRetcode(2001);
            timeLineRsp.setCashCode(0);
            timeLineRsp.setCashMsg("请求参数为空");
            timeLineRsp.setTweetCode(0);
            timeLineRsp.setTweetMsg("请求参数为空");
            timeLineRsp.setConfirmCode(0);
            timeLineRsp.setConfirmMsg("请求参数为空");
            return  timeLineRsp;
        }
        Tweet tweet=null;
        try{
            tweet =mainService.getTweetByID(tweetid); //1.获取推文，稍后改为按照uuid获取
            if (null==tweet){
                timeLineRsp.setMsg("tweet为空");
                timeLineRsp.setRetcode(2001);

                timeLineRsp.setTweetMsg("tweet为空");
                timeLineRsp.setTweetCode(2001);
                return timeLineRsp; //如果这个为空，下面无法进行，必须返回
            }
            timeLine.setTweet(tweet);
            timeLineRsp.setTweetMsg("tweet数据获取成功");
            timeLineRsp.setTweetCode(2000);
        }catch (Exception e){
            timeLineRsp.setMsg("获取相应的推文出错");
            timeLineRsp.setRetcode(2001);

            timeLineRsp.setTweetMsg("获取tweet抛出异常");
            timeLineRsp.setTweetCode(2001);
            return  timeLineRsp;
        }

        Long  userIDTweet=tweet.getUseridtweet();   //2.获取推文的作者，稍后改成uuid

        /*  根据推文的作者id获取作者的昵称，头像等  */
        User user=null;
        try{
            user=helpService.getUserPartProfileByID(userIDTweet);
            if (null==user){
                timeLineRsp.setMsg("获取tweet对应的用户出错");
                timeLineRsp.setRetcode(2001);
                return   timeLineRsp;
            }
            UserPartProfile userPartProfile=new UserPartProfile();
            //加入用户的uuid
            userPartProfile.setUserid(user.getUserid());
            userPartProfile.setNickName(user.getUsernickname());
            userPartProfile.setUserPhotoUrl(user.getUserphoto());

            timeLine.setUserPartProfile(userPartProfile);

        }catch (Exception  e){
            timeLineRsp.setLp(null);
            timeLineRsp.setMsg("获取tweet对应的用户抛出异常");
            timeLineRsp.setRetcode(2001);
            return  timeLineRsp;
        }

        /*    根据cashid获取 有关资金的情况和证实人的数量   */
        // Long cashid=tweet.getTweetbackupfive();    //3.获取推文中的cashid，必须改成uuid
        // Long cashid=tweet.getCashid();
        String cashuuid=tweet.getCashuuid();//
        Cash  cash=null;
        try{
           // cash=cashService.getCashRecord(cashid); //获取救助推文对应的cash，要改为uuid
            cash=cashService.getCashRecordByUUID(cashuuid); //通过cash的uuid获取
            if (cash==null){
                timeLineRsp.setMsg("tweet对应的cash为空");
                timeLineRsp.setRetcode(2001);
                timeLineRsp.setCashMsg("tweet对应的cash为空");
                timeLineRsp.setCashCode(2001);
                return   timeLineRsp;
            }
            timeLine.setCash(cash);
            timeLineRsp.setRetcode(2000); //tweet  cash  userprofile都存在的时候是2000
            timeLineRsp.setMsg("获取数据成功");

            timeLineRsp.setCashMsg("获取tweet对应的资金信息成功");
            timeLineRsp.setCashCode(2000);
        }catch (Exception  e){
            timeLineRsp.setLp(null);
            timeLineRsp.setMsg("获取tweet对应资金信息抛出异常");
            timeLineRsp.setRetcode(2001);
            timeLineRsp.setCashMsg("获取tweet对应资金信息抛出异常");
            timeLineRsp.setCashCode(2001);

            return  timeLineRsp;
        }
        /* 证实列表*/
        List<Confirm> confirmList=new ArrayList<Confirm>();
        Map<String,Object> map=new HashMap<String,Object>();

        map.put("id",tweetid); //应该根据表中confirmbackupOne字段获取，即对应的cash的UUID
        map.put("tag", 1);
        map.put("page", 0);
        map.put("pageSize", 8);
        try{
            confirmList=confirmService.getConfirmLists(map);
            if (null==confirmList){
                timeLineRsp.setConfirmMsg("证实人为空");
                timeLineRsp.setConfirmCode(2001);
            }

            timeLine.setConfirmList(confirmList);

        }catch (Exception e){
            timeLineRsp.setConfirmMsg("获取证实人信息抛出异常");
            timeLineRsp.setConfirmCode(2001);
        }

        timeLineRsp.setLp(timeLine);
        timeLineRsp.setRetcode(2000);
        timeLineRsp.setMsg("朋友圈分享页面数据成功获取");

        return timeLineRsp;
    }

    /*这个接口是给App使用的，如果从tweet信息流中进入救助推文，那么就不必再次获取推文了，值需要获取对应的cash和证实信息*/
    @RequestMapping(value = "/getInfoByCashUUID/{cashUUID}",method = RequestMethod.GET)
    public @ResponseBody  Object getCashConfirmInfoByCashUUID(@PathVariable("cashUUID") String cashUUID){
         /*  通过推文的id查出这个人的id */
        CashConfirmRsp cashConfirmRsp=new CashConfirmRsp();
        CashConfirm cashConfirm=new CashConfirm();
        TimeLine timeLine=new TimeLine();
        if (cashUUID==null){
            cashConfirmRsp.setMsg("请求参数为空");
            return  cashConfirmRsp;
        }

        /*    根据cashuuid获取 有关资金的情况和证实人的数量   */
        Cash  cash=null;
        try{
            // cash=cashService.getCashRecord(cashid); //获取救助推文对应的cash，要改为uuid
            cash=cashService.getCashRecordByUUID(cashUUID); //通过cash的uuid获取
            if (cash==null){
                cashConfirmRsp.setMsg("tweet对应的cash为空");
                cashConfirmRsp.setRetcode(2001);
                return   cashConfirmRsp;
            }
            cashConfirm.setCash(cash);
        }catch (Exception  e){
            cashConfirmRsp.setLp(null);
            cashConfirmRsp.setMsg("根据cashuuid获取资金信息抛出异常");
            return  cashConfirmRsp;
        }
        /* 证实列表*/
        List<Confirm> confirmList=new ArrayList<Confirm>();  //保证不能是null 是[]
        Map<String,Object> map=new HashMap<String,Object>();
        //System.out.print(cashUUID);
        map.put("cashuuid",cashUUID); //应该根据表中confirmbackupOne字段获取，即对应的cash的UUID
        map.put("tag", 1);
        map.put("page", 0);
        map.put("pageSize", 8);
        try{
            confirmList=confirmService.getConfirmListsByUUID(map);
            cashConfirm.setConfirmList(confirmList);

        }catch (Exception e){
            //因为证实人
        }

        cashConfirmRsp.setLp(cashConfirm);
        cashConfirmRsp.setRetcode(2000);
        cashConfirmRsp.setMsg("朋友圈分享页面数据成功获取");

        return cashConfirmRsp;
    }


}
