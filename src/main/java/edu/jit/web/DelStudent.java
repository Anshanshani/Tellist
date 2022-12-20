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
//			У�鲻ͨ��������ʾ��Ϣ���뵽request��
			request.setAttribute("a2", "�û�������Ϊ��");
//			ͨ������ת���ص�ע��ҳ�棬��ȡ�����е���Ϣ������ʾ
			request.getRequestDispatcher("/web/adminpersonage.jsp").forward(request, response);
//			����ת��ǰ��Ĵ��������ִ�еģ�����Ӧ����У��Ϊ��ʱ����������ִ��
			return;
		}
		int num = userService.del(rsUsername);
		if(num==0) {
			request.setAttribute("a2", "ɾ����ϵ��ʧ��");
			request.getRequestDispatcher("/web/adminpersonage.jsp").forward(request, response);
			return;
		}else {
			request.setAttribute("a2", "ɾ����ϵ�˳ɹ�");
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
