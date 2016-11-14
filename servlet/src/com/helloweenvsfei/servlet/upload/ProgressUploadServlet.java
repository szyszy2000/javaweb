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
		
		//禁止浏览器缓存
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragrma", "no-cache");
		response.setDateHeader("Expires", 0);
		
		//从Session中读取上传信息
		UploadStatus status = (UploadStatus) request.getSession(true).getAttribute("uploadStatus");
		
		if(status == null){
			response.getWriter().println("没有上传信息");
			//没有上传信息，返回
			return;
		}
		
		//上传开始时间
		long startTime = status.getStartTime();
		
		//现在时间
		long currentTime = System.currentTimeMillis();
		
		//已传输的时间 单位：s
		long time = (currentTime - startTime) / 1000 + 1;
		
		//传输速度 单位：byte/s
		double velocity = ((double)status.getBytesRead())/(double) time;
		
		//估计总时间 单位：s
		double totalTime = status.getContentLength() / velocity;
		
		//估计剩余时间 单位：s
		double timeLeft = totalTime - time;
		
		//已完成的百分比
		int percent = (int) (100*(double)status.getBytesRead()/(double) status.getContentLength());
		
		//已完成数 单位：M
		double length = ((double) status.getBytesRead())/1024/1024;
		
		//总长度 单位：M
		double totalLength = ((double)status.getContentLength())/1024/1024;
		
		//格式：百分比||已完成数(M)||文件总长度(M)||传输速率(K)||已用时间(s)||估计总时间(s)||估计剩余时间(s)||正在上传第几个文件
		String value = percent + "||" + length + "||" + totalLength + "||" + velocity + "||" + time + "||" + totalTime + "||" + timeLeft + "||" + status.getItems();
		
		//输出给浏览器进度条
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
		
		//上传状态
		UploadStatus status = new UploadStatus();
		//监听器
		UploadListener listener = new UploadListener(status);
		//把状态放到Session里
		request.getSession(true).setAttribute("uploadStatus", status);
		
		//解析
		ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
		//设置上传listener
		upload.setProgressListener(listener);
		
		try{
			
			//提交的所有参数
			List itemList = upload.parseRequest(request);
			
			//遍历所有的参数
			for(Iterator it = itemList.iterator(); it.hasNext();){
				
				FileItem item = (FileItem) it.next();
				
				//如果是表单数据
				if(item.isFormField()){
					System.out.println("FormField: " + item.getFieldName() + " = " + item.getString());
				}
				//否则就是上传的文件
				else{
					System.out.println("File: " + item.getName());
					
					//统一Linux与Windows的路径分隔符
					String fileName = item.getName().replace("/", "\\");
					fileName = fileName.substring(fileName.lastIndexOf("\\"));
					
					//创建文件对象
					File saved = new File("C:\\upload_test", fileName);
					
					//保证路径存在
					saved.getParentFile().mkdirs();
					
					//提交的文件内容
					InputStream ins = item.getInputStream();
					
					//输出流
					OutputStream ous = new FileOutputStream(saved);
					
					//缓存
					byte[] tmp = new byte[1024];
					//缓存的实际长度
					int len = -1;
					
					//写文件
					while((len = ins.read(tmp)) != -1){
						ous.write(tmp, 0, len);
					}
					
					//关闭流
					ous.close();
					//关闭流
					ins.close();
					
					response.getWriter().println("已保存文件：" + saved);
				}
			}
			

			
		}catch(Exception e){
			response.getWriter().println("上传发生错误：" + e.getMessage());
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
