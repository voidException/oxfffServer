package org.geilove.dao;

import org.geilove.pojo.Account;
import org.geilove.pojo.UserAccount;

import java.util.List;
import java.util.Map;

public interface AccountMapper {
    int deleteByPrimaryKey(Long accountid);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Long accountid);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);

    Account selectByuserIdentity(String userIdentity); //根据userIdentity获取账号表





}