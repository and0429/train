package com.train.excel.domain;

import java.util.Date;

/**
 * 结果文件
 * 
 * @author and04
 *
 */
public class ResultFile {

	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 文件Id
	 */
	private String fileId;
	
	/**
	 * 文件路径
	 */
	private String filePath;

	/**
	 * 文件名字
	 */
	private String fileName;

	/**
	 * 上传时间
	 */
	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	

}
