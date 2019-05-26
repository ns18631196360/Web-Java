package org.zjn.myplant.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zjn.myplant.entity.User;

public interface UserDao {
	//�û�ע�ᣬ�������û���Ϣ
	int newUser(@Param("userName")String userName, @Param("userPasswd")String userPasswd, @Param("userPhone")String userPhone, @Param("userSex") int userSex);
	//�û���¼����ѯ�û���Ϣ
	User queryById(int userId);
	
	//�û���¼�����ݵ绰�����ѯ�û���Ϣ
	User queryByPhone(String userPhone);
	
	//��ѯȫ���û�
	List<User> queryAll();
	
	//ɾ���û�
	int deleteUser(int userId);
	
	//�޸��û�����
	int changePassword(@Param("userId")int userId, @Param("userPassword")String userPassword);

}
