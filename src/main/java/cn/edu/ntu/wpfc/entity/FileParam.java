package cn.edu.ntu.wpfc.entity;

import java.io.InputStream;

/**
 * 文件上传参数对象
 * @author wpfc
 *
 */
public class FileParam {

	/**
	 * 参数名称
	 */
	private String fieldName;
	/**
	 * 文件名称
	 */
	private String fileName;
	/**
	 * 文件大小
	 */
	private long fileSize;
	/**
	 * 文件类型
	 */
	private String contentType;
	private InputStream inputStream;
	
	
	
	public FileParam(String fieldName, String fileName, 
			long fileSize, String contentType, InputStream inputStream) {
		super();
		this.fieldName = fieldName;
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.contentType = contentType;
		this.inputStream = inputStream;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

}
