package com.helloweenvsfei.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.helloweenvsfei.util.IpUtil;

import java.security.Principal;

public class RequestServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public RequestServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	
	//���ؿͻ�����������ܵ��ļ�����
	private String getAccept(String accept){
		StringBuffer buffer = new StringBuffer();
		if(accept.contains("image/gif")) buffer.append("GIF �ļ�, ");
		if(accept.contains("image/x-xbitmp")) buffer.append("BMP �ļ�, ");
		if(accept.contains("image/jpeg")) buffer.append("JPG �ļ�, ");
		if(accept.contains("application/vnd.ms-excel")) buffer.append("Excel �ļ�, ");
		if(accept.contains("application/vnd.ms-powerpoint")) buffer.append("PPT �ļ�, ");
		if(accept.contains("application/vnd.msword")) buffer.append("Word �ļ�, ");
		
		return buffer.toString().replaceAll(", $", "");
	}
	
	//���ؿͻ������Ի�������
	private String getLocale(Locale locale){
		if(Locale.SIMPLIFIED_CHINESE.equals(locale))  return "��������";
		if(Locale.TRADITIONAL_CHINESE.equals(locale)) return "��������";
		if(Locale.ENGLISH.equals(locale))             return "Ӣ��";
		if(Locale.JAPANESE.equals(locale))            return "����";
		return "δ֪���Ի���";
	}

	//����IP��ַ��Ӧ������λ��
	private String getAddress(String ip){
		
		return IpUtil.getIpAddress(ip);
		
	}
	
	//���ؿͻ����������Ϣ
	private String getNavigator(String userAgent){
		if(userAgent.indexOf("TencentTraveler") > 0) return "��Ѷ�����";
		if(userAgent.indexOf("Maxthon") > 0) return "Maxthon�����";
		if(userAgent.indexOf("MyIE2") > 0) return "MyIE2�����";
		if(userAgent.indexOf("FireFox") > 0) return "FireFox�����";
		if(userAgent.indexOf("MSIE") > 0) return "IE�����";
		return "δ֪�����";
	}
	
	//���ؿͻ��˲���ϵͳ
	private String getOS(String userAgent){
		if(userAgent.indexOf("Windows NT 5.1") > 0) return "Windows XP";
		if(userAgent.indexOf("Windows 98") > 0) return "Windows 98";
		if(userAgent.indexOf("Windows NT 5.0") > 0) return "Windows 2000";
		if(userAgent.indexOf("Linux") > 0) return "Linux";
		if(userAgent.indexOf("Unix") > 0) return "Unix";
		return "δ֪";
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

		//����request���뷽ʽ
		request.setCharacterEncoding("UTF-8");
		
		//����response���뷽ʽ
		response.setCharacterEncoding("UTF-8");
		
		response.setContentType("text/html");
		
		String authType = request.getAuthType();
		
		//����IP,��������IP
		String localAddr = request.getLocalAddr();
		
		//��������,������������
		String localName = request.getLocalName();
		
		//���ض˿ں�,��Tomcat�˿ں�
		int localPort = request.getLocalPort();
		
		//�û����Ի���
		Locale locale = request.getLocale();
		
		//context·��
		String contextPath = request.getContextPath();
		
		//GET����POST
		String method = request.getMethod();
		
		String pathInfo = request.getPathInfo();
		
		String pathTranslated = request.getPathTranslated();
		
		//Э��,����ΪHTTPЭ��
		String protocol = request.getProtocol();
		
		//��ѯ�ַ���,��?������ַ���
		String queryString = request.getQueryString();
		
		//Զ��IP,���ͻ���IP
		String remoteAddr = request.getRemoteAddr();
		
		//Զ�̶˿�,���ͻ��˶˿�		
		int port = request.getRemotePort();
		
		//Զ���û�
		String remoteUser = request.getRemoteUser();
		
		//�ͻ���session��ID
		String requestSessionId = request.getRequestedSessionId();
		
		//�û������URI
		String requestURI = request.getRequestURI();
		
		//�û������URL
		StringBuffer requestURL = request.getRequestURL();
		
		//Э��ͷ,����Ϊhttp
		String scheme = request.getScheme();
		
		//����������
		String serverName = request.getServerName();
		
		//�������˿�
		int serverPort = request.getServerPort();
		
		//Servlet��·��
		String servletPath = request.getServletPath();
		
		Principal userPrincipal = request.getUserPrincipal();
		
		//�����֧�ֵĸ�ʽ
		String accept = request.getHeader("accept");
		
		//���ĸ�ҳ�浥�����ӵ��˱�ҳ
		String referer = request.getHeader("referer");
		
		//User Agent��Ϣ,��������ϵͳ���ͺͰ汾�š���������ͼ��汾��
		String userAgent = request.getHeader("user-agent");
		
		String serverInfo = this.getServletContext().getServerInfo();
	
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>Request Servlet</TITLE></HEAD>");
		out.println("  <style>body, font, td, div {font-size:12px; line-height:18px }</style>");
		out.println("  <BODY>");
		out.print("<b>����IPΪ</b>" + remoteAddr + "<b>, λ��</b>" + getAddress(remoteAddr) + 
				"<b>; ��ʹ��</b>" + getOS(userAgent) + "<b>����ϵͳ</b>, " + getNavigator(userAgent)+
				" <b>. ��ʹ��</b> " + getLocale(locale) + ". <br/>");
		out.println("<b>������IPΪ</b>" + localAddr + "<b>, λ��</b>" + getAddress(localAddr) +
				"<b>; ������ʹ��</b> " + serverPort + " <b> �˿�, ���������ʹ����</b> " + port + "<b>�˿ڷ��ʱ�ҳ��.</b><br />");
		out.println("<b>���������Ϊ</b>: " + serverInfo + ". <b>����������Ϊ</b> " + localName + ".<br/>");
		out.println("�������������</b>" + getAccept(accept) + ". <br />");
		out.println("<b>����</b>" + referer + " <b>���ʵ���ҳ��. </b> <br />");
		out.println("<b>ʹ�õ�Э��Ϊ</b> " + protocol + ". <b> URLЭ��ͷ</b>" + scheme + ", <b>����������</b> " + serverName +
				", <b>�����ʵ�URIΪ</b> " + requestURI + ". <br/>");
		out.println("<b>��Servlet·��Ϊ</b> " + servletPath + ", <b>��Servlet����Ϊ</b> " + this.getClass().getName() + ". <br/>");
		out.println("<b>��Ӧ�ó�����Ӳ�̵ĸ�Ŀ¼Ϊ</b> " + this.getServletContext().getRealPath("") + ", <b>�������·��Ϊ</b>" + contextPath + ". <br/>");
		out.println("<br/>");
		out.println("<br/><br/><a href=" + requestURI + "> ����ˢ�±�ҳ��</a>");
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
