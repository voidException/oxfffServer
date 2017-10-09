package org.geilove.service;

import org.geilove.pojo.ItemPgComment;
import org.geilove.pojo.ItemProgress;
import org.geilove.response.CommonRsp;

import java.util.List;
import java.util.Map;

/**
 * Created by mfhj-dz-001-424 on 17/2/8.
 */
public interface ItemProgressService {
    //获取完整的，进度更新表
    public  List<ItemProgress> getItemProgressUpdate(Map<String,Object> map);

    //获取一条进度更新的评论
    public List<ItemPgComment> getOneItemProgressUpdateComments(Map<String,Object>  map);

    //加入"项目进度更新"
    public CommonRsp  addOneItemProgress(ItemProgress  itemProgress);

    //对"项目进度更新"进行评论
    public  CommonRsp addItemPgComment(ItemPgComment  itemPgComment);

    //删除"醒目进度更新"的某条评论，这里只有其评论者才能删除
    public  CommonRsp deleteItemPgComment(Long itemPgCommentiD);
}
