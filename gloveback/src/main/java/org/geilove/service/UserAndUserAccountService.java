package org.geilove.service;

import org.geilove.pojo.Account;
import org.geilove.pojo.UserAccount;
import org.springframework.stereotype.Service;

import java.util.HashMap;

public interface UserAndUserAccountService {
    public Account selectByuserIdentity(String userIdentity);

    public UserAccount selectByUserAndUserAccount(HashMap<String,Object> map);

    public int updateByPrimaryKey(UserAccount userAccount);
}
