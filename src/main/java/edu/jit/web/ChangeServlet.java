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
		// �����������
		int id = Integer.parseInt(request.getParameter("id"));
		String job=request.getParameter("job");
		String rsUsername=request.getParameter("rs_username");
		String rsGender=request.getParameter("rs_gender");
		String rsPhone=request.getParameter("rs_phone");
		String rsAddress=request.getParameter("rs_address");
		// �ǿ�У��
		if(CheckNullUtils.isNull(rsUsername)) {
			request.setAttribute("message", "�û�������Ϊ��");
			request.getRequestDispatcher("/web/studentpersonage.jsp").forward(request, response);
			return;
		}
		if(CheckNullUtils.isNull(rsPhone)) {
			request.setAttribute("message", "�ֻ��Ų���Ϊ��");
			request.getRequestDispatcher("/web/studentpersonage.jsp").forward(request, response);
			return;
		}
		if(CheckNullUtils.isNull(rsAddress)) {
			request.setAttribute("message", "�û�������Ϊ��");
			request.getRequestDispatcher("/web/studentpersonage.jsp").forward(request, response);
			return;
		// ��װ���ݣ���User�����½���Ӧ�Ĺ��췽��
		}
			User user = new User(id,rsUsername,rsGender,rsPhone,rsAddress);
		User user1=userService.queryByUserName(rsUsername);
		if(user1!=null) {
			request.setAttribute("message","�û��Ѵ���");
			request.getRequestDispatcher("/web/studentpersonage.jsp").forward(request, response);
			return  ;
		}
		User user2=userService.queryByPhone(rsPhone);
		if(user2!=null) {
			request.setAttribute("message","�绰�Ѵ���");
			request.getRequestDispatcher("/web/studentpersonage.jsp").forward(request, response);
			return  ;
		}
	
		// ����userService�еĸ��·���
		int num = userService.updataUser(user);
		if(num==0) {
			request.setAttribute("message", "����ʧ��");
			request.getRequestDispatcher("/web/personage.jsp").forward(request, response);
			return;
		}
		// �����º��user����洢��session����
		request.getSession().setAttribute("user", user);
		// Ȼ�������ض���ص�personage.jspҳ��
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
