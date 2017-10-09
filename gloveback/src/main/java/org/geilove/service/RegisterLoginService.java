package org.geilove.service;

import org.geilove.pojo.User;

import java.util.Map;

public interface RegisterLoginService {
	
	public User userLogin(String userEmail);
	
	public User byAtUserProfile(String usernickname);
	
	public int userRegister(User record);
	
	public String selectMD5Password(Long userid);
	
	public String findPasswd(String userEmail);
	
	public int updateUserSelective(User record);

	public User selectByNicknameOrEmail(Map<String,Object> map); //根据昵称或者邮箱检查用户是否注册
}
