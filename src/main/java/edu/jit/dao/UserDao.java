package edu.jit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import edu.jit.beans.User;
import edu.jit.utils.JDBCUtils;

public class UserDao {
	public User queryByUsername(String username) {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;	
	try {
		conn = JDBCUtils.getConn();
		ps = conn.prepareStatement("select * from user where username = ?");
		ps.setString(1, username);
		rs = ps.executeQuery();
		if(rs.next()) {//用户名存在，不能注册
			User user = new User();
			 user.setJob(rs.getString("job"));
			 user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			 user.setGender(rs.getString("gender"));
			user.setPhone(rs.getString("phone"));
			 user.setAddress(rs.getString("address"));
			return user;
	}else {
		return null;
	}}catch (Exception e) {
	e.printStackTrace();
	// 处理异常
	throw new RuntimeException();
	}finally {
		JDBCUtils.close(conn, ps, rs);
	}
	}
	
	
	
	
	
	public User queryByPhone(String phone) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;	
		try {
			conn = JDBCUtils.getConn();
			ps = conn.prepareStatement("select * from user where phone = ?");
			ps.setString(1, phone);
			rs = ps.executeQuery();
			if(rs.next()) {//手机号存在，不能注册
				User user = new User();
				 user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setPhone(rs.getString("phone"));
				user.setAddress(rs.getString("address"));
				return user;
		}else {
			return null;
		}}catch (Exception e) {
		e.printStackTrace();
		// 处理异常
		throw new RuntimeException();
		}finally {
			JDBCUtils.close(conn, ps, rs);
		}
		}
	
	public User queryByUnameAndPWD(String username, String password) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
		conn = JDBCUtils.getConn();
		ps = conn.prepareStatement("select * from user where username = ? and password=?");
		ps.setString(1, username);
		ps.setString(2, password);
		rs = ps.executeQuery();
		if(rs.next()) {//用户存在，完成登录
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setJob(rs.getString("job"));
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password"));
		user.setPhone(rs.getString("phone"));
		return user;
		}else {
			return null;
		}
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
			
			}finally {
		
				JDBCUtils.close(conn, ps, rs);
			}
		}
	
	
	
	public User queryByID(int id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
		conn = JDBCUtils.getConn();
		ps = conn.prepareStatement("select * from user where id = ? ");
		ps.setInt(1, id);
		rs = ps.executeQuery();
		if(rs.next()) {//用户存在，完成登录
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password"));
		user.setPhone(rs.getString("phone"));
		user.setAddress(rs.getString("address"));
		return user;
		}else {
			return null;
		}
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
			
			}finally {
		
				JDBCUtils.close(conn, ps, rs);
			}
		}
	
	
	//插入用户
		public int insertUser(String job,String username ,String password,String phone) {
			Connection conn = null;
			PreparedStatement ps = null;
			try {
		conn = JDBCUtils.getConn();
		ps = conn.prepareStatement("insert into user(id,job,username,password,phone) values(null,?,?,?,?)");
		ps.setString(1, job);
		ps.setString(2, username);
		ps.setString(3, password);
		ps.setString(4, phone);
		int num = ps.executeUpdate();
		return num;
		}catch (Exception e) {
		e.printStackTrace();
		throw new RuntimeException();
		}finally {
			JDBCUtils.close(conn, ps, null);
		}
		
		
		}
		
		
		//修改用户
		public int updataUser(User user) {
			Connection conn = null;
			PreparedStatement ps = null;
			try {
		conn = JDBCUtils.getConn();
		ps = conn.prepareStatement("update user set username=?,gender=?,phone=?,address=?where id=?");
		ps.setString(1, user.getUsername());
		ps.setString(2, user.getGender());
		ps.setString(3, user.getPhone());
		ps.setString(4, user.getAddress());
		ps.setInt(5, user.getId());
		int num = ps.executeUpdate();
		return num;
		}catch (Exception e) {
		e.printStackTrace();
		throw new RuntimeException();
		}finally {
			JDBCUtils.close(conn, ps, null);
		}
			
			
		
		
		}
		public int updataPassword(String password, int id) {
			Connection conn = null;
			PreparedStatement ps = null;
			try {
		conn = JDBCUtils.getConn();
		ps = conn.prepareStatement("update user set password=?where id=?");
		ps.setString(1,password);
		ps.setInt(2, id);
		int num = ps.executeUpdate();
		return num;
		}catch (Exception e) {
		e.printStackTrace();
		throw new RuntimeException();
		}finally {
			JDBCUtils.close(conn, ps, null);
		}
			
			
		
		
		}
		
		

		//zengjia
		public int alter(String job,String username ,String password,String gender,String phone,String address) {
			Connection conn = null;
			PreparedStatement ps = null;
			try {
		conn = JDBCUtils.getConn();
		ps = conn.prepareStatement("update user set job=?,password=?,gender=?,phone=?,address=?where username=?");
		ps.setString(1, job);
		
		ps.setString(2, password);
		ps.setString(3, gender);
		ps.setString(4, phone);
		ps.setString(5, address);
		ps.setString(6, username);
		int num = ps.executeUpdate();
		return num;
		}catch (Exception e) {
		e.printStackTrace();
		throw new RuntimeException();
		}finally {
			JDBCUtils.close(conn, ps, null);
		}
		
		
		}
		public int add(String job,String username ,String password,String gender,String phone,String address) {
			Connection conn = null;
			PreparedStatement ps = null;
			try {
		conn = JDBCUtils.getConn();
		ps = conn.prepareStatement("insert into user(id,job,username,password,gender,phone,address) values(null,?,?,?,?,?,?)");
		ps.setString(1, job);
		ps.setString(2, username);
		ps.setString(3, password);
		ps.setString(4, gender);
		ps.setString(5, phone);
		ps.setString(6, address);
		
		int num = ps.executeUpdate();
		return num;
		}catch (Exception e) {
		e.printStackTrace();
		throw new RuntimeException();
		}finally {
			JDBCUtils.close(conn, ps, null);
		}
		
		
		}
		

		public static ArrayList getLikeList(){
			ArrayList  tag_Array = new ArrayList();
			Connection conn = null;
			
			PreparedStatement ps = null;
			String sql ="select * from user";
			try {
				conn = JDBCUtils.getConn();
				ps =conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					User tag =new User();
					tag.setId(rs.getInt("id"));
					tag.setJob(rs.getString("job"));
					tag.setUsername(rs.getString("username"));
					tag.setPassword(rs.getString("password"));
					tag.setGender(rs.getString("gender"));
					tag.setPhone(rs.getString("phone"));
					tag.setAddress(rs.getString("address"));
					tag_Array.add(tag);
				}
				rs.close();
				ps.close();
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			return tag_Array;
		}
		
		public static ArrayList getLikeList(String rsUsername) {
			ArrayList  tag_Array = new ArrayList ();//建立一个数组集合
			Connection conn=null;
			String sql = "select * from user where username=?";
			PreparedStatement ps = null;
			ResultSet rs = null;
				try {
					conn = JDBCUtils.getConn();
					ps =conn.prepareStatement(sql);
					ps.setString(1, rsUsername);
					rs = ps.executeQuery();
					
					while(rs.next()) {
						User tag = new User();
						tag.setId(rs.getInt("id"));
						tag.setJob(rs.getString("job"));
						tag.setUsername(rs.getString("username"));
						tag.setPassword(rs.getString("password"));
						tag.setGender(rs.getString("gender"));
						tag.setPhone(rs.getString("phone"));
						tag.setAddress(rs.getString("address"));
						tag_Array.add(tag);
				} 
					
					rs.close();
					ps.close();
				}catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			return tag_Array;
		}
		
		
		public int del(String username) {
			Connection conn = null;
			PreparedStatement ps = null;
			try {
		conn = JDBCUtils.getConn();
		ps = conn.prepareStatement("delete from user where username=?");
		
		ps.setString(1, username);
		

		int num = ps.executeUpdate();
		return num;
		}catch (Exception e) {
		e.printStackTrace();
		throw new RuntimeException();
		}finally {
			JDBCUtils.close(conn, ps, null);
		}
		
		
		}
		
}
