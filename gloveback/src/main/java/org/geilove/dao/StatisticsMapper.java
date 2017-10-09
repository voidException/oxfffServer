package org.geilove.dao;

import org.geilove.pojo.Statistics;

public interface StatisticsMapper {
    int deleteByPrimaryKey(Integer statisticsid);

    int insert(Statistics record);

    int insertSelective(Statistics record);

    Statistics selectByPrimaryKey(Integer statisticsid);

    int updateByPrimaryKeySelective(Statistics record);

    int updateByPrimaryKey(Statistics record);
}