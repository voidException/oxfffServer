package org.geilove.dao;

import org.geilove.pojo.RenZheng;

public interface RenZhengMapper {
    int deleteByPrimaryKey(Long renzhengid);

    int insert(RenZheng record);

    int insertSelective(RenZheng record);

    RenZheng selectByPrimaryKey(Long renzhengid);

    RenZheng selectByUseriD(Long userid); //自定义，根据表中的useriD查找出记录

    int updateByPrimaryKeySelective(RenZheng record);

    int updateByPrimaryKey(RenZheng record);
}