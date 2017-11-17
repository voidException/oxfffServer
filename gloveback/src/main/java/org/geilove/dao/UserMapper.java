package org.geilove.dao;

import java.util.List;
import  java.util.Map;
import org.geilove.pojo.User;
import org.geilove.sqlpojo.PeopleNeedLovePojo;
import org.geilove.sqlpojo.LoveClubListPojo;
import org.geilove.sqlpojo.DonaterPojo;
import org.geilove.sqlpojo.OtherPartHelpPojo;

public interface UserMapper {
    int deleteByPrimaryKey(Long userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByUUID(String userUUID);
    
    User selectByPrimaryKey(Long userid);
    
    User selectByUserEmail(String useremail);//自定义，根据用户的邮箱进行验证

    User selectByopenId(String openId); // 根据公众号id选择出用户

    User selectByUserNickName(String usernickname); //这个形参要与xml中的参数保持一致

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User>  getPayOrWatchPeople(List<Long> ll); //查询关注我 我关注的人列表,查询我帮助或者帮助我的人列表

    List<OtherPartHelpPojo> selectUserPartProfile(List<Long> lst); //给定一组id，获取用户的头像昵称简介
    
    String selectMD5Password(Long userid);
    
    String findPasswd(String userEmail);
    
    List<User> selectMenListLoadMore(Map<String,Object> map); ////1普通，2社团，3监督，4志愿者等列表

    List<User>  gongyiMenListloadMore(Map<String,Object> map);//公益排行榜，loadMore

    User selectByNicknameOrEmail(Map<String,Object> map); //根据用户的昵称或者邮箱选择出一个用户，用于注册时检查是否已有注册用户

    ////********************************以下是手机号********************************************///
    User getUserByPhone(String phone); //根据用户手机号选择用户
    User getUserByPhonePass(Map<String,Object> map); //根据手机号和加密的密码，查询出用户
    int  updateByPhone(User user);
    int  updateByUnionid(User user);
    //////////////---------根据unionid 选择出用户--------////////////////////////////////
    User getUserByunionid(String  unionid); //通过微信的unionid选择出用户
    User selectByNicknameOrPhone(Map<String,Object> map); //根据用户的昵称或者邮箱选择出一个用户，用于注册时检查是否已有注册用户

}




