package org.geilove.dao;

import org.geilove.pojo.Putaoauth;

import java.util.List;
import java.util.Map;

public interface PutaoauthMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Putaoauth record);

    int insertSelective(Putaoauth record);

    Putaoauth selectByPrimaryKey(Long id);

    Putaoauth selectByUserUUID(String useruuid);

    int updateByPrimaryKeySelective(Putaoauth record);

    int updateByPrimaryKey(Putaoauth record);

    public Putaoauth  checkIfauth(String  useruuid);//检查是否已经认证了

    public int  addPutaoauth(Putaoauth putaoauth); //加入一条待认证的用户信息

    public  int updatePutaoauth(Putaoauth putaoauth); //后台管理员 ，更新认证信息

    List<Putaoauth>  getPutaoauths(Map<String,Object> map); //获取已认证，未认证，或者被拒绝的公司

    List<Putaoauth>  getPutaoauthSearch(Map<String,Object> map); //根据企业的名字获得企业的认证信息
}