package org.geilove.dao;

import org.geilove.pojo.MoneysrcPinglun;

import java.util.List;
import java.util.Map;

public interface MoneysrcPinglunMapper {
    int deleteByPrimaryKey(Long moneysrcpinglunid);

    int insert(MoneysrcPinglun record);

    int insertSelective(MoneysrcPinglun record);

    MoneysrcPinglun selectByPrimaryKey(Long moneysrcpinglunid);

    int updateByPrimaryKeySelective(MoneysrcPinglun record);

    int updateByPrimaryKey(MoneysrcPinglun record);

    //根据一个MoneySourceID,获取其对应的评论，这里把pageSize的值设置的尽量大，尽量一次都加载出来
    List<MoneysrcPinglun>  getMoneySourcePingLunList(Map<String,Object> map);  //xml 待实现
}