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

/**
 * Servlet implementation class ChangeSrvlet
 */
@WebServlet("/changeServlet")
public class ChangeServlet extends HttpServlet {
	private UserService userService=new UserService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// 接收请求参数
		int id = Integer.parseInt(request.getParameter("id"));
		String job=request.getParameter("job");
		String rsUsername=request.getParameter("rs_username");
		String rsGender=request.getParameter("rs_gender");
		String rsPhone=request.getParameter("rs_phone");
		String rsAddress=request.getParameter("rs_address");
		// 非空校验
		if(CheckNullUtils.isNull(rsUsername)) {
			request.setAttribute("message", "用户名不能为空");
			request.getRequestDispatcher("/web/studentpersonage.jsp").forward(request, response);
			return;
		}
		if(CheckNullUtils.isNull(rsPhone)) {
			request.setAttribute("message", "手机号不能为空");
			request.getRequestDispatcher("/web/studentpersonage.jsp").forward(request, response);
			return;
		}
		if(CheckNullUtils.isNull(rsAddress)) {
			request.setAttribute("message", "用户名不能为空");
			request.getRequestDispatcher("/web/studentpersonage.jsp").forward(request, response);
			return;
		// 封装数据，在User类中新建对应的构造方法
		}
			User user = new User(id,rsUsername,rsGender,rsPhone,rsAddress);
		User user1=userService.queryByUserName(rsUsername);
		if(user1!=null) {
			request.setAttribute("message","用户已存在");
			request.getRequestDispatcher("/web/studentpersonage.jsp").forward(request, response);
			return  ;
		}
		User user2=userService.queryByPhone(rsPhone);
		if(user2!=null) {
			request.setAttribute("message","电话已存在");
			request.getRequestDispatcher("/web/studentpersonage.jsp").forward(request, response);
			return  ;
		}
	
		// 调用userService中的更新方法
		int num = userService.updataUser(user);
		if(num==0) {
			request.setAttribute("message", "更新失败");
			request.getRequestDispatcher("/web/personage.jsp").forward(request, response);
			return;
		}
		// 将更新后的user对象存储到session域中
		request.getSession().setAttribute("user", user);
		// 然后请求重定向回到personage.jsp页面
		response.sendRedirect("http://localhost/StudentPhone/web/studentpersonage.jsp");
		}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
