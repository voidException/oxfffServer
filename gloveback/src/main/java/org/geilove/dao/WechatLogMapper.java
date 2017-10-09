package org.geilove.dao;

import org.geilove.pojo.WechatLog;

public interface WechatLogMapper {
    int insert(WechatLog record);

    int insertSelective(WechatLog record);
}