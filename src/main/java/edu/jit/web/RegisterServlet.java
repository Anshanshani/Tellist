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
	 * ʵ�ֻ�ȡ����Ϣ����У���û���Ϣ
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		1���������Ӧ����Ĵ���
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
//		2����ȡ���е��������������input��ǩ��name���Ի�ȡ
		String job = request.getParameter("job");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String passwordconfirm = request.getParameter("passwordconfirm");
		String phone = request.getParameter("phone");
//		3������ǰ���Ƿ�����У�飬���һ��Ҫ���н������ݵķǿ�У�顪�����ù�����CheckNullUtils
		if(CheckNullUtils.isNull(username)) {
//			У�鲻ͨ��������ʾ��Ϣ���뵽request��
			request.setAttribute("message", "�û�������Ϊ��");
//			ͨ������ת���ص�ע��ҳ�棬��ȡ�����е���Ϣ������ʾ
			request.getRequestDispatcher("/web/register.jsp").forward(request, response);
//			����ת��ǰ��Ĵ��������ִ�еģ�����Ӧ����У��Ϊ��ʱ����������ִ��
			return;
		}
		if(CheckNullUtils.isNull(password)) {
			request.setAttribute("message", "���벻��Ϊ��");
			request.getRequestDispatcher("/web/register.jsp").forward(request, response);
			return;
		}
		if(CheckNullUtils.isNull(passwordconfirm)) {
			request.setAttribute("message", "ȷ�����벻��Ϊ��");
			request.getRequestDispatcher("/web/register.jsp").forward(request, response);
			return;
		}
		if(CheckNullUtils.isNull(phone)) {
			request.setAttribute("message", "�ֻ��Ų���Ϊ��");
			request.getRequestDispatcher("/web/register.jsp").forward(request, response);
			return;
		}
//		4�������ȷ�������һ����У��
		if(password!=null&&passwordconfirm!=null& !password.equals(passwordconfirm)) {
			request.setAttribute("message", "�������벻һ��");
			request.getRequestDispatcher("/web/register.jsp").forward(request, response);
			return;
		}

		
//	5������У�鶼ͨ�������ע��
		User user1=userService.queryByUserName(username);
		if(user1!=null) {
			request.setAttribute("message","�û��Ѵ���");
			request.getRequestDispatcher("/web/register.jsp").forward(request, response);
			return  ;
		}
		User user2=userService.queryByPhone(phone);
		if(user2!=null) {
			request.setAttribute("message","�ֻ����Ѵ���");
			request.getRequestDispatcher("/web/register.jsp").forward(request, response);
			return  ;
		}
	
	int num=userService.insertUser(job,username,password,phone);
	if(num==0) {
		request.setAttribute("message","ע��ʧ��");
		request.getRequestDispatcher("/web/register.jsp").forward(request, response);
		return  ;
	}
	response.getWriter().write("<h1 align='center'><font color='pink'>��ϲע��ɹ���3�����ת��¼ҳ</font></h1>");
	response.setHeader("refresh","3;url=http://localhost/StudentPhone/web/login.jsp");

	}
	/**
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
