package org.zjn.myplant.service;

public interface UserService {
	/*
	 * 用户注册，插入用户基本信息
	 * return: -1，注册失败；否则返回用户id
	 */
	int userRegister(String userName, String userPasswd);
	
	/*
	 * 用户登录，验证用户输入的信息
	 * return：-1，登录失败；否则返回用户id
	 */
	int userLogin(String userName, String userPasswd);
	
	/*
	 * 用户注册设备信息
	 * return：-1，注册失败；1，注册成功
	 */
	int userRegisterDevice(int deviceId, int plantId, int userId);
	
	/*
	 * 查询用户id
	 * return: userId
	 */
	int queryUserId(String userName);
}
