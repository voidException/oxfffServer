package org.geilove.service;

import org.geilove.pojo.Public;
import org.geilove.pojo.Statistics;
import  org.geilove.pojo.Account;
import  org.geilove.pojo.UserAccount;

import java.util.List;
import java.util.Map;

/**
 * Created by aihaitao on 8/7/2017.
 */
public interface AshipService {

    public Statistics  getStatistics(); // 获取统计信息

    public  Account   getAccount( String  userIdentity); //获取Account表信息

    public  int insertAccount( Account account); //增加一条记录到Account表

    public  UserAccount  getUserAccountBreakIf(Map<String,Object> map); // 查看用户是否有关联的公司

    public  int insertUserAccount( UserAccount userAccount); //增加一条记录到UserAccount表

    public List<UserAccount>  getUserAccountList(Map<String,Object> map); //获取员工列表

    public  int updateByAccountUUIDSelective(UserAccount userAccount);  //根据用户的AccountUUID 进行更新

    /*********以下是公告**********/
    public  List<Public> getPublicList(Map<String,Object> map);  //

    public int updateByPrimaryKeySelective(UserAccount userAccount);

    public int updateByPrimaryKeySelectiveAndBusiness(UserAccount userAccount);


}
