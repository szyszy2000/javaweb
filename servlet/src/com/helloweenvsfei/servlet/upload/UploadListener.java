package com.helloweenvsfei.servlet.upload;

import org.apache.commons.fileupload.ProgressListener;

public class UploadListener implements ProgressListener{
	
	//��¼�ϴ���Ϣ��Java Bean
	private  UploadStatus status;
	
	public UploadListener(UploadStatus status){
		this.status = status;
	}
	
	//�ϴ��������ø÷���
	public void update(long bytesRead, long contentLength, int items){
		
		//�Ѷ�ȡ�����ݳ���
		status.setBytesRead(bytesRead);
		//�ļ����ܳ���
		status.setContentLength(contentLength);
		//���ڱ���ڼ����ļ�
		status.setItems(items);
	}

}
