package com.helloweenvsfei.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Enumeration;

public class InitParamServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public InitParamServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>���¼�鿴 Notice �ļ�</TITLE></HEAD>");
		out.println("<style>body, td, div {font-size:12px; }</style>");
		out.println("  <BODY>");
		
		out.println("<form action='" + request.getRequestURI()+ "' method='post'>");
		out.println("�˺�: <input type='text' name='username' sytle='width:200px;'> <br/>");
		out.println("����: <input type='password' name='password' style='width:200px; '> <br/><br/>");
		out.println("<input type='submit' value=' ��¼ ' >");
		out.println("</form>");

		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//�ύ��username����
		String username = request.getParameter("username");
		//�ύ��password����
		String password = request.getParameter("password");
		
		//���еĳ�ʼ����������
		Enumeration params = this.getInitParameterNames();
		
		while(params.hasMoreElements()){
			String usernameParam = (String)params.nextElement();
			String passnameParam = getInitParameter(usernameParam);
			
			//����û�������ƥ������ʾ
			if(username.equalsIgnoreCase(username) && passnameParam.equals(password)){
				request.getRequestDispatcher("/WEB-INF/notice.html").forward(request, response);
				return;
			}
		}
		
		//username, password��ƥ�䣬��ʾ��¼ҳ��
		this.doGet(request, response);

	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
