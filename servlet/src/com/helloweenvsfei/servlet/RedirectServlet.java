package com.helloweenvsfei.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Map;
import java.util.HashMap;
import java.util.Map.Entry;

public class RedirectServlet extends HttpServlet {
	
	Map<String, Integer> map = new HashMap<String, Integer>();

	/**
	 * Constructor of the object.
	 */
	public RedirectServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
		map = null;
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
		
		String filename = request.getParameter("filename");
		
		//统计下载次数
		if(filename != null){
			//取下载次数
			int hit = map.get(filename);
			//下载次数+1后保存
			map.put(filename, ++hit);
			//重定向到文件
			response.sendRedirect(request.getContextPath() + filename);
		}
		else{
			
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML>");
			out.println("  <HEAD><TITLE>文件下载</TITLE></HEAD>");
			out.println("  <link rel='stylesheet' type='text/css' href='../css/style.css'");
			out.println("  <BODY><br/>");
			out.println("<fieldset align=center style=widht:90%><legend>文件下载</legend>");
			out.println("<table width=100%>");
			out.println("  <tr>");
			out.println("    <td><b>文件名</b></td>");
			out.println("    <td><b>下载次数</b></td>");
			out.println("    <td><b>下载</b></td>");
			out.println("  </tr>");
			for(Entry<String, Integer> entry: map.entrySet()){
				out.println("<tr>");
				out.println("  <td>" + entry.getKey() + "</td>");
				out.println("  <td>" + entry.getValue() + "</td>");
				out.println("  <td><a href='" + request.getRequestURI() + "?filename=" + entry.getKey() + "' target='_blank' onclick='location; '>下载</a></td>");
				out.println("</tr>");
			}
			out.println("</table>");
			out.println("</legend>");
			out.println("  </BODY>");
			out.println("</HTML>");
			out.flush();
			out.close();			
		}

		

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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
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
	@Override
	public void init() throws ServletException {
		// Put your code here
		map.put("/download/setup.exe", 0);
		map.put("/download/application.zip", 0);
		map.put("/download/01.mp3", 0);
	}

}
