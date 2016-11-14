package com.helloweenvsfei.servlet.upload;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.util.List;
import java.util.Iterator;

public class ProgressUploadServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ProgressUploadServlet() {
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
		
		//��ֹ���������
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragrma", "no-cache");
		response.setDateHeader("Expires", 0);
		
		//��Session�ж�ȡ�ϴ���Ϣ
		UploadStatus status = (UploadStatus) request.getSession(true).getAttribute("uploadStatus");
		
		if(status == null){
			response.getWriter().println("û���ϴ���Ϣ");
			//û���ϴ���Ϣ������
			return;
		}
		
		//�ϴ���ʼʱ��
		long startTime = status.getStartTime();
		
		//����ʱ��
		long currentTime = System.currentTimeMillis();
		
		//�Ѵ����ʱ�� ��λ��s
		long time = (currentTime - startTime) / 1000 + 1;
		
		//�����ٶ� ��λ��byte/s
		double velocity = ((double)status.getBytesRead())/(double) time;
		
		//������ʱ�� ��λ��s
		double totalTime = status.getContentLength() / velocity;
		
		//����ʣ��ʱ�� ��λ��s
		double timeLeft = totalTime - time;
		
		//����ɵİٷֱ�
		int percent = (int) (100*(double)status.getBytesRead()/(double) status.getContentLength());
		
		//������� ��λ��M
		double length = ((double) status.getBytesRead())/1024/1024;
		
		//�ܳ��� ��λ��M
		double totalLength = ((double)status.getContentLength())/1024/1024;
		
		//��ʽ���ٷֱ�||�������(M)||�ļ��ܳ���(M)||��������(K)||����ʱ��(s)||������ʱ��(s)||����ʣ��ʱ��(s)||�����ϴ��ڼ����ļ�
		String value = percent + "||" + length + "||" + totalLength + "||" + velocity + "||" + time + "||" + totalTime + "||" + timeLeft + "||" + status.getItems();
		
		//����������������
		response.getWriter().println(value);

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
		
		//�ϴ�״̬
		UploadStatus status = new UploadStatus();
		//������
		UploadListener listener = new UploadListener(status);
		//��״̬�ŵ�Session��
		request.getSession(true).setAttribute("uploadStatus", status);
		
		//����
		ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
		//�����ϴ�listener
		upload.setProgressListener(listener);
		
		try{
			
			//�ύ�����в���
			List itemList = upload.parseRequest(request);
			
			//�������еĲ���
			for(Iterator it = itemList.iterator(); it.hasNext();){
				
				FileItem item = (FileItem) it.next();
				
				//����Ǳ�����
				if(item.isFormField()){
					System.out.println("FormField: " + item.getFieldName() + " = " + item.getString());
				}
				//��������ϴ����ļ�
				else{
					System.out.println("File: " + item.getName());
					
					//ͳһLinux��Windows��·���ָ���
					String fileName = item.getName().replace("/", "\\");
					fileName = fileName.substring(fileName.lastIndexOf("\\"));
					
					//�����ļ�����
					File saved = new File("C:\\upload_test", fileName);
					
					//��֤·������
					saved.getParentFile().mkdirs();
					
					//�ύ���ļ�����
					InputStream ins = item.getInputStream();
					
					//�����
					OutputStream ous = new FileOutputStream(saved);
					
					//����
					byte[] tmp = new byte[1024];
					//�����ʵ�ʳ���
					int len = -1;
					
					//д�ļ�
					while((len = ins.read(tmp)) != -1){
						ous.write(tmp, 0, len);
					}
					
					//�ر���
					ous.close();
					//�ر���
					ins.close();
					
					response.getWriter().println("�ѱ����ļ���" + saved);
				}
			}
			

			
		}catch(Exception e){
			response.getWriter().println("�ϴ���������" + e.getMessage());
		}
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
