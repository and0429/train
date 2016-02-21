package com.train.excel.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.train.excel.controller.dto.FileDto;
import com.train.excel.domain.SourceFile;
import com.train.excel.domain.Status;
import com.train.excel.service.SourceFileService;
import com.train.excel.utils.FilePathConstants;

@RestController
@RequestMapping("/sf")
public class SourceFileController {

	@Inject
	private SourceFileService service;

	@Inject
	private ServletContext sc;

	/**
	 * list
	 * 
	 * @return
	 */
	@RequestMapping(path = "/list", method = RequestMethod.GET)
	public Object list(FileDto condition) {
		Map<String, Object> map = new HashMap<>();
		List<SourceFile> files = service.getWithCondition(condition);
		int count = service.getCountWithCondition(condition);
		map.put("data", files);
		map.put("recordsTotal", count);
		map.put("draw", condition.getDraw());
		return map;
	}

	/**
	 * delete
	 * 
	 * @param id
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(path = "/delete/{id}/{fileid}/{filepath}", method = RequestMethod.DELETE)
	public Object deleteById(@PathVariable("id") Long id, @PathVariable("fileid") String fileId,
			@PathVariable("filepath") String filePath) throws IOException {
		String realFilePath = sc.getRealPath(filePath);
		service.deleteFileAndContent(id, fileId, realFilePath);
		return new Status();
	}

	/**
	 * analysis
	 * 
	 * @param fileId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(path = "/analysis/{fileId}/{fileName}", method = RequestMethod.GET)
	public Object analysis(@PathVariable String fileId, @PathVariable String fileName) throws IOException {
		String realPath = sc.getRealPath(FilePathConstants.UPLOAD_RESULT_FILE_PATH);
		return service.analysis(fileId, fileName, realPath);
	}

}
