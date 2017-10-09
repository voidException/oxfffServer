package org.geilove.controller;

/**
 * Created by mfhj-dz-001-424 on 17/2/7.
 * 实现对救助推文更新进度的功能，进度列表，评论回复进度
 */
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.geilove.pojo.*;
import org.geilove.requestParam.AddItemPgCommentParam;
import org.geilove.requestParam.AddItemProgressParam;
import org.geilove.requestParam.DeleteItemPgCommentParam;
import org.geilove.requestParam.ItemProgressListParam;
import org.geilove.response.CommonRsp;
import org.geilove.response.ProgressUpdate;
import org.geilove.response.ProgressUpdateRsp;
import org.geilove.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("/itemprogress")
public class ItemProgressController {

    @Resource
    private ItemProgressService  itemProgressService;

    @Resource
    private RegisterLoginService rlService;
    /*获取救助推文的进度更新及其评论---只能通过itemprogress表的userUUID和cashUUID获取*/
    @RequestMapping(value="/updatelist.do",method=RequestMethod.POST)
    @ResponseBody
    public Object getProgressUpdateList(@RequestBody ItemProgressListParam updateListParam, HttpServletRequest request ){
        ProgressUpdateRsp progressUpdateRsp=new ProgressUpdateRsp();
        List<ProgressUpdate> listProgress=new ArrayList<ProgressUpdate>(); //进度更新及其评论列表
        Map<String,Object>  map=new HashMap<String,Object>();
        if (updateListParam==null){
            progressUpdateRsp.setRetcode(2001);
            progressUpdateRsp.setMsg("参数为空");
            return  progressUpdateRsp;
        }

        Integer page= updateListParam.getPage();
        Integer pageSize=updateListParam.getPageSize();
        String  timeStamp=updateListParam.getTimeStamp();

        String userUUID=updateListParam.getUserUUID();  //userUUID
        String cashUUID=updateListParam.getCashUUID();  //cashUUID

        map.put("userUUID",userUUID); //xml中使用了
        map.put("cashUUID",cashUUID); //xml中使用了
        map.put("page",0);
        map.put("pageSize",10);
        map.put("lastTime",timeStamp);

        List<ItemProgress>  lip=new ArrayList<ItemProgress>(); //单纯的更新列表
        try{
            lip=itemProgressService.getItemProgressUpdate(map);  //从数据库获取
            if (lip==null || lip.size()==0) {
                progressUpdateRsp.setMsg("暂时没有动态更新哦");
                progressUpdateRsp.setRetcode(2001);
                progressUpdateRsp.setLp(null);
            }
        }catch (Exception e){
            progressUpdateRsp.setMsg("暂时没有动态更新哦");
            progressUpdateRsp.setRetcode(2001);
            progressUpdateRsp.setLp(null);
            return  progressUpdateRsp;
        }
        List<Long> itemProgressIDs=new ArrayList<Long>(); //存放获取的"进度更新的"iDs
        List<ProgressUpdate> progressUpdates=new ArrayList<ProgressUpdate>(); // 包含有"评论了"的 进度更新列表

        for(int i=0;i<lip.size();i++){
            itemProgressIDs.add(lip.get(i).getItemprogressid());
            ProgressUpdate  progressUpdate=new ProgressUpdate();
            progressUpdate.setItemProgress(lip.get(i)); //listProgress 中的任意一项的第一次组装，还需要第二次
            listProgress.add(progressUpdate);
        }
        progressUpdateRsp.setLp(listProgress);

        for (int i=0;i<itemProgressIDs.size();i++){
            Long  itemProgressiD=itemProgressIDs.get(i); //获取一个iD
            //用这个iD查找其对应的，评论回复列表
            List<ItemPgComment> pgCommentList=new ArrayList<ItemPgComment>();

            Map<String,Object> map2=new HashMap<String,Object>();
            map2.put("itemProgressiD",itemProgressiD); //
            map2.put("page",0);
            map2.put("pageSize",20);

            try {
                pgCommentList=itemProgressService.getOneItemProgressUpdateComments(map2); // 查找"进度更新" 的评论列表

            }catch (Exception e){
                pgCommentList=null;
                progressUpdateRsp.setMsg("对进度的评论查找失败");
                progressUpdateRsp.setRetcode(2000);
                return progressUpdateRsp;
            }
            //这样保险
            if(itemProgressiD==  listProgress.get(i).getItemProgress().getItemprogressid()){
                listProgress.get(i).setLmp(pgCommentList); //listProgress 中的任意一项的第二次组装
            }
            //如果不相等，那说明对应不上，这种情况怎么处理呢，怎么处理呢，不处理
            //listProgress.get(i).setLmp(pgCommentList); //listProgress 中的任意一项的第二次组装
        }
        progressUpdateRsp.setMsg("成功了");
        progressUpdateRsp.setRetcode(2000);

        return progressUpdateRsp;
    }

    /*更新项目的进度*/
    @RequestMapping(value="/addupdate.do",method=RequestMethod.POST)
    @ResponseBody
    public Object addItemProgressUpdate(@RequestBody AddItemProgressParam  addItemProgressParam ){
        CommonRsp commonRsp=new CommonRsp();

        if (addItemProgressParam==null){
            commonRsp.setMsg("请求参数为空");
            commonRsp.setRetcode(2001);
            return  commonRsp;
        }

        String token=addItemProgressParam.getToken();
        if(token.length()<33){
            commonRsp.setMsg("凭证不合法");
            commonRsp.setRetcode(2001);
            return commonRsp;
        }
        String userPassword=token.substring(0,32); //token是password和userID拼接成的。
        String useridStr=token.substring(32); //取得userid部分
        Long userid=Long.valueOf(useridStr).longValue();  //转换成long类型
        String passwdTrue=rlService.selectMD5Password(Long.valueOf(userid));
        if(!userPassword.equals(passwdTrue)){
            commonRsp.setRetcode(2001);
            commonRsp.setMsg("用户密码不对，非法");
            return commonRsp;
        }

        ItemProgress itemProgress=new ItemProgress();
        itemProgress.setItemuuid(java.util.UUID.randomUUID().toString());

        itemProgress.setUserid(userid);
        itemProgress.setUuid(addItemProgressParam.getUseruuid());
        itemProgress.setUserphoto(addItemProgressParam.getUserphoto());
        itemProgress.setUsernickname(addItemProgressParam.getUsernickname());

        itemProgress.setCashuuid(addItemProgressParam.getCashUUID()); //资金表的cashUUID

        itemProgress.setContent(addItemProgressParam.getContent());
        itemProgress.setUpdatetime(new Date());

//        itemProgress.setImgaddressone(addItemProgressParam.getImgaddressone());
//        itemProgress.setImgaddresstwo(addItemProgressParam.getImgaddresstwo());
//        itemProgress.setImgaddressthree(addItemProgressParam.getImgaddressthree());
//        itemProgress.setImgaddressfour(addItemProgressParam.getImgaddressfour());
//        itemProgress.setImgaddressfive(addItemProgressParam.getImgaddressfive());
//        itemProgress.setImgaddresssix(addItemProgressParam.getImgaddresssix());

        try{
            commonRsp=itemProgressService.addOneItemProgress(itemProgress);
        }catch (Exception e){
            commonRsp.setMsg("增加项目更新出现异常");
            commonRsp.setRetcode(2001);
            return  commonRsp;
        }
        commonRsp.setRetcode(2000);
        commonRsp.setMsg("更新成功");
        return commonRsp;
    }


    /*对进度进行评论*/
    @RequestMapping(value="/addupdatecomment.do",method=RequestMethod.POST)
    @ResponseBody
    public Object addItemPgComment(@RequestBody AddItemPgCommentParam itemPgCommentParam ){
        CommonRsp commonRsp=new CommonRsp();

        if (itemPgCommentParam==null){
            commonRsp.setMsg("请求参数为空");
            commonRsp.setRetcode(2001);
            return  commonRsp;
        }

        String token=itemPgCommentParam.getToken();
        if(token.length()<33){
            commonRsp.setMsg("凭证不合法");
            commonRsp.setRetcode(2001);
            return commonRsp;
        }
        String userPassword=token.substring(0,32); //token是password和userID拼接成的。
        String useridStr=token.substring(32); //取得userid部分
        Long userid=Long.valueOf(useridStr).longValue();  //转换成long类型
        String passwdTrue=rlService.selectMD5Password(Long.valueOf(userid));
        if(!userPassword.equals(passwdTrue)){
            commonRsp.setRetcode(2001);
            commonRsp.setMsg("用户密码不对，非法");
            return commonRsp;
        }

        //写入数据库
        ItemPgComment  itemPgComment=new ItemPgComment();
        itemPgComment.setItemprogressid(itemPgCommentParam.getItemprogressid());
        itemPgComment.setUseridsender(userid);
        itemPgComment.setUseruuidsender(itemPgCommentParam.getUseruuidsender());
        itemPgComment.setSendernickname(itemPgCommentParam.getSendernickname());
        itemPgComment.setSenderphoto(itemPgCommentParam.getSenderphoto());
        itemPgComment.setUseridreciver(itemPgCommentParam.getUseridreciver());
        itemPgComment.setUseruuidreciver(itemPgCommentParam.getUseruuidreciver());
        itemPgComment.setRecivernickname(itemPgCommentParam.getRecivernickname());
        itemPgComment.setContent(itemPgCommentParam.getContent());
        itemPgComment.setRefer(itemPgCommentParam.getRefer());
        itemPgComment.setCommenttime(new Date());

        try{
            commonRsp=itemProgressService.addItemPgComment(itemPgComment);
        }catch (Exception e){
            commonRsp.setMsg("对进度更新评论出现异常");
            commonRsp.setRetcode(2001);
            return  commonRsp;
        }

        return commonRsp;
    }

    /*对进度的评论进行删除(仅评论人可以删除)*/
    @RequestMapping(value="/deleteitemupdatecomment.do",method=RequestMethod.POST)
    @ResponseBody
    public Object deleteItemPgComment(@RequestBody DeleteItemPgCommentParam deleteItemPgCommentParam ){
        CommonRsp commonRsp=new CommonRsp();

        if (deleteItemPgCommentParam==null){
            commonRsp.setMsg("请求参数为空");
            commonRsp.setRetcode(2001);
            return  commonRsp;
        }

        String token=deleteItemPgCommentParam.getToken();
        if(token.length()<33){
            commonRsp.setMsg("凭证不合法");
            commonRsp.setRetcode(2001);
            return commonRsp;
        }
        String userPassword=token.substring(0,32); //token是password和userID拼接成的。
        String useridStr=token.substring(32); //取得userid部分
        Long userid=Long.valueOf(useridStr).longValue();  //转换成long类型
        String passwdTrue=rlService.selectMD5Password(Long.valueOf(userid));
        if(!userPassword.equals(passwdTrue)){
            commonRsp.setRetcode(2001);
            commonRsp.setMsg("用户密码不对，非法");
            return commonRsp;
        }

        try {
            commonRsp=itemProgressService.deleteItemPgComment(deleteItemPgCommentParam.getMoneysrcpinglunid());
        }catch (Exception e){
            commonRsp.setMsg("删除项目更新进度的评论出现异常");
            commonRsp.setRetcode(2001);
            return commonRsp;
        }

        return commonRsp;
    }

}


















