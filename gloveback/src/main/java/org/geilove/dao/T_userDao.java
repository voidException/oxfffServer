package org.geilove.dao;



import org.geilove.pojo.T_user;

import java.util.List;
import java.util.Set;

/**
 * Created with IDEA
 * Created by ${jie.chen} on 2016/7/14.
 */
public interface T_userDao {
    T_user findUserByUsername(String userName);

    Set<String> findRoles(String username);

    Set<String> findPermissions(String username);

    int deleteByPrimaryKey(Integer id);

    int insert(T_user record);

    int insertSelective(T_user record);

    T_user selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(T_user record);

    int updateByPrimaryKey(T_user record);

    List<T_user> getuserList();
}
