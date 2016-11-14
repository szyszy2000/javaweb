package com.helloweenvsfei.servlet;

import com.yahoo.search.ImageSearchRequest;
import com.yahoo.search.ImageSearchResult;
import com.yahoo.search.ImageSearchResults;
import com.yahoo.search.SearchClient;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SearchServlet() {
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
		
		String word = request.getParameter("word");
		String type = request.getParameter("type");
		String allowedAudit = request.getParameter("allowedAduit");
		boolean adultOk = "true".equals(allowedAudit);
		
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>"+word+"�������</TITLE></HEAD>");
		out.println("  <BODY>");
		out.println("  <div style='float:left; height:40px; '><img src='http://us.il.yimg.com/us.yimg.com/i/ww/beta/y3.gif'><div>");
		out.println("<form action='" + request.getRequestURI() + "' method='get' >");
		out.println("  <div style='height:40px; '>");
		out.println("    <input type='radio' name='type' value='web' " + (type.equals("web")?"checked":"") + ">��ҳ");
		out.println("    <input type='radio' name='type' value='news' " + (type.equals("news")?"checked":"") + ">����");
		out.println("    <input type='radio' name='type' value='image' " + (type.equals("image")?"checked":"") + ">ͼ��");
		out.println("    <input type='radio' name='type' value='video' " + (type.equals("video")?"checked":"") + ">��Ƶ");
		out.println("	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
		out.println("	<input type='checkbox' name='allowedAdult' value='true' " + (adultOk?"checked":"") +">�����������<br/>");
		out.println("   <input type='text' name='word' value='" + word + "' style='width:300px; '> <input type='submit' value='���Ż�����' style='width:100px; '> ");
		out.println("   </div>");
		out.println("</form>");
		
		//����Yahoo API
		SearchClient client = new SearchClient("javasdktest");
		
		
		try{
			
			//�����������ͼƬ
			if("image".equals(type)){
				ImageSearchRequest searchRequest = new ImageSearchRequest(URLEncoder.encode(word, "UTF-8"));
				
				//�Ƿ���ʾ��������
				searchRequest.setAdultOk(adultOk);
				//��ѯ��¼��
				searchRequest.setResults(20);
				//�ӵ�0����¼��ʼ��ʾ
				searchRequest.setStart(BigInteger.valueOf(0));
				
				//��¼��ʼʱ��
				double startTime = System.currentTimeMillis();
				
				//��ѯ
				ImageSearchResults results = client.imageSearch(searchRequest);
				
				//��¼����ʱ��
				double endTime = System.currentTimeMillis();
				
				out.println("<div align=right style='width:100%; background:#FFDDD; height:25px; padding:2px; border-top:1px solid #FF9999; margin-bottom:5px; '>");
				out.println("  �ܹ� " + results.getTotalResultsAvailable() + "����¼������ʱ " + (endTime-startTime)/1000 + "�롣");
				out.println("</div>");
				
				for(ImageSearchResult result : results.listResults()){
					
					//�������еĲ�ѯ���
					out.println("<div class='imgDiv'>");
					out.println("  <div align='center'><a href='" + result.getClickUrl() + "'target=_blank><img width=160 height=120 src=\""
							+ result.getThumbnail().getUrl() + "\" border='0'></a></div>");
					out.println("  <div align='center'><a href=\"" + result.getRefererUrl() + "\" target=_blank>" + result.getTitle() + "</a></div>");
					out.println("  <div align='center'>" + result.getWidth() + "x" + result.getHeight() + " " + result.getFileFormat() + "</div>");
					out.println("  <div>" + (result.getSummary()==null?"":result.getSummary()) + "</div>");
					out.println("</div>");
					
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
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
	public void init() throws ServletException {
		// Put your code here
	}

}
