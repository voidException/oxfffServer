package org.geilove.service.impl;

import org.geilove.pojo.HelpInfo;
import org.geilove.pojo.User;
import org.geilove.requestParam.SubOpenIDEmailParam;
import org.geilove.service.HelpInfoService;
import org.geilove.service.LinkService;
import org.geilove.service.RegisterLoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by aihaitao on 30/5/2017.
 */
@Service("linkService")
public class LinkServiceImpl implements LinkService{
    @Resource
    private RegisterLoginService rlService;
    @Resource
    private HelpInfoService helpInfoService;

    public  void linkEmail(SubOpenIDEmailParam subOpenIDEmailParam){
        String email=subOpenIDEmailParam.getEmail();
        String token=subOpenIDEmailParam.getToken();
        String openId=subOpenIDEmailParam.getOpenId();
        String userUUID="";
        int userDonate=subOpenIDEmailParam.getUsertotaldonate();
        int userHelpTimes=subOpenIDEmailParam.getUsertotalhelp();

        //验证email ，通过后，把数据写入到User表 和 HelpInfo 表中
        if(token.length()<33){
            return ;
        }
        String userPassword=token.substring(0,32); //token是password和userID拼接成的。
        String useridStr=token.substring(32); //取得userid部分
        Long userid=Long.valueOf(useridStr).longValue();  //转换成long类型
        String passwdTrue=null;
        try {
            passwdTrue=rlService.selectMD5Password(Long.valueOf(userid));
            if(!userPassword.equals(passwdTrue)){
                return ;
            }
        }catch (Exception e){
            return;
        }
        //验证通过了，用email去查user表，然后用email查表user
        User user=null;
        try{
            user=rlService.userLogin(email);
            if (user!=null){
                user.setOpenid(openId);
                user.setUserhelpsman(userHelpTimes);
                user.setUserdonate(userDonate);
                userUUID=user.getBackupfour();
            }
        }catch (Exception e){

        }
       //更新HelpInfo表
        HelpInfo helpInfo=new HelpInfo();
        helpInfo.setUseruuid(userUUID);
        helpInfo.setDonatemount(subOpenIDEmailParam.getUsertotaldonate());
        if (userHelpTimes!=0){
            userHelpTimes=userHelpTimes+1;
        }
        try {
            int helpInfoTag=helpInfoService.updateRecord(helpInfo);
        }catch (Exception e){


        }
    }

}
