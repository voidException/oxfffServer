package org.geilove.dao;

import org.geilove.pojo.Public;

import java.util.List;
import java.util.Map;

public interface PublicMapper {
    int deleteByPrimaryKey(Long publicid);

    int insert(Public record);

    int insertSelective(Public record);

    Public selectByPrimaryKey(Long publicid);

    int updateByPrimaryKeySelective(Public record);

    int updateByPrimaryKey(Public record);

    List<Public> getPublicList(Map<String,Object> map);
}