package com.train.excel.service;

import java.io.IOException;
import java.util.List;

import com.train.excel.controller.dto.SourceFileDto;
import com.train.excel.domain.SourceFile;

public interface SourceFileService {

	/**
	 * 
	 * @param md5
	 * @return
	 */
	SourceFile getByMd5(String md5);

	/**
	 * 保存
	 * 
	 * @param sf
	 */
	void save(SourceFile sf);

	/**
	 * 
	 * @param id
	 */
	void deleteById(String id);

	/**
	 * 
	 * @param condition
	 * @return
	 */
	List<SourceFile> getWithCondition(SourceFileDto condition);

	/**
	 * 
	 * @param condition
	 * @return
	 */
	int getCountWithCondition(SourceFileDto condition);

	/**
	 * 同步删除
	 * 
	 * @param id
	 * @param fileId
	 * @param filePath
	 * @throws IOException 
	 */
	void deleteFileAndContent(String id, String fileId, String filePath) throws IOException;
}
