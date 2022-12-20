package edu.jit.service;

import edu.jit.beans.User;
import edu.jit.dao.UserDao;

public class UserService {
	// 访问数据要使用dao层，创建UserDao类型的属性
		private UserDao userDao=new UserDao();
		// 根据用户名查询用户
		public User queryByUserName(String username) {
			// 调用UserDao的方法
		return userDao.queryByUsername(username);
		}
		public User queryByPhone(String phone) {
			// 调用UserDao的方法
		return userDao.queryByPhone(phone);
		}
		public User loginUser(String username,String password) {
			// 调用UserDao的方法
		return userDao.queryByUnameAndPWD(username,password);
		}
		
		public User queryByID(int id) {
			return userDao.queryByID(id);
		}
		
		
		// 用户注册
		public int insertUser(String job,String username,String password,String phone) {
			// 调用UserDao的方法
		return userDao.insertUser(job,username, password, phone);
		}
		
		//用户修改
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
