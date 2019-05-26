package org.zjn.myplant.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zjn.myplant.entity.User;

public interface UserDao {
	//用户注册，插入新用户信息
	int newUser(@Param("userName")String userName, @Param("userPasswd")String userPasswd, @Param("userPhone")String userPhone, @Param("userSex") int userSex);
	//用户登录，查询用户信息
	User queryById(int userId);
	
	//用户登录，根据电话号码查询用户信息
	User queryByPhone(String userPhone);
	
	//查询全部用户
	List<User> queryAll();
	
	//删除用户
	int deleteUser(int userId);
	
	//修改用户密码
	int changePassword(@Param("userId")int userId, @Param("userPassword")String userPassword);

}
