package org.geilove.dao;

import org.geilove.pojo.Resource;

import java.util.List;

public interface ResourceMapper {
    int deleteByPrimaryKey(Integer resourceid);

    int insert(Resource record);

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(Integer resourceid);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);

    // 获得资源
    List<Resource> getResources();
}