package org.geilove.dao;

import org.geilove.pojo.Putaoauth;

public interface PutaoauthMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Putaoauth record);

    int insertSelective(Putaoauth record);

    Putaoauth selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Putaoauth record);

    int updateByPrimaryKey(Putaoauth record);

    public Putaoauth  checkIfauth(String  useruuid);//检查是否已经认证了

    public int  addPutaoauth(Putaoauth putaoauth); //加入一条待认证的用户信息

    public  int updatePutaoauth(Putaoauth putaoauth); //后台管理员 ，更新认证信息
}