package edu.jit.web;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.jit.beans.User;
import edu.jit.service.UserService;
import edu.jit.utils.CheckNullUtils;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private UserService userService=new UserService();
       
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		1、请求和响应乱码的处理
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
//		2、获取表单中的请求参数：根据input标签的name属性获取
		String job = request.getParameter("job");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
//		3、请求参数非空校验
		if(CheckNullUtils.isNull(username)) {
//			校验不通过，将提示信息存入到request域
			request.setAttribute("message", "用户名不能为空");
//			通过请求转发回到注册页面，并取出域中的消息进行提示
			request.getRequestDispatcher("/web/login.jsp").forward(request, response);
//			请求转发前后的代码会正常执行的，所以应该在校验为空时结束方法的执行
			return;
		}
		if(CheckNullUtils.isNull(password)) {
			request.setAttribute("message", "密码不能为空");
			request.getRequestDispatcher("/web/login.jsp").forward(request, response);
			return;
		}
	
//          4.实现登录功能
		User user=userService.loginUser(username,password);
		if(user==null||job.equals(user.getJob())!=true) {
			request.setAttribute("message","用户名或密码错误");
			request.getRequestDispatcher("/web/login.jsp").forward(request, response);
			return;
		}
		
//         5.登入成功，重定向到首页；并且通过session域共享已登录用户信息
		request.getSession().setAttribute("user",user);
		response.sendRedirect("http://localhost/StudentPhone//web/index.jsp");
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
