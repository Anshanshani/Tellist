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
//		1���������Ӧ����Ĵ���
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
//		2����ȡ���е��������������input��ǩ��name���Ի�ȡ
		String username1 = request.getParameter("username1");
	
		
//		3����������ǿ�У��
		if(CheckNullUtils.isNull(username1)) {
//			У�鲻ͨ��������ʾ��Ϣ���뵽request��
			request.setAttribute("message", "����û�������Ϊ��");
//			ͨ������ת���ص�ע��ҳ�棬��ȡ�����е���Ϣ������ʾ
			request.getRequestDispatcher("/web/friend.jsp").forward(request, response);
//			����ת��ǰ��Ĵ��������ִ�еģ�����Ӧ����У��Ϊ��ʱ����������ִ��
			return;
		}
		
	
//          
		User user=userService.queryByUserName(username1);
		
		if(user!=null) {
			request.getSession().setAttribute("user1",user);
			request.getSession().setAttribute("user2",user.GetUsername());
		}
		
		if(user==null) {
			request.setAttribute("message","�û���������");
			request.getRequestDispatcher("/web/friend.jsp").forward(request, response);
			return;
		}
		else {
			request.setAttribute("message","��ӳɹ�");
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
