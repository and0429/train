/**
 * 
 */
package com.train.excel.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import com.train.excel.dao.SourceFileDao;
import com.train.excel.domain.SourceFile;
import com.train.excel.service.SourceFileService;

/**
 * @author and04
 *
 */
@Named
public class SourceFileServiceImpl implements SourceFileService {

	@Inject
	private SourceFileDao dao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.train.excel.service.SourceFileService#getByMd5(java.lang.String)
	 */
	@Override
	public SourceFile getByMd5(String md5) {
		return dao.getByMd5(md5);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.train.excel.service.SourceFileService#save(com.train.excel.domain.
	 * SourceFile)
	 */
	@Override
	public void save(SourceFile sf) {
		dao.save(sf);
	}

}
