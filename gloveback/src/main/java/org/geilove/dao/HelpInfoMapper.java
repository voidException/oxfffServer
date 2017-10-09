package org.geilove.dao;

import org.geilove.pojo.HelpInfo;

public interface HelpInfoMapper {
    int deleteByPrimaryKey(Long helpeachotherid);

    int insert(HelpInfo record);

    int insertSelective(HelpInfo record);

    HelpInfo selectByPrimaryKey(Long helpeachotherid);

    HelpInfo selectByUserUUID(String useruuid);

    int updateByPrimaryKeySelective(HelpInfo record);

    int updateByPrimaryKey(HelpInfo record);
}