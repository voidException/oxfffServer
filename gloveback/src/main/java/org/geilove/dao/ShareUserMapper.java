package org.geilove.dao;

import org.geilove.pojo.ShareUser;

public interface ShareUserMapper {
    int deleteByPrimaryKey(Long shareid);

    int insert(ShareUser record);

    int insertSelective(ShareUser record);

    ShareUser selectByPrimaryKey(Long shareid);

    int updateByPrimaryKeySelective(ShareUser record);

    int updateByPrimaryKey(ShareUser record);

    ShareUser selectByUserUUID(String userUUID);
}