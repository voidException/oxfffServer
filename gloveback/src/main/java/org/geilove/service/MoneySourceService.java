package org.geilove.service;
//所有涉及到moneysource 表的都放到这里面来

import java.util.List;
import java.util.Map;
import  org.geilove.pojo.MoneySource;
import org.geilove.pojo.MoneysrcPinglun;
import org.geilove.requestParam.BackUpParam;
import org.geilove.response.CommonRsp;
import org.geilove.sqlpojo.PartHelpPojo;
public interface MoneySourceService {
       // public   List<PartHelpPojo> getIhelpMen( Map<String,Object> map); //获取我帮助的人
       //public   List<PartHelpPojo> getHelpMeMen( Map<String,Object> map);//获取帮助我的人

        public  void wxNotify(MoneySource  moneySource); //用户支持一次的方法

        //获取完整的，"支持了" 列表通过id，已废弃
        public  List<MoneySource> getGuyHelpMe(Map<String,Object>  map); //获取帮助我的人

        //获取完整的，"支持了" 列表，通过uuid，待完成,这里应该用MoneySource的子集代替
        public  List<MoneySource> getGuyHelpMeByCashUUID(Map<String,Object>  map); //获取帮助我的人


        //根据MoneySourceID 获取其对应的评论
        public  List<MoneysrcPinglun> getMoneySourcePingLuns(Map<String,Object>  map); //获取一条"支持了"的评论列表

        //"支持了" 也就是用户捐钱一次
        //public CommonRsp addBackupService(BackUpParam backUpParam); //这个要加入事务支持

        //对"支持了" 进行一次评论
        public  CommonRsp addOneComment(MoneysrcPinglun  moneysrcPinglun) ;

        //删除 "支持了" 的评论
        public  CommonRsp deleteOneComment(Long  moneySrcPingluniD );

}
