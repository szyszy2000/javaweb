package com.helloweenvsfei.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class PostServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public PostServlet() {
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
		response.getWriter().println("��ʹ��POST��ʽ�ύ���ݡ�");

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
		
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String sex = request.getParameter("sex");
		
		int age = 0;
		try{
			age = Integer.parseInt(request.getParameter("age"));
		}catch(Exception e){}
		
		Date birthday = null;
		
		try{
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			birthday = format.parse(request.getParameter("birthday"));
		}catch(Exception e){}
		
		String[] interesting = request.getParameterValues("interesting");
		String area = request.getParameter("area");
		String description = request.getParameter("description");
		String btn = request.getParameter("btn");
		
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>��л���ύ��Ϣ</TITLE></HEAD>");
		out.println("<div align=\"center\"><br/>");
		out.println("<fieldset style='width:90%'><legend>��д�û���Ϣ</legend><br/>");
		out.println("  <div class='line'>");
		out.println("    <div align='left' class='leftDiv'>����������</div>");
		out.println("    <div align='left' class='rightDiv'>" + name + "</div>");
		out.println("</div>");
		out.println("  <div class='line'>");
		out.println("    <div align='left' class='leftDiv'>�������룺</div>");
		out.println("    <div align='left' class='rightDiv'>" + password + "</div>");
		out.println("</div>");
		out.println("  <div class='line'>");
		out.println("    <div align='left' class='leftDiv'>�����Ա�</div>");
		out.println("    <div align='left' class='rightDiv'>" + sex + "</div>");
		out.println("</div>");
		out.println("  <div class='line'>");
		out.println("    <div align='left' class='leftDiv'>�������䣺</div>");
		out.println("    <div align='left' class='rightDiv'>" + age + "</div>");
		out.println("  </div>");
		out.println("  <div class='line'>");
		out.println("    <div align='left' class='leftDiv'>�������գ�</div>");
		out.println("    <div align='left' class='rightDiv'>");
		out.println(new SimpleDateFormat("yyyy��MM��dd��").format(birthday));
		out.println("    </div>");
		out.println("</div>");
		out.println("  <div class='line'>");
		out.println("    <div align='left' class='leftDiv'>������Ȥ��</div>");
		out.println("    <div align='left' class='rightDiv'>");
		for(String str:interesting){ out.println(str + ", ");}
		out.println("    </div>");
		out.println("  </div>");
		out.println("  <div class='line'>");
		out.println("    <div align='left' class='leftDiv'>����������</div>");
		out.println("    <div align='left' class='rightDiv'>" + description + "</div>");
		out.println("  </div>");
		out.println("  <div class='line'>");
		out.println("    <div align='left' class='leftDiv'>��ť��ֵ��</div>");
		out.println("    <div align='left' class='rightDiv'>" + btn + "</div>");
		out.println("  </div>");
		out.println("  <div class='line'>");
		out.println("    <div align='left' class='leftDiv'></div>");
		out.println("    <div align='left' class='rightDiv'>");
		out.println("      <br/><input type='button' name='btn' value='������һҳ' onclick='history.go(-1); ' class='button'><br/>");
		out.println("    </div>");
		out.println("  </div>");
		out.println("  </fieldset>");
		out.println("  </div>");
		out.println("  <BODY>");
		out.println("  </BODY>");
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
