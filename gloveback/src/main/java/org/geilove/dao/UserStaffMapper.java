package org.geilove.dao;

import org.geilove.pojo.Companyputao;
import org.geilove.pojo.User;
import org.geilove.pojo.UserAccount;
import org.geilove.pojo.UserStaff;
import org.geilove.requestParam.StuffsUserUUID;

import java.util.List;
import java.util.Map;

public interface UserStaffMapper {
    int deleteByPrimaryKey(Long staffid);

    int insert(UserStaff record);

    int insertSelective(UserStaff record);

    UserStaff selectByPrimaryKey(Long staffid);

    List<UserStaff> selectByPhone(String phone);

    int updateByPrimaryKeySelective(UserStaff record);

    int updateByPrimaryKey(UserStaff record);

    List<UserStaff> getAllStuffs(Map<String,Object> map);

    int deleteByUserUUIDAccount(Map<String,Object> map); //根据用户的useruuid和员工身份证号删除该员工

    int updateforConfirmStuff(StuffsUserUUID stuffsUserUUID);

    UserStaff selectByparam(Map<String,Object> map);

    List<UserStaff> getSumInfo(Map<String,Object> map); //根据互助计划，获得所有参与该计划的员工

    int getTotalInfo(Map<String,Object> map); // 参加大病互助或者意外伤害互助的员工总数

    List<UserStaff> getMainSumInfo(Map<String,Object> map); //hlptype，affirm确认

    List<UserStaff> getUserStaffByUserUUID(Map<String,Object> map); //根据useruuid获取该公司对应的员工列表

    //
    List<UserStaff> selectTotalStaff(Map<String,Object> map);

    List<UserStaff> selectTotalStaff2(Map<String,Object> map);

    int getTotalStaff();

    int getTotalByHelpType(Map<String,Object> map ); //根据互助类型helpType和affirm 是否确认来统计是总人数

    int getTotalByHelpTypeAndUserUUID(Map<String,Object> map ); //统计某个公司下的某种互助计划的总人数
}