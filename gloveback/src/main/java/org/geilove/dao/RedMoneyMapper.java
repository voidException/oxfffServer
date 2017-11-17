package org.geilove.dao;

import org.geilove.pojo.RedMoney;

import java.util.HashMap;
import java.util.List;

public interface RedMoneyMapper {
    int deleteByPrimaryKey(Long redmoneyid);

    int insert(RedMoney record);

    int insertSelective(RedMoney record);

    RedMoney selectByPrimaryKey(Long redmoneyid);

    int updateByPrimaryKeySelective(RedMoney record);

    int updateByPrimaryKey(RedMoney record);

    RedMoney selectByUserUUIDClick(String userUUIDClick);

    List<RedMoney> selectAllUserRedMoney(HashMap<String,Object> hashMap);


}


