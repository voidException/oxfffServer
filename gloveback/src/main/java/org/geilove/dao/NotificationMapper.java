package org.geilove.dao;

import org.geilove.pojo.Notification;

import java.util.List;
import java.util.Map;

public interface NotificationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Notification record);

    int insertSelective(Notification record);

    Notification selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Notification record);

    int updateByPrimaryKey(Notification record);

    List<Notification> getNotifications(Map<String,Object> map);
}