package com.helloweenvsfei.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;

public class UploadServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UploadServlet() {
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
		response.getWriter().println("����POST��ʽ�ϴ��ļ�");
		
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
	
	//����cunchecked warning
	@SuppressWarnings("unckecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		File file1 = null, file2=null;
		String description1 = null, description2=null;
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <link rel='stylesheet' type='text/css' href='../css/style.css'>");
		out.println("  <BODY>");
		
		out.println("<div align=center><br/>");
		out.println("<fieldset style='width:90%'><legend>�ϴ��ļ�</legend><br/>");
		
		out.println("		<div class='line'>");
		out.println("			<div align='left' class='leftDiv'>�ϴ���־��</div>");
		out.println("			<div align='left' class='rightDiv'>");
		
		// ʹ�� DiskFileUpload ������� request
		DiskFileUpload diskFileUpload = new DiskFileUpload();
		
		try{
			List<FileItem> list = diskFileUpload.parseRequest(request);
			out.println("�������е�fileItem ... <br/>");
			for(FileItem fileItem : list){
				
				//������ı���
				if(fileItem.isFormField()){
					if("description1".equals(fileItem.getFieldName())){
						out.println("������description1 ... <br/>");
						description1 = new String(fileItem.getString().getBytes(), "UTF-8");
					}
					if("description2".equals(fileItem.getFieldName())){
						out.println("������description2 ... <br/>");
						description2 = new String(fileItem.getString().getBytes(), "UTF-8");						
					}
				}
				//����Ϊ�ļ���
				else{
					//���Ϊfile1�ļ���
					if("file1".equals(fileItem.getFieldName())){
						File remoteFile = new File(new String(fileItem.getName().getBytes(), "UTF-8"));
						out.println("������file1 ... </br>");
						out.println("�ͻ����ļ�λ�ã�" + remoteFile.getAbsolutePath() + "<br/>");
						
						//������ļ�������upload�ļ�����
						file1 = new File(this.getServletContext().getRealPath("attachment"), remoteFile.getName());
						
						//�����ļ���·��
						file1.getParentFile().mkdirs();
						//�������ļ�
						file1.createNewFile();
						
						//FileItem������
						InputStream ins = fileItem.getInputStream();
						//������ļ���
						OutputStream ous = new FileOutputStream(file1);
						
						try{
							//�ֽڻ���
							byte[] buffer = new byte[1024];
							//ʵ�ʻ��泤��
							int len = 0;
							//ѭ�����뻺��
							while((len=ins.read(buffer)) > -1)
								ous.write(buffer, 0, len);
							out.println("�ѱ����ļ�" + file1.getAbsolutePath() + "<br/>");
							
						}finally{
							ous.close();
							ins.close();
						}
					}
					//���Ϊfile1�ļ���
					if("file2".equals(fileItem.getFieldName())){
						File remoteFile = new File(new String(fileItem.getName().getBytes(), "UTF-8"));
						out.println("������file2 ... </br>");
						out.println("�ͻ����ļ�λ�ã�" + remoteFile.getAbsolutePath() + "<br/>");
						
						//������ļ�������upload�ļ�����
						file2 = new File(this.getServletContext().getRealPath("attachment"), remoteFile.getName());
						
						//�����ļ���·��
						file2.getParentFile().mkdirs();
						//�������ļ�
						file2.createNewFile();
						
						//FileItem������
						InputStream ins = fileItem.getInputStream();
						//������ļ���
						OutputStream ous = new FileOutputStream(file2);
						
						try{
							//�ֽڻ���
							byte[] buffer = new byte[1024];
							//ʵ�ʻ��泤��
							int len = 0;
							//ѭ�����뻺��
							while((len=ins.read(buffer)) > -1)
								ous.write(buffer, 0, len);
							out.println("�ѱ����ļ�" + file2.getAbsolutePath() + "<br/>");
							
						}finally{
							ous.close();
							ins.close();
						}
					}
				}
			}
			out.println("Request �������");
		}catch (FileUploadException e){}
		
		//���file1����
		if(file1 != null){
			out.println("    <div class='line'>");
			out.println("      <div align='left' class='leftDiv'>file1: </div>");
			out.println("      <div align='left' class='rightDiv'>");
			out.println("<a href='" + request.getContextPath() + "/attachment/" + file1.getName() + "' target=_blank>" + file1.getName() + "</a>");
			out.println("      </div>");
			out.println("    </div>");
		}
		//���file2����
		if(file2 != null){
			out.println("    <div class='line'>");
			out.println("      <div align='left' class='leftDiv'>file2: </div>");
			out.println("      <div align='left' class='rightDiv'>");
			out.println("<a href='" + request.getContextPath() + "/attachment/" + file2.getName() + "' target=_blank>" + file2.getName() + "</a>");
			out.println("      </div>");
			out.println("    </div>");
		}
		out.println("		<div class='line'>");
		out.println("			<div align='left' class='leftDiv'>description1��</div>");
		out.println("			<div align='left' class='rightDiv'>");
		out.println(description1);
		out.println("           </div>");
		out.println("         </div>");
		out.println("</fieldset>");
		out.println("       </div>");
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
