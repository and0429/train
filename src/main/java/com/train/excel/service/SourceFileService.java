package com.train.excel.service;

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
}
