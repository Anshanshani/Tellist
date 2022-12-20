package edu.jit.service;

import edu.jit.beans.User;
import edu.jit.dao.UserDao;

public class UserService {
	// ��������Ҫʹ��dao�㣬����UserDao���͵�����
		private UserDao userDao=new UserDao();
		// �����û�����ѯ�û�
		public User queryByUserName(String username) {
			// ����UserDao�ķ���
		return userDao.queryByUsername(username);
		}
		public User queryByPhone(String phone) {
			// ����UserDao�ķ���
		return userDao.queryByPhone(phone);
		}
		public User loginUser(String username,String password) {
			// ����UserDao�ķ���
		return userDao.queryByUnameAndPWD(username,password);
		}
		
		public User queryByID(int id) {
			return userDao.queryByID(id);
		}
		
		
		// �û�ע��
		public int insertUser(String job,String username,String password,String phone) {
			// ����UserDao�ķ���
		return userDao.insertUser(job,username, password, phone);
		}
		
		//�û��޸�
		public int updataUser(User user) {
			// TODO Auto-generated method stub
			return userDao.updataUser(user);
		}
		public int updataPassword(String password, int id) {
			return userDao.updataPassword(password,id);
		}
		
		public int alter(String job,String username ,String password,String gender,String phone,String address) {
			// TODO Auto-generated method stub
			return userDao.alter(job,username ,password,gender,phone,address);
		}
		public int add(String job,String username ,String password,String gender,String phone,String address) {
			// TODO Auto-generated method stub
			return userDao.add(job,username ,password,gender,phone,address);
		}
		public int del(String username) {
			// TODO Auto-generated method stub
			return userDao.del(username);
		}
}
