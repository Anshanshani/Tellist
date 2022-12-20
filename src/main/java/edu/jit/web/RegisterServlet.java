package edu.jit.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.jit.beans.User;
import edu.jit.service.UserService;
import edu.jit.utils.CheckNullUtils;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
	private UserService userService=new UserService();
	/**
	 * 实现获取表单信息，并校验用户信息
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		1、请求和响应乱码的处理
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
//		2、获取表单中的请求参数：根据input标签的name属性获取
		String job = request.getParameter("job");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String passwordconfirm = request.getParameter("passwordconfirm");
		String phone = request.getParameter("phone");
//		3、不论前端是否做了校验，后端一定要自行进行数据的非空校验——调用工具类CheckNullUtils
		if(CheckNullUtils.isNull(username)) {
//			校验不通过，将提示信息存入到request域
			request.setAttribute("message", "用户名不能为空");
//			通过请求转发回到注册页面，并取出域中的消息进行提示
			request.getRequestDispatcher("/web/register.jsp").forward(request, response);
//			请求转发前后的代码会正常执行的，所以应该在校验为空时结束方法的执行
			return;
		}
		if(CheckNullUtils.isNull(password)) {
			request.setAttribute("message", "密码不能为空");
			request.getRequestDispatcher("/web/register.jsp").forward(request, response);
			return;
		}
		if(CheckNullUtils.isNull(passwordconfirm)) {
			request.setAttribute("message", "确认密码不能为空");
			request.getRequestDispatcher("/web/register.jsp").forward(request, response);
			return;
		}
		if(CheckNullUtils.isNull(phone)) {
			request.setAttribute("message", "手机号不能为空");
			request.getRequestDispatcher("/web/register.jsp").forward(request, response);
			return;
		}
//		4、密码和确认密码的一致性校验
		if(password!=null&&passwordconfirm!=null& !password.equals(passwordconfirm)) {
			request.setAttribute("message", "两次密码不一致");
			request.getRequestDispatcher("/web/register.jsp").forward(request, response);
			return;
		}

		
//	5、以上校验都通过，完成注册
		User user1=userService.queryByUserName(username);
		if(user1!=null) {
			request.setAttribute("message","用户已存在");
			request.getRequestDispatcher("/web/register.jsp").forward(request, response);
			return  ;
		}
		User user2=userService.queryByPhone(phone);
		if(user2!=null) {
			request.setAttribute("message","手机号已存在");
			request.getRequestDispatcher("/web/register.jsp").forward(request, response);
			return  ;
		}
	
	int num=userService.insertUser(job,username,password,phone);
	if(num==0) {
		request.setAttribute("message","注册失败");
		request.getRequestDispatcher("/web/register.jsp").forward(request, response);
		return  ;
	}
	response.getWriter().write("<h1 align='center'><font color='pink'>恭喜注册成功，3秒后跳转登录页</font></h1>");
	response.setHeader("refresh","3;url=http://localhost/StudentPhone/web/login.jsp");

	}
	/**
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
