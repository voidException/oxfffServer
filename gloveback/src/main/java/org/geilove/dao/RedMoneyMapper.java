package org.geilove.dao;

import org.geilove.pojo.RedMoney;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface RedMoneyMapper {
    int deleteByPrimaryKey(Long redmoneyid);

    int insert(RedMoney record);

    int insertSelective(RedMoney record);

    RedMoney selectByPrimaryKey(Long redmoneyid);

    RedMoney selectByRedUUID(String redmoneyuuid);//通过红包uuid选择出红包

    int updateByPrimaryKeySelective(RedMoney record);

    int updateByPrimaryKey(RedMoney record);

    RedMoney selectByUserUUIDClick(String userUUIDClick);

    List<RedMoney> selectAllUserRedMoney(HashMap<String,Object> hashMap);

    int countByState(Map<String,Object> hashMap); //根据unactive used  active 统计相应的红包个数


}


