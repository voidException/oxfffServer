package org.geilove.service.impl;


import org.geilove.dao.T_userDao;
import org.geilove.pojo.T_user;
import org.geilove.service.T_userService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

/**
 * Created with IDEA
 * Created by ${jie.chen} on 2016/7/14.
 */
@Service("t_userService")
public class T_userServiceImpl implements T_userService {

    @Resource
    private T_userDao t_userDao ;

    @Override
    public T_user findUserByUsername(String userName) {
        T_user t_user = t_userDao.findUserByUsername(userName);
        return t_user;
    }

    @Override
    public Set<String> findRoles(String username) {
        return t_userDao.findRoles(username);
    }

    @Override
    public Set<String> findPermissions(String username) {
        return t_userDao.findPermissions(username);
    }
}
