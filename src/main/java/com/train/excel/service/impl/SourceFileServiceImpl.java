/**
 * 
 */
package com.train.excel.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.train.excel.controller.dto.FileDto;
import com.train.excel.dao.SourceFileDao;
import com.train.excel.dao.StationTrainDao;
import com.train.excel.domain.ResultFile;
import com.train.excel.domain.SourceFile;
import com.train.excel.service.SourceFileService;
import com.train.excel.utils.FilePathConstants;

/**
 * @author and04
 *
 */
@Named
public class SourceFileServiceImpl implements SourceFileService {

	@Inject
	private SourceFileDao dao;

	@Inject
	private StationTrainDao stDao;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.train.excel.service.SourceFileService#deleteById(java.lang.String)
	 */
	@Override
	public void deleteById(Long id) {
		dao.deleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.train.excel.service.SourceFileService#getWithCondition(com.train.
	 * excel.controller.dto.SourceFileDto)
	 */
	@Override
	public List<SourceFile> getWithCondition(FileDto condition) {
		return dao.getWithCondition(condition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.train.excel.service.SourceFileService#getCountWithCondition(com.train
	 * .excel.controller.dto.SourceFileDto)
	 */
	@Override
	public int getCountWithCondition(FileDto condition) {
		return dao.getCountWithCondition(condition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.train.excel.service.SourceFileService#deleteFileAndContent(java.lang.
	 * String, java.lang.String, java.lang.String)
	 */
	@Override
	public void deleteFileAndContent(Long id, String fileId, String filePath) throws IOException {
		dao.deleteById(id);
		stDao.deleteByFileId(fileId);
		Files.deleteIfExists(Paths.get(filePath));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.train.excel.service.SourceFileService#analysis(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public ResultFile analysis(String srcFileId, String srcFileName, String realPath) throws IOException {

		Path resultFilePath = Paths.get(realPath, srcFileName + FilePathConstants.FILE_EXTENSION_NAME_XLSX);
		Files.newOutputStream(resultFilePath);

		return null;
	}

}
