package com.train.excel.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import com.train.excel.controller.FileUploadController;
import com.train.excel.dao.StationTrainDao;
import com.train.excel.domain.SourceFile;
import com.train.excel.domain.StationTrain;
import com.train.excel.domain.Status;
import com.train.excel.service.FileUpLoadService;
import com.train.excel.service.SourceFileService;
import com.train.excel.utils.ExcelUtil;

@Named
public class FileUpLoadServiceImpl implements FileUpLoadService {

	@Inject
	private StationTrainDao stationTrainDao;

	@Inject
	private SourceFileService sourceFileService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.train.excel.service.FileUpLoadService#saveContentAndfile(org.
	 * springframework.web.multipart.MultipartFile)
	 */
	@Override
	public Status saveContentAndfile(MultipartFile file, String destPath)
			throws IOException, EncryptedDocumentException, InvalidFormatException {

		String fileName = file.getOriginalFilename();
		InputStream in = file.getInputStream();
		String fileMd5 = DigestUtils.md5DigestAsHex(in);

		SourceFile sourceFile = sourceFileService.getByMd5(fileMd5);
		if (sourceFile != null) {
			return new Status(1, "file exits!");
		}

		Date uploadTime = new Date();
		String fileId = UUID.randomUUID().toString();
		List<StationTrain> sts = ExcelUtil.excel2List(in, fileId, uploadTime);
		for (StationTrain stationTrain : sts) {
			stationTrainDao.save(stationTrain);
		}

		SourceFile sf = new SourceFile();
		sf.setFileId(fileId);
		sf.setFileName(fileName);
		sf.setFilePath(FileUploadController.uploadFilePath + File.separator + fileName);
		sf.setMd5(DigestUtils.md5DigestAsHex(in));
		sf.setUploadTime(uploadTime);
		sourceFileService.save(sf);

		File sourcefile = new File(destPath, fileName);
		file.transferTo(sourcefile);

		return new Status();

	}

}
