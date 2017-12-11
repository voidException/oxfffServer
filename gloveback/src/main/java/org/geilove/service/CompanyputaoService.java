package org.geilove.service;

import org.geilove.pojo.Companyputao;
import org.geilove.pojo.Notification;
import org.geilove.pojo.Putaoauth;
import org.geilove.pojo.UserStaff;
import org.geilove.requestParam.StuffsUserUUID;
import org.geilove.vov.CompanyBaseInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by aihaitao on 3/11/2017.
 */
public interface CompanyputaoService {

    public List<Companyputao>  getCompanyputaos(Map<String,Object> map);

    public  List<UserStaff>  getAllStuffs(Map<String,Object> map); //获得公司员工列表

    public  CompanyBaseInfo   delResult(Map<String,Object> map); //删除一名员工后，返回公司的余额，员工总数，平均余额



    public  List<Notification> getNotifications(Map<String,Object> map);

    public  int  addStuff(UserStaff userStaff);

    public Putaoauth  checkIfauth(String  useruuid);//检查是否已经认证了

    public int  addPutaoauth(Putaoauth putaoauth); //加入一条待认证的用户信息

    public  int updatePutaoauth(Putaoauth putaoauth); //后台管理员 ，更新认证信息

    //查询员工是否已经加入一个互助计划
    public  UserStaff selectUserStaff(Map<String,Object> map);

}
