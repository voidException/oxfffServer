package org.geilove.dao;

import java.util.List;
import java.util.Map;

import org.geilove.pojo.MoneySource;
import org.geilove.sqlpojo.PartHelpPojo;
import org.geilove.sqlpojo.OtherPartHelpPojo;

public interface MoneySourceMapper {
    int deleteByPrimaryKey(Long moneysourceid);

    int insert(MoneySource record);

    int insertSelective(MoneySource record);

    MoneySource selectByPrimaryKey(Long moneysourceid);

    MoneySource selectByCashUUID(String cashUUID);

    MoneySource selectByMoneySourceUUID(String moneySourceUUID);

    List<PartHelpPojo>  selectMenHelpMeloadMore(Map<String,Object> map); //自定义，获取帮助我的，加载更多


    List<PartHelpPojo> selectIhelploadMore(Map<String,Object> map); //获取我帮助的人，加载更多

    //获取完整的MoneySource列表，按照时间获取
    List<MoneySource> getMoneySourceList(Map<String,Object>  map); //userBeHelpID
    List<MoneySource> getMoneySourceListByCashUUID(Map<String,Object>  map); //useruuid
    //List.size 大于1的话就是多次捐款，那么救助的人数不应该+1
    List<PartHelpPojo> selectCheckCount(Map<String,Object> map); //这个用于确认用户是不是对一个人的某次救助多次捐款，


    int updateByPrimaryKeySelective(MoneySource record);

    int updateByPrimaryKey(MoneySource record);
}