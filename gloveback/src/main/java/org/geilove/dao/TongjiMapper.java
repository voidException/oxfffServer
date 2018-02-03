package org.geilove.dao;

import org.geilove.pojo.Tongji;

import java.util.List;
import java.util.Map;

public interface TongjiMapper {
    int deleteByPrimaryKey(Integer tongjiid);

    int insert(Tongji record);

    int insertSelective(Tongji record);

    Tongji selectByPrimaryKey(Integer tongjiid);

    int updateByPrimaryKeySelective(Tongji record);

    int updateByPrimaryKey(Tongji record);

    List<Tongji> selectByType(Map<String,Object> map); //选择出最近10天的统计

    List<Tongji>  selectNewest();

}