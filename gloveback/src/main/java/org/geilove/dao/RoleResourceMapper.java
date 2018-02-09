package org.geilove.dao;

import org.geilove.pojo.RoleResource;

import java.util.List;

public interface RoleResourceMapper {
    int deleteByPrimaryKey(Integer roleresourceid);

    int insert(RoleResource record);

    int insertSelective(RoleResource record);

    RoleResource selectByPrimaryKey(Integer roleresourceid);

    int updateByPrimaryKeySelective(RoleResource record);

    int updateByPrimaryKey(RoleResource record);

    List<RoleResource> getRoleResourceList(Integer roleid); //
}