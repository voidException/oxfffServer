package org.geilove.service.impl;

import org.geilove.dao.CompanyputaoMapper;
import org.geilove.dao.NotificationMapper;
import org.geilove.dao.PutaoauthMapper;
import org.geilove.dao.UserStaffMapper;
import org.geilove.pojo.Companyputao;
import org.geilove.pojo.Notification;
import org.geilove.pojo.Putaoauth;
import org.geilove.pojo.UserStaff;
import org.geilove.requestParam.StuffsUserUUID;
import org.geilove.service.CompanyputaoService;
import org.geilove.vov.CompanyBaseInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 葡萄互助有关公司的操作api.
 */

@Service("CompanyputaoService")
public class CompanyputaoServiceImpl implements CompanyputaoService {

    @Resource
    private CompanyputaoMapper  companyputaoMapper;

    @Resource
    private UserStaffMapper  userStaffMapper;

    @Resource
    private NotificationMapper notificationMapper;

    @Resource
    private PutaoauthMapper putaoauthMapper;

    @Override
    public List<Companyputao> getCompanyputaos(Map<String,Object> map){
        List<Companyputao> companyputaoList=companyputaoMapper.getCompanyputao(map);
        return  companyputaoList;
    }
    @Override
    public  List<UserStaff>  getAllStuffs(Map<String,Object> map){
        List<UserStaff> userStaffList=userStaffMapper.getAllStuffs(map);
        return  userStaffList;
    }

    @Override
    public  CompanyBaseInfo   delResult(Map<String,Object> map){
        CompanyBaseInfo companyBaseInfo=new CompanyBaseInfo();

        try {
            int deltag=userStaffMapper.deleteByUserUUIDAccount(map);

        }catch (Exception e){
            companyBaseInfo.setCode(2100);
            return companyBaseInfo;
        }
        try {
           int deltag1=companyputaoMapper.delOneStuff(map.get("useruuid").toString());
        }catch (Exception e){
           companyBaseInfo.setCode(2200);
           return companyBaseInfo;
        }
        Companyputao companyputao=new Companyputao();
        try {
             companyputao=companyputaoMapper.getCompanyputaoInfo(map);

        }catch (Exception e){
            companyBaseInfo.setCode(2300);
            return companyBaseInfo;
        }
        companyBaseInfo.setStuff(companyputao.getStaffall().toString());
        companyBaseInfo.setAverage(companyputao.getAverage());
        companyBaseInfo.setTotalmoney(companyputao.getTotalmoenystr());

        return  companyBaseInfo;
    }
    @Override
    public int updateConfirmStuff(StuffsUserUUID stuffsUserUUID){
         int updateTag=userStaffMapper.updateforConfirmStuff(stuffsUserUUID);
         return  updateTag;
    }
    @Override
    public  List<Notification> getNotifications(Map<String,Object> map){
           List<Notification> notificationList=notificationMapper.getNotifications(map);
           return  notificationList;
    }

    @Override
    public  int  addStuff(UserStaff userStaff){
        int tag=userStaffMapper.insertSelective(userStaff);
        return  tag;
    }

    @Override
    public Putaoauth checkIfauth(String  useruuid){
        Putaoauth putaoauth=putaoauthMapper.checkIfauth(useruuid);
        return putaoauth;
    }
    @Override
    public int  addPutaoauth(Putaoauth putaoauth){
        int tagValue=putaoauthMapper.addPutaoauth(putaoauth);
        return  tagValue;

    }
    @Override
    public  int updatePutaoauth(Putaoauth putaoauth){
        int updateValue=putaoauthMapper.updatePutaoauth(putaoauth);
        return  updateValue;
    }

}




















