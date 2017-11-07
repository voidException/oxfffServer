package org.geilove.service.impl;

import org.geilove.dao.*;
import org.geilove.pojo.*;
import org.geilove.service.AshipService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by aihaitao on 8/7/2017.
 */
@Service("ashipService")
public class AshipServiceImpl implements AshipService {

    @Resource
    private StatisticsMapper  statisticsMapper;
    @Resource
    private AccountMapper    accountMapper;
    @Resource
    private UserAccountMapper  userAccountMapper;
    @Resource
    private PublicMapper  publicMapper;
    @Resource
    private PayMoneyMapper payMoneyMapper;


    @Override
    public Statistics getStatistics(){
        Statistics statistics=statisticsMapper.selectByPrimaryKey(1); //永远只有一条数据
        return  statistics;
    }

    @Override
    public Account getAccount( String  userIdentity ){
        Account account=new Account();
        account=accountMapper.selectByuserIdentity(userIdentity); //根据用户的身份证号获取
        return  account;
    }

    public  int insertAccount( Account account){
        int insertTag=accountMapper.insertSelective(account);
        return  insertTag;
    }

    @Override
    public UserAccount getUserAccountBreakIf(Map<String,Object> map){ // 查看用户是否有关联的公司
         UserAccount userAccount=null;
         userAccount=userAccountMapper.selectByBreakIf(map);
         return  userAccount;
    }
    @Override
    public  int insertUserAccount( UserAccount userAccount){
         int insertTag=userAccountMapper.insertSelective(userAccount);
         return  insertTag;
    }
    @Override
    public List<UserAccount> getUserAccountList(Map<String,Object> map){ //获取员工列表
        List<UserAccount> userAccountList;
        userAccountList=userAccountMapper.getUserAccountList(map);
        return  userAccountList;
    }

    @Override
    public  int updateByAccountUUIDSelective(UserAccount userAccount){
        int updateTag;
        updateTag=userAccountMapper.updateByAccountUUIDSelective(userAccount);
        return  updateTag;
    }

    /*********以下是公告**********/
    @Override
    public  List<Public> getPublicList(Map<String,Object> map){ //获取公告列表
         List<Public> publicList=publicMapper.getPublicList(map);
         return publicList;
    }

    @Override
    public int updateByPrimaryKeySelective(UserAccount userAccount) {
        return userAccountMapper.updateByPrimaryKeySelective(userAccount);
    }

    @Override
    public int updateByPrimaryKeySelectiveAndBusiness(UserAccount userAccount) {
        return userAccountMapper.updateByPrimaryKeySelectiveAndBusiness(userAccount);
    }

    ///////////////////////////
    public List<PayMoney> getPayMoneyList(String  useruuid){
         List<PayMoney> payMoneyList=payMoneyMapper.getPaymoneyList(useruuid);
         return  payMoneyList;
    }

}
