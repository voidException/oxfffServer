package org.geilove.dao;

import org.geilove.pojo.ItemPgComment;

import java.util.List;
import java.util.Map;

public interface ItemPgCommentMapper {
    int deleteByPrimaryKey(Long itempgcommentid);

    int insert(ItemPgComment record);

    int insertSelective(ItemPgComment record);

    ItemPgComment selectByPrimaryKey(Long itempgcommentid);

    int updateByPrimaryKeySelective(ItemPgComment record);

    int updateByPrimaryKey(ItemPgComment record);

    //获取一条进度更新的评论
    List<ItemPgComment> getItemPgComments(Map<String,Object> map);
}