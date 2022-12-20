package edu.jit.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.jit.service.UserService;
import edu.jit.utils.CheckNullUtils;

/**
 * Servlet implementation class delstudent
 */
@WebServlet("/delstudent")
public class DelStudent extends HttpServlet {
	private UserService userService=new UserService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String rsUsername=request.getParameter("rsusername");
		if(CheckNullUtils.isNull(rsUsername)) {
//			校验不通过，将提示信息存入到request域
			request.setAttribute("a2", "用户名不能为空");
//			通过请求转发回到注册页面，并取出域中的消息进行提示
			request.getRequestDispatcher("/web/adminpersonage.jsp").forward(request, response);
//			请求转发前后的代码会正常执行的，所以应该在校验为空时结束方法的执行
			return;
		}
		int num = userService.del(rsUsername);
		if(num==0) {
			request.setAttribute("a2", "删除联系人失败");
			request.getRequestDispatcher("/web/adminpersonage.jsp").forward(request, response);
			return;
		}else {
			request.setAttribute("a2", "删除联系人成功");
			request.getRequestDispatcher("/web/adminpersonage.jsp").forward(request, response);
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
