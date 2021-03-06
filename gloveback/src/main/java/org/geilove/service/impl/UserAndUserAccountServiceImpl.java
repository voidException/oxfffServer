package org.geilove.service.impl;

import org.geilove.dao.AccountMapper;
import org.geilove.dao.UserAccountMapper;
import org.geilove.pojo.Account;
import org.geilove.pojo.UserAccount;
import org.geilove.service.UserAndUserAccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Songshifeng on 2017/9/21
 */
@Service
public class UserAndUserAccountServiceImpl implements UserAndUserAccountService {

    @Resource
    private AccountMapper accountMapper;

    @Resource
    private UserAccountMapper userAccountMapper;


    /**
     * 通过 身份证号 去数据库查询到对应的account
     * @param
     * @return
     */
    @Override
    public UserAccount selectByuserIdentity(Map<String,Object> map) {
        UserAccount userAccount=userAccountMapper.selectByuserIdentity(map);
        return userAccount;
    }

    @Override
    public UserAccount selectByUserAndUserAccount(HashMap<String, Object> map) {
        return userAccountMapper.selectByUserAndUserAccount(map);
    }

    @Override
    public int updateByPrimaryKey(UserAccount userAccount) {
        return userAccountMapper.updateByPrimaryKeySelective(userAccount);
    }
}
