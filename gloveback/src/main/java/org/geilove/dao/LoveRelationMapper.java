package org.geilove.dao;

import org.geilove.pojo.LoveRelation;

public interface LoveRelationMapper {
    int deleteByPrimaryKey(Long loverelationid);

    int insert(LoveRelation record);

    int insertSelective(LoveRelation record);

    LoveRelation selectByPrimaryKey(Long loverelationid);

    int updateByPrimaryKeySelective(LoveRelation record);

    int updateByPrimaryKey(LoveRelation record);
}