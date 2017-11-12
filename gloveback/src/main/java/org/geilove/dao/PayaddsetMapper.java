package org.geilove.dao;

import org.geilove.pojo.Payaddset;

import java.util.List;
import java.util.Map;

public interface PayaddsetMapper {
    int deleteByPrimaryKey(Long payaddid);

    int insert(Payaddset record);

    int insertSelective(Payaddset record);

    Payaddset selectByPrimaryKey(Long payaddid);

    int updateByPrimaryKeySelective(Payaddset record);

    int updateByPrimaryKey(Payaddset record);
    //根据out_trade_no 选择出所有的待确认用户
    List<Payaddset> getPayaddsets(Map<String,Object> map);

}