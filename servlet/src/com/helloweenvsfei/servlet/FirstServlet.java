package com.helloweenvsfei.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FirstServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public FirstServlet() {
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
		
		//����servlet�Դ�����־�����Ϣ������̨
		this.log("ִ��doGet����...");
		
		//����doGet
		this.execute(request, response);
		
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
		
		//����Servlet�Դ�����־������Ϣ������̨
		this.log("ִ��doPost ����...");
		this.execute(request, response);
	}
	
	@Override
	public long getLastModified(HttpServletRequest request){
		this.log("ִ��getLastModified����...");
		return -1;
	}
	
	//ִ�з���
	private void execute(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException{
		
		//����response���뷽ʽ
		response.setCharacterEncoding("UTF-8");
		//����request���뷽ʽ
		request.setCharacterEncoding("UTF-8");
		//���ʸ�Servlet��URI
		String requestURI = request.getRequestURI();
		//����Servlet�ķ�ʽ, GET����POST
		String method = request.getMethod();
		//�ͻ����ύ�Ĳ���paramֵ
		String param = request.getParameter("param");
		
		//�����ĵ�����ΪHTML����
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.println("  �� " + method + " ��ʽ���ʸ�ҳ�档ȡ���� param ����Ϊ��" + param + "<br/>");
		
		out.println("  <form action='" + requestURI + " ' method='get'>" +
				"<input type='text' name='param' value='param string'>" +
				"<input type='submit' value='��GET��ʽ�ύ��ҳ�� " + requestURI + "'></form>");

		out.println("  <form action='" + requestURI + " ' method='post'>" +
				"<input type='text' name='param' value='param string'>" +
				"<input type='submit' value='��POST��ʽ�ύ��ҳ�� " + requestURI + "'></form>");
		
		//�ɿͻ����������ȡ���ĵ��ĸ���ʱ��
		out.println(" <script> document.write('��ҳ��������ʱ�䣺'+ document.lastModified); </script>");
		
		out.println(" </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
		
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
