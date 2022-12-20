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
 * Servlet implementation class SafeServlet
 */
@WebServlet("/safeServlet")
public class SafeServlet extends HttpServlet {
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
		String oldPassword=request.getParameter("oldPassword");
		String newPassword=request.getParameter("newPassword");
		String conNewPassword=request.getParameter("conNewPassword");
		
		// �ǿ�У��
		if(CheckNullUtils.isNull(oldPassword)) {
			request.setAttribute("message", "�����벻��Ϊ��");
			request.getRequestDispatcher("/web/studentpersonal_password.jsp").forward(request, response);
			return;
		}
		if(CheckNullUtils.isNull(newPassword)) {
			request.setAttribute("message", "�����벻��Ϊ��");
			request.getRequestDispatcher("/web/studentpersonal_password.jsp").forward(request, response);
			return;
		}
		if(CheckNullUtils.isNull(conNewPassword)) {
			request.setAttribute("message", "ȷ�����벻��Ϊ��");
			request.getRequestDispatcher("/web/studentpersonal_password.jsp").forward(request, response);
			return;
		}
		if(newPassword!=null&&conNewPassword!=null&& !newPassword.equals(conNewPassword)) {
			request.setAttribute("message", "�������벻һ��");
			request.getRequestDispatcher("/web/studentpersonal_password.jsp").forward(request, response);
			return;
		}
		User user = userService.queryByID(id);
		if(oldPassword.equals(user.GetPassword())!=true) {
			request.setAttribute("message","�������������");
			request.getRequestDispatcher("/web/studentpersonal_password.jsp").forward(request, response);
			return  ;
		}
		else {
			int num = userService.updataPassword(newPassword,id);
			if(num==0) {
				request.setAttribute("message", "�޸�����ʧ��");
				request.getRequestDispatcher("/web/studentpersonal_password.jsp").forward(request, response);
				return;
			}else {
				request.setAttribute("message", "�޸�����ɹ�");
				request.getRequestDispatcher("/web/studentpersonal_password.jsp").forward(request, response);
				return;
			}
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
