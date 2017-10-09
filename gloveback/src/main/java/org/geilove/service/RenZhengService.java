package org.geilove.service;

import org.geilove.pojo.RenZheng;

import javax.management.relation.RelationNotFoundException;

/**
 * Created by mfhj-dz-001-424 on 17/3/7.
 */
public interface RenZhengService {

    public RenZheng getRenzheng(Long userid); //根据用户的iD查询是否已有此用户

    public int insertRecord(RenZheng record);  //插入认证记录

    public  int updateRecord(RenZheng record); //更新一条记录
}
