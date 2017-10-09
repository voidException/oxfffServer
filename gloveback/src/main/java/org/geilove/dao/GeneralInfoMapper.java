package org.geilove.dao;

import org.geilove.pojo.GeneralInfo;

public interface GeneralInfoMapper {
    int deleteByPrimaryKey(Long generalinfoid);

    int insert(GeneralInfo record);

    int insertSelective(GeneralInfo record);

    GeneralInfo selectByPrimaryKey(Long generalinfoid);

    int updateByPrimaryKeySelective(GeneralInfo record);

    int updateByPrimaryKey(GeneralInfo record);
}