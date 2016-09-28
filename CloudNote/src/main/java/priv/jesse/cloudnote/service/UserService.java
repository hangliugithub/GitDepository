package priv.jesse.cloudnote.service;

import java.io.Serializable;

import priv.jesse.cloudnote.entity.User;

public interface UserService extends Serializable{
	/**
	 * ��¼����
	 * @param name �û���
	 * @param password ����
	 * @return ��¼�ɹ�ʱ�����û���Ϣ
	 * @throws NameOrPasswordException �û������������
	 * 			�û���������Ϊ��
	 */
	User login(String name,String password) throws NameOrPasswordException;
	/**
	 * ע��
	 * @param name
	 * @param password
	 * @param nick �ǳ�
	 * @return ����ע��ɹ����û���Ϣ
	 * @throws UserExistException �û��Ѵ���
	 */
	User regist(String name, String password, String nick) throws UserExistException;
	
	boolean checkPassword(String userId,String password) throws NameOrPasswordException;
	
	User changePassword(String userId,String password,String newPassword) throws NameOrPasswordException,ServiceException;
	
}
