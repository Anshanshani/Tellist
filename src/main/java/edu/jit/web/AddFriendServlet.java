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
 * Servlet implementation class FriendServlet
 */
@WebServlet("/friendServlet")
public class AddFriendServlet extends HttpServlet {
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
		String username1 = request.getParameter("username1");
	
		
//		3、请求参数非空校验
		if(CheckNullUtils.isNull(username1)) {
//			校验不通过，将提示信息存入到request域
			request.setAttribute("message", "添加用户名不能为空");
//			通过请求转发回到注册页面，并取出域中的消息进行提示
			request.getRequestDispatcher("/web/friend.jsp").forward(request, response);
//			请求转发前后的代码会正常执行的，所以应该在校验为空时结束方法的执行
			return;
		}
		
	
//          
		User user=userService.queryByUserName(username1);
		
		if(user!=null) {
			request.getSession().setAttribute("user1",user);
			request.getSession().setAttribute("user2",user.GetUsername());
		}
		
		if(user==null) {
			request.setAttribute("message","用户名不存在");
			request.getRequestDispatcher("/web/friend.jsp").forward(request, response);
			return;
		}
		else {
			request.setAttribute("message","添加成功");
			request.getRequestDispatcher("/web/friend.jsp").forward(request, response);
			return;	
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
