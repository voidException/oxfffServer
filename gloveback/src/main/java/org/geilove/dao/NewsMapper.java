package org.geilove.dao;

import org.geilove.pojo.News;

import java.util.List;
import java.util.Map;

public interface NewsMapper {
    int deleteByPrimaryKey(Long id);

    int deleteByNewsUUID(String  newsuuid); //根据newsuuid删除新闻

    int insert(News record);

    int insertSelective(News record);

    News selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKey(News record);

    List<News> getNewss(Map<String,Object> map);

    int countAll();
}