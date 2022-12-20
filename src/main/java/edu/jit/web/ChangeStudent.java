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
		// �����������
		String job=request.getParameter("job");
		String rsUsername=request.getParameter("rs_username");
		String rsPassword=request.getParameter("rs_password");
		String rsGender=request.getParameter("rs_gender");
		String rsPhone=request.getParameter("rs_phone");
		String rsAddress=request.getParameter("rs_address");
		if(CheckNullUtils.isNull(rsUsername)) {
//			У�鲻ͨ��������ʾ��Ϣ���뵽request��
			request.setAttribute("a1", "�û�������Ϊ��");
//			ͨ������ת���ص�ע��ҳ�棬��ȡ�����е���Ϣ������ʾ
			request.getRequestDispatcher("/web/adminpersonage.jsp").forward(request, response);
//			����ת��ǰ��Ĵ��������ִ�еģ�����Ӧ����У��Ϊ��ʱ����������ִ��
			return;
		}
				int num = userService.alter(job,rsUsername,rsPassword,rsGender,rsPhone,rsAddress);
				if(num==0) {
					request.setAttribute("a1", "�޸���ϵ��ʧ��");
					request.getRequestDispatcher("/web/adminpersonage.jsp").forward(request, response);
					return;
				}else {
					request.setAttribute("a1", "�޸���ϵ�˳ɹ�");
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
