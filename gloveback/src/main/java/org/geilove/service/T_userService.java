package org.geilove.service;



import org.geilove.pojo.T_user;

import java.util.Set;

/**
 * Created with IDEA
 * Created by ${jie.chen} on 2016/7/14.
 * 后台登录Service
 */
public interface T_userService {
    /**
     * Shiro的登录验证，通过用户名查询用户信息
     * @param userName
     * @return
     */
    public T_user findUserByUsername(String userName) ;

    /**
     * 根据账号查找角色名称
     * @param username
     * @return
     */
    public Set<String> findRoles(String username) ;

    /**
     * 根据账号查找权限
     * @param username
     * @return
     */
    public Set<String> findPermissions(String username) ;
}
