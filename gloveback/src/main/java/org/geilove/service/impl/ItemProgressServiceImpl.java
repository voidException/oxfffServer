package org.geilove.service.impl;

import org.geilove.dao.ItemPgCommentMapper;
import org.geilove.dao.ItemProgressMapper;
import org.geilove.pojo.ItemPgComment;
import org.geilove.pojo.ItemProgress;
import org.geilove.response.CommonRsp;
import org.geilove.service.ItemProgressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by mfhj-dz-001-424 on 17/2/8.
 */
@Service("itemProgress")
public class ItemProgressServiceImpl implements ItemProgressService {
    @Resource
    private ItemProgressMapper  itemProgressMapper;
    @Resource
    private ItemPgCommentMapper itemPgCommentMapper;

    //获取完整的，进度更新表
    public List<ItemProgress> getItemProgressUpdate(Map<String,Object> map){
        List<ItemProgress> itemProgressList= itemProgressMapper.getItemProgressList(map);
        return  itemProgressList;
    }
    //获取一条进度更新的评论
    public List<ItemPgComment> getOneItemProgressUpdateComments(Map<String,Object>  map){
        List<ItemPgComment>  itemPgCommentList=itemPgCommentMapper.getItemPgComments(map);
        return  itemPgCommentList;
    }

    //加入一条项目更新
    public CommonRsp addOneItemProgress(ItemProgress  itemProgress){
        CommonRsp  commonRsp=new CommonRsp();
        int addTag=itemProgressMapper.insertSelective(itemProgress);
        if (addTag==1){
            commonRsp.setMsg("增加一条项目更新成功");
            commonRsp.setRetcode(2000);
        }else {
            commonRsp.setMsg("增加项目更新失败");
            commonRsp.setRetcode(2001);
        }

        return  commonRsp;
    }
    //对"项目进度更新"进行评论
    public  CommonRsp addItemPgComment(ItemPgComment  itemPgComment){
        CommonRsp  commonRsp =new CommonRsp();
        int addTag=itemPgCommentMapper.insertSelective(itemPgComment);
        if (addTag==1){
            commonRsp.setMsg("对项目进度更新进行评论成功");
            commonRsp.setRetcode(2000);
            return  commonRsp;
        }else {
            commonRsp.setMsg("对项目进度更新评论失败");
            commonRsp.setRetcode(2001);
            return  commonRsp;
        }
    }
    //删除"项目进度更新"的某条评论，这里只有其评论者才能删除
    public  CommonRsp deleteItemPgComment(Long itemPgCommentiD){
        CommonRsp commonRsp=new CommonRsp();
        int  deleteTag=itemPgCommentMapper.deleteByPrimaryKey(itemPgCommentiD);
        if (deleteTag==1){
            commonRsp.setMsg("项目进度更新的某条评论失败");
            commonRsp.setRetcode(2001);
            return  commonRsp;
        }else {
            commonRsp.setMsg("删除项目进度的评论成功");
            commonRsp.setRetcode(2000);
            return commonRsp;

        }
    }
}
