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
 * Servlet implementation class changestudent
 */
@WebServlet("/changestudent")
public class ChangeStudent extends HttpServlet {
	private UserService userService=new UserService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// 接收请求参数
		String job=request.getParameter("job");
		String rsUsername=request.getParameter("rs_username");
		String rsPassword=request.getParameter("rs_password");
		String rsGender=request.getParameter("rs_gender");
		String rsPhone=request.getParameter("rs_phone");
		String rsAddress=request.getParameter("rs_address");
		if(CheckNullUtils.isNull(rsUsername)) {
//			校验不通过，将提示信息存入到request域
			request.setAttribute("a1", "用户名不能为空");
//			通过请求转发回到注册页面，并取出域中的消息进行提示
			request.getRequestDispatcher("/web/adminpersonage.jsp").forward(request, response);
//			请求转发前后的代码会正常执行的，所以应该在校验为空时结束方法的执行
			return;
		}
				int num = userService.alter(job,rsUsername,rsPassword,rsGender,rsPhone,rsAddress);
				if(num==0) {
					request.setAttribute("a1", "修改联系人失败");
					request.getRequestDispatcher("/web/adminpersonage.jsp").forward(request, response);
					return;
				}else {
					request.setAttribute("a1", "修改联系人成功");
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
