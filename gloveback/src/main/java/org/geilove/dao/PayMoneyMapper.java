package org.geilove.dao;

import org.geilove.pojo.PayMoney;

import java.util.List;
import java.util.Map;

public interface PayMoneyMapper {
    int deleteByPrimaryKey(Long paymoneyid);

    int insert(PayMoney record);

    int insertSelective(PayMoney record);

    PayMoney selectByPrimaryKey(Long paymoneyid);

    int updateByPrimaryKeySelective(PayMoney record);

    int updateByPrimaryKey(PayMoney record);

    PayMoney selectByMapKeys(Map<String,String> map);

    List<PayMoney> getPaymoneyList(String  useruuid);

    List<PayMoney> getPaymoneys(Map<String,Object> map);//根据useruuid helptype选择出

}