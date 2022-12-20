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
//		1���������Ӧ����Ĵ���
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
//		2����ȡ���е��������������input��ǩ��name���Ի�ȡ
		String username2 = request.getParameter("username2");
	
		
//		3����������ǿ�У��
		if(CheckNullUtils.isNull(username2)) {
//			У�鲻ͨ��������ʾ��Ϣ���뵽request��
			request.setAttribute("message", "��ѯ�û�������Ϊ��");
//			ͨ������ת���ص�ע��ҳ�棬��ȡ�����е���Ϣ������ʾ
			request.getRequestDispatcher("/web/friend.jsp").forward(request, response);
//			����ת��ǰ��Ĵ��������ִ�еģ�����Ӧ����У��Ϊ��ʱ����������ִ��
			return;
		}
	      
			String user=(String) request.getSession().getAttribute("user2");
			
			if(user.equals(username2)!=true) {
				request.setAttribute("message","��ѯ�����û���������");
				request.getRequestDispatcher("/web/friend.jsp").forward(request, response);
				return;
			}
			response.getWriter().write("<h1 align='center'><font color='pink'>��ϲ��ѯ�ɹ���3�����ת������Ϣҳ</font></h1>");
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
