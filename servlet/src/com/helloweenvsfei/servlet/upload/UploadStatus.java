package com.helloweenvsfei.servlet.upload;

public class UploadStatus {
	
	//���ϴ����ֽ�������λ���ֽ�
	private long bytesRead;
	//�����ļ����ܳ��ȣ���λ���ֽ�
	private long contentLength;
	//�����ϴ��ڼ����ļ�
	private int items;
	//��ʼ�ϴ���ʱ�䣬���ڼ����ϴ����ʵ�
	private long startTime = System.currentTimeMillis();
	public long getBytesRead() {
		return bytesRead;
	}
	public void setBytesRead(long bytesRead) {
		this.bytesRead = bytesRead;
	}
	public long getContentLength() {
		return contentLength;
	}
	public void setContentLength(long contentLength) {
		this.contentLength = contentLength;
	}
	public int getItems() {
		return items;
	}
	public void setItems(int items) {
		this.items = items;
	}
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	
	

}
