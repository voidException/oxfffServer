package org.geilove.dao;

import org.geilove.pojo.User;
import org.geilove.pojo.UserAccount;
import java.lang.Object;
import java.util.List;
import java.util.Map;

public interface UserAccountMapper {
    int deleteByPrimaryKey(Long useraccountid);

    int insert(UserAccount record);

    int insertSelective(UserAccount record);

    UserAccount selectByPrimaryKey(Long useraccountid);

    int updateByPrimaryKeySelective(UserAccount record);

    int updateByAccountUUIDSelective(UserAccount record);

   // int updateByPrimaryKeyWithBLOBs(UserAccount record);

    int updateByPrimaryKey(UserAccount record);

    UserAccount selectByBreakIf(Map<String, Object> map); //自定义,根据用户的身份证号和breakIf和buildRelationDescription选择出来

    List<UserAccount> getUserAccountList(Map<String,Object> map); //获取一个用户下面关联的家庭成员或者公司员工

    UserAccount selectByUserAndUserAccount(Map<String,Object> map);//获取一个User和Account的关联数据

    int updateByPrimaryKeySelectiveAndBusiness(UserAccount userAccount);

    UserAccount  selectByUUID(Map<String,Object> map);

    UserAccount selectByaccountUUID(Map<String,Object> map);

    UserAccount selectByuserIdentity(Map<String,Object> map); //根据用户的身份证号选择出用户

    List<UserAccount> getSumInfo(String helptype); //

    int getTotalUserAccount ();
}