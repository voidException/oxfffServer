package org.geilove.dao;

import org.geilove.pojo.ItemProgress;

import java.util.List;
import java.util.Map;

public interface ItemProgressMapper {
    int deleteByPrimaryKey(Long itemprogressid);

    int insert(ItemProgress record);

    int insertSelective(ItemProgress record);

    ItemProgress selectByPrimaryKey(Long itemprogressid);

    int updateByPrimaryKeySelective(ItemProgress record);

    int updateByPrimaryKey(ItemProgress record);

    //获取进度更新的列表
    List<ItemProgress> getItemProgressList(Map<String,Object> map);

}