package org.geilove.dao;

import org.geilove.pojo.Companyputao;

import java.util.List;
import java.util.Map;

public interface CompanyputaoMapper {

    int deleteByPrimaryKey(Integer companyid);

    int insert(Companyputao record);

    int insertSelective(Companyputao record);

    Companyputao selectByPrimaryKey(Integer companyid);

    int updateByPrimaryKeySelective(Companyputao record);

    int updateByPrimaryKey(Companyputao record);

    //自定义，获取公司参与的互助计划
    List<Companyputao> getCompanyputao(Map<String,Object> map);

    //员工数减一
    int delOneStuff(String useruuid);

    //获得一公司参与的某互助计划信息，useruuid、helptype
    Companyputao getCompanyputaoInfo(Map<String,Object> map);

    List<Companyputao> getSumInfo(String helptype);

    int getTotalCompany();

    List<Companyputao> getCompanyList(Map<String,Object> map);

    int getCompanyputaoCount();

}