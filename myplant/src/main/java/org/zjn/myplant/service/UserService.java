package org.zjn.myplant.service;

public interface UserService {
	/*
	 * �û�ע�ᣬ�����û�������Ϣ
	 * return: -1��ע��ʧ�ܣ����򷵻��û�id
	 */
	int userRegister(String userName, String userPasswd);
	
	/*
	 * �û���¼����֤�û��������Ϣ
	 * return��-1����¼ʧ�ܣ����򷵻��û�id
	 */
	int userLogin(String userName, String userPasswd);
	
	/*
	 * �û�ע���豸��Ϣ
	 * return��-1��ע��ʧ�ܣ�1��ע��ɹ�
	 */
	int userRegisterDevice(int deviceId, int plantId, int userId);
	
	/*
	 * ��ѯ�û�id
	 * return: userId
	 */
	int queryUserId(String userName);
}
