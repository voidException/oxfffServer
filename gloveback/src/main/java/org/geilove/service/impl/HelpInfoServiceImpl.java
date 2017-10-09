package org.geilove.service.impl;

import org.geilove.dao.HelpInfoMapper;
import org.geilove.pojo.HelpInfo;
import org.geilove.service.HelpInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by aihaitao on 14/5/2017.
 */

@Service("HelpInfoServiceImpl")
public class HelpInfoServiceImpl implements HelpInfoService {

    @Resource
    HelpInfoMapper helpInfoMapper;

    public HelpInfo selectRecord(String  useruuid){
        HelpInfo helpInfo=helpInfoMapper.selectByUserUUID(useruuid); //根据uuid选择
        return  helpInfo;
    }

    public  int  insertRecord(HelpInfo helpInfo){
        int helpInfoTag=helpInfoMapper.insertSelective(helpInfo);
        return  helpInfoTag;
    }

    public  int  updateRecord(HelpInfo helpInfo){
        int helpInfoTag=helpInfoMapper.updateByPrimaryKeySelective(helpInfo);
        return  helpInfoTag;
    }


}
