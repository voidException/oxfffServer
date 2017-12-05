package org.geilove.service;

import org.geilove.pojo.Account;
import org.geilove.pojo.UserAccount;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

public interface UserAndUserAccountService {

    public UserAccount selectByuserIdentity(Map<String,Object> map) ;

    public UserAccount selectByUserAndUserAccount(HashMap<String,Object> map);

    public int updateByPrimaryKey(UserAccount userAccount);


}
