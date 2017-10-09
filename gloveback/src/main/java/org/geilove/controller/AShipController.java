package org.geilove.controller;

/**
 * 这里是专门为同舟共济App开发的接口.
 * http请求路径必须是包含 /ship
 */
import org.geilove.pojo.Public;
import org.geilove.pojo.RedMoney;
import org.geilove.pojo.Statistics;
import org.geilove.requestParam.CashParam;
import org.geilove.response.AwardListRsp;
import org.geilove.response.CashResponse;
import org.geilove.response.PublicListRsp;
import org.geilove.response.StatisticsRsp;
import org.geilove.service.AshipService;
import org.geilove.util.ServerIP;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/ship")
public class AShipController {

    @Resource
    private AshipService ashipService;


    @RequestMapping(value="/getStatistics.do",method=RequestMethod.GET)
    @ResponseBody
    public StatisticsRsp getStatistics(){
        StatisticsRsp statisticsRsp=new StatisticsRsp();
        Statistics statistics=null;
        try{
            statistics=ashipService.getStatistics();
        }catch (Exception e){
            statisticsRsp.setMsg("请求统计表数据出现异常");
            statisticsRsp.setRetcode(2001);
        }
        if (statistics==null){
            statisticsRsp.setRetcode(2001);
            statisticsRsp.setMsg("数据为空，失败");
        }else {
            statisticsRsp.setRetcode(2000);
            statisticsRsp.setMsg("获取数据成功");
            statisticsRsp.setStatistics(statistics);
        }
        return statisticsRsp;
    }
    //获取公示列表
    @RequestMapping(value="/getAwardList.do",method=RequestMethod.GET)
    @ResponseBody
    public Object  getAwardList(){
        AwardListRsp awardListRsp=new AwardListRsp();
        List<RedMoney> redMoneyList=null;
        Map<String,Object> map=new HashMap<>();
        map.put("page",0);
        map.put("pageSize",10);
        try{
           // redMoneyList=ashipService.getPublicList(map);
            if (redMoneyList==null){
                awardListRsp.setRetcode(2001);
                awardListRsp.setMsg("您还没有奖励，邀请吧");
                return  awardListRsp;
            }
        }catch (Exception e){
            awardListRsp.setRetcode(2001);
            awardListRsp.setMsg("抛出异常");
            return  awardListRsp;
        }
        awardListRsp.setLp(redMoneyList);
        awardListRsp.setRetcode(2000);
        awardListRsp.setMsg("成功");
        return awardListRsp;
    }


}
