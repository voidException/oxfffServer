package org.geilove.dao;

import org.geilove.pojo.Role;

public interface RoleMapper {
    int insert(Role record);

    int insertSelective(Role record);
}