package edu.jit.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.jit.beans.User;
import edu.jit.dao.UserDao;
import edu.jit.service.UserService;

/**
 * Servlet implementation class changestudent
 */
@WebServlet("/querystudent")
public class QueryStudent extends HttpServlet {
	private UserService UserService=new UserService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// 接收请求参数
		String rsUsername=request.getParameter("rsusername");
		User user=UserService.queryByUserName(rsUsername);
		
		ArrayList a;
		if(rsUsername.equals("")) {
			a=UserDao.getLikeList();
			request.setAttribute("a2", "已展示所有联系人");
			request.getSession().setAttribute("stumessage", a);
			request.getRequestDispatcher("/web/adminpersonage.jsp").forward(request, response);
			return;
		}else {
			if(user==null) {
				a=UserDao.getLikeList(rsUsername);
				request.setAttribute("a2", "未找到指定联系人");
				request.getSession().setAttribute("stumessage", a);
				request.getRequestDispatcher("/web/adminpersonage.jsp").forward(request, response);
				return;
			}else {
			a=UserDao.getLikeList(rsUsername);
			request.setAttribute("a2", "查询联系人成功");
			request.getSession().setAttribute("stumessage", a);
			request.getRequestDispatcher("/web/adminpersonage.jsp").forward(request, response);
			return;
			}
		}
		
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
