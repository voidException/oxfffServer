package org.geilove.dao;

import org.geilove.pojo.Resource;

public interface ResourceMapper {
    int deleteByPrimaryKey(Integer resourceid);

    int insert(Resource record);

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(Integer resourceid);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);
}