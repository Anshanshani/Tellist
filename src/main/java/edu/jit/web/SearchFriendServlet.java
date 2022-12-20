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
 * Servlet implementation class SearchFriendServlet
 */
@WebServlet("/searchServlet")
public class SearchFriendServlet extends HttpServlet {
	private UserService userService=new UserService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
//		1、请求和响应乱码的处理
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
//		2、获取表单中的请求参数：根据input标签的name属性获取
		String username2 = request.getParameter("username2");
	
		
//		3、请求参数非空校验
		if(CheckNullUtils.isNull(username2)) {
//			校验不通过，将提示信息存入到request域
			request.setAttribute("message", "查询用户名不能为空");
//			通过请求转发回到注册页面，并取出域中的消息进行提示
			request.getRequestDispatcher("/web/friend.jsp").forward(request, response);
//			请求转发前后的代码会正常执行的，所以应该在校验为空时结束方法的执行
			return;
		}
	      
			String user=(String) request.getSession().getAttribute("user2");
			
			if(user.equals(username2)!=true) {
				request.setAttribute("message","查询好友用户名不存在");
				request.getRequestDispatcher("/web/friend.jsp").forward(request, response);
				return;
			}
			response.getWriter().write("<h1 align='center'><font color='pink'>恭喜查询成功，3秒后跳转好友信息页</font></h1>");
			response.setHeader("refresh","3;url=http://localhost/StudentPhone/web/friendpersonage.jsp");
		
		
		
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
