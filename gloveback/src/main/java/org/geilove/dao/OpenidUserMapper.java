package org.geilove.dao;

import org.geilove.pojo.OpenidUser;

public interface OpenidUserMapper {
    int deleteByPrimaryKey(Long openiduserid);

    int insert(OpenidUser record);

    int insertSelective(OpenidUser record);

    OpenidUser selectByPrimaryKey(Long openiduserid);

    OpenidUser selectByOpenId(String openiduserid);

    int updateByPrimaryKeySelective(OpenidUser record);

    int updateByPrimaryKey(OpenidUser record);


}