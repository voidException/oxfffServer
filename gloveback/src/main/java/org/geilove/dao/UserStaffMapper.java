package org.geilove.dao;

import org.geilove.pojo.UserStaff;
import org.geilove.requestParam.StuffsUserUUID;

import java.util.List;
import java.util.Map;

public interface UserStaffMapper {
    int deleteByPrimaryKey(Long staffid);

    int insert(UserStaff record);

    int insertSelective(UserStaff record);

    UserStaff selectByPrimaryKey(Long staffid);

    int updateByPrimaryKeySelective(UserStaff record);

    int updateByPrimaryKey(UserStaff record);

    List<UserStaff> getAllStuffs(Map<String,Object> map);

    int deleteByUserUUIDAccount(Map<String,Object> map); //根据用户的useruuid和员工身份证号删除该员工

    int updateforConfirmStuff(StuffsUserUUID stuffsUserUUID);

}