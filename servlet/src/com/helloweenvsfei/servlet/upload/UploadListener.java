package com.helloweenvsfei.servlet.upload;

import org.apache.commons.fileupload.ProgressListener;

public class UploadListener implements ProgressListener{
	
	//记录上传信息的Java Bean
	private  UploadStatus status;
	
	public UploadListener(UploadStatus status){
		this.status = status;
	}
	
	//上传组件会调用该方法
	public void update(long bytesRead, long contentLength, int items){
		
		//已读取的数据长度
		status.setBytesRead(bytesRead);
		//文件的总长度
		status.setContentLength(contentLength);
		//正在保存第几个文件
		status.setItems(items);
	}

}
