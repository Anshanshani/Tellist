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
//		1���������Ӧ����Ĵ���
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
//		2����ȡ���е��������������input��ǩ��name���Ի�ȡ
		String job = request.getParameter("job");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
//		3����������ǿ�У��
		if(CheckNullUtils.isNull(username)) {
//			У�鲻ͨ��������ʾ��Ϣ���뵽request��
			request.setAttribute("message", "�û�������Ϊ��");
//			ͨ������ת���ص�ע��ҳ�棬��ȡ�����е���Ϣ������ʾ
			request.getRequestDispatcher("/web/login.jsp").forward(request, response);
//			����ת��ǰ��Ĵ��������ִ�еģ�����Ӧ����У��Ϊ��ʱ����������ִ��
			return;
		}
		if(CheckNullUtils.isNull(password)) {
			request.setAttribute("message", "���벻��Ϊ��");
			request.getRequestDispatcher("/web/login.jsp").forward(request, response);
			return;
		}
	
//          4.ʵ�ֵ�¼����
		User user=userService.loginUser(username,password);
		if(user==null||job.equals(user.getJob())!=true) {
			request.setAttribute("message","�û������������");
			request.getRequestDispatcher("/web/login.jsp").forward(request, response);
			return;
		}
		
//         5.����ɹ����ض�����ҳ������ͨ��session�����ѵ�¼�û���Ϣ
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
