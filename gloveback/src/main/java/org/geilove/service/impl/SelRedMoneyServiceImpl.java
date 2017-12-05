package org.geilove.service.impl;

import org.geilove.dao.RedMoneyMapper;
import org.geilove.pojo.RedMoney;
import org.geilove.service.SelRedMoneyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

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
    public RedMoney selectByRedUUID(String redmoneyuuid){
        RedMoney redMoney=redMoneyMapper.selectByRedUUID(redmoneyuuid);
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

        Calendar calendar   =  Calendar.getInstance();
        calendar.add(Calendar.DATE, 30);
        redMoney1.setLosefuncdate(calendar.getTime());

        try{
            redMoneyMapper.insert(redMoney1);
            return;
        }catch (Exception e){

         //记录下日志
            return;
        }
    }
    public  static void main(String[]arg){

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //当前日期
        System.out.println("增加天数以后的日期：" + format.format(new Date()));

        Calendar calendar   =  Calendar.getInstance();
        calendar.add(Calendar.DATE, 30);

        //增加后的日期
        Date d = calendar.getTime();
        String enddate = format.format(d);
        System.out.println("增加天数以后的日期：" + enddate);

    }
}
















