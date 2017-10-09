package org.geilove.service.impl;

import org.geilove.dao.RenZhengMapper;
import org.geilove.pojo.RenZheng;
import org.geilove.service.RenZhengService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by mfhj-dz-001-424 on 17/3/7.
 */
@Service("renzheng")
public class RenZhengServiceImpl implements RenZhengService {

    @Resource
    private RenZhengMapper renZhengMapper;

    public RenZheng getRenzheng(Long userid){
        RenZheng renZheng=null;
        renZheng=renZhengMapper.selectByUseriD(userid);
        return  renZheng;
    } //根据用户的iD查询是否已有此用户

    public int insertRecord(RenZheng record){
        int insertTag=renZhengMapper.insertSelective(record);
        return  insertTag;
    }  //插入认证记录

    public  int updateRecord(RenZheng record){
        int  updateTage=renZhengMapper.updateByPrimaryKeySelective(record); //需要根据主键进行更新，这个得查询后取得主键。
        return updateTage;
    } //更新一条记录

}
