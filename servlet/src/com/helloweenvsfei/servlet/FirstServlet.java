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
		
		//调用servlet自带的日志输出信息到控制台
		this.log("执行doGet方法...");
		
		//处理doGet
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
		
		//调用Servlet自带的日志传输信息到控制台
		this.log("执行doPost 方法...");
		this.execute(request, response);
	}
	
	@Override
	public long getLastModified(HttpServletRequest request){
		this.log("执行getLastModified方法...");
		return -1;
	}
	
	//执行方法
	private void execute(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException{
		
		//设置response编码方式
		response.setCharacterEncoding("UTF-8");
		//设置request编码方式
		request.setCharacterEncoding("UTF-8");
		//访问该Servlet的URI
		String requestURI = request.getRequestURI();
		//访问Servlet的方式, GET或者POST
		String method = request.getMethod();
		//客户端提交的参数param值
		String param = request.getParameter("param");
		
		//设置文档类型为HTML类型
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.println("  以 " + method + " 方式访问该页面。取到的 param 参数为：" + param + "<br/>");
		
		out.println("  <form action='" + requestURI + " ' method='get'>" +
				"<input type='text' name='param' value='param string'>" +
				"<input type='submit' value='以GET方式提交到页面 " + requestURI + "'></form>");

		out.println("  <form action='" + requestURI + " ' method='post'>" +
				"<input type='text' name='param' value='param string'>" +
				"<input type='submit' value='以POST方式提交到页面 " + requestURI + "'></form>");
		
		//由客户端浏览器读取该文档的更新时间
		out.println(" <script> document.write('本页面最后更新时间：'+ document.lastModified); </script>");
		
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
