package com.train.excel.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.train.excel.domain.Status;
import com.train.excel.service.FileUpLoadService;

@RestController
@RequestMapping("/upload")
public class FileUploadController {

	public static final String uploadFilePath = "/upload/sourcefile";

	@Inject
	private FileUpLoadService service;

	@Inject
	private ServletContext sc;

	/**
	 * 上传Excel
	 * 
	 * @param name
	 * @param file
	 * @return
	 * @throws IOException
	 * @throws InvalidFormatException
	 * @throws EncryptedDocumentException
	 */
	@RequestMapping(path = "/excel", method = RequestMethod.GET)
	public Status handleFormUpload(String name, MultipartFile file) throws Exception {
		if (!file.isEmpty()) {
			String destPath = sc.getRealPath(uploadFilePath);
			return service.saveContentAndfile(file, destPath);
		}
		return new Status(1, "file is empty");
	}

}
