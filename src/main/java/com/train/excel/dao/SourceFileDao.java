package com.train.excel.dao;

import com.train.excel.domain.SourceFile;

/**
 * 
 * @author and04
 *
 */
public interface SourceFileDao {

	/**
	 * 
	 * @param md5
	 * @return
	 */
	SourceFile getByMd5(String md5);

	/**
	 * 
	 * @param sf
	 */
	void save(SourceFile sf);

}
