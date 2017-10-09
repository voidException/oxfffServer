package org.geilove.service.impl;

import org.geilove.dao.WechatLogMapper;
import org.geilove.pojo.WechatLog;
import org.geilove.service.WechatLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by aihaitao on 29/4/2017.
 */
@Service("WechatLogService")
public class WechatLogServiceImpl implements WechatLogService {
    @Resource
    private WechatLogMapper wechatLogMapper;

    public int addlog(WechatLog wechatLog){
         int result;
         try{
             result=wechatLogMapper.insert(wechatLog);
             return  result;
         }catch (Exception e){
             result=10;  //10代表异常
             return  result;
         }
    }

}
