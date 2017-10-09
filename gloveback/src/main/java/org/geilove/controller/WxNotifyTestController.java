package org.geilove.controller;

import org.geilove.pojo.MoneySource;
import org.geilove.response.CommonRsp;
import org.geilove.service.MoneySourceService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 这个类用来测试支付回调的
 */
@RequestMapping(value="/wxNotify")
@Controller
public class WxNotifyTestController {

    @Resource
    private MoneySourceService moneySourceService;

    @RequestMapping(value="/test",method= RequestMethod.GET)
    @ResponseBody
    public CommonRsp wxNotify(HttpServletRequest request, HttpServletResponse response){
        MoneySource moneySource=new MoneySource();

        moneySource.setMoneynum(10); //test 这个是测试对象，
        moneySource.setAttach("a1e0c875-379a-40ad-b3d3-a17534ae92c41654727c-40f9-42ba-b8a2-d492e2a1d0ed");

        NotifyThread  notifyThread=new NotifyThread(moneySource);
        notifyThread.start();

        //System.out.print("执行到这里");
        CommonRsp commonRsp=new CommonRsp();
        commonRsp.setMsg("我要返回了");
        return commonRsp;
    }

     class NotifyThread  extends  Thread {

        public MoneySource  moneySource;

        public NotifyThread(MoneySource  moneySource){  //构造方法

            this.moneySource=moneySource;
        }
        public  void  run(){
            moneySourceService.wxNotify(this.moneySource);
            System.out.print(moneySource.getMoneynum());
        }
    }


}
