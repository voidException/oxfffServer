package org.geilove.service.impl;

import org.geilove.dao.RedMoneyMapper;
import org.geilove.pojo.RedMoney;
import org.geilove.service.SelRedMoneyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class SelRedMoneyServiceImpl implements SelRedMoneyService {

    @Resource
    public  RedMoneyMapper redMoneyMapper;

    @Override
    public List<RedMoney> selAllRedMoney(HashMap<String,Object> hashMap) {
        List<RedMoney> redMoneyList=redMoneyMapper.selectAllUserRedMoney(hashMap);
        return redMoneyList;
    }

    /**
     * 通过id查询对应的红包信息
     * @param redmoneyid
     * @return
     */
    @Override
    public RedMoney selectByPrimaryKey(Long redmoneyid) {
        RedMoney redMoney=redMoneyMapper.selectByPrimaryKey(redmoneyid);
        return  redMoney;
    }

    @Override
    public int updateByPrimaryKeySelective(RedMoney redMoney) {
        return redMoneyMapper.updateByPrimaryKeySelective(redMoney);
    }

    @Override
    public int insertRedMoneyByShare(RedMoney redMoney) {
        return redMoneyMapper.insertSelective(redMoney);
    }

    @Override
    public void bornRedMony( String  shareUserUUID, String  newUserUUID){

        //1.先查询数据库红包表，是否存在此人加入时生成的红包
        try{
            RedMoney  redMoney=redMoneyMapper.selectByUserUUIDClick(newUserUUID);
            if ( redMoney!=null){
                return;
            }
        }catch (Exception e){
            return;
        }


        RedMoney  redMoney1=new RedMoney();
        redMoney1.setRedmoneyuuid(UUID.randomUUID().toString());
        redMoney1.setRedmoney(new Long(5));
        redMoney1.setRedmoneydate(new Date());
        redMoney1.setUseruuid(shareUserUUID);
        redMoney1.setUseruuidclick(newUserUUID);
        redMoney1.setRedmoneystate("unactive"); //未激活

        try{
            redMoneyMapper.insert(redMoney1);
            return;
        }catch (Exception e){

         //记录下日志
            return;
        }

    }
}
















