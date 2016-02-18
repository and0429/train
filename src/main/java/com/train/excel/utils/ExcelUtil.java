package com.train.excel.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.train.excel.domain.StationTrain;

/**
 * Excel 工具类
 * 
 * @author and04
 *
 */
public class ExcelUtil {

	public static List<StationTrain> excel2List(InputStream in, String fileId, Date upLoadTime)
			throws EncryptedDocumentException, InvalidFormatException, IOException {

		List<StationTrain> list = new ArrayList<>();
		if (in == null) {
			return list;
		}
		try (Workbook wk = WorkbookFactory.create(in)) {
			if (wk == null) {
				return list;
			}
			for (Sheet sheet : wk) {
				for (Row row : sheet) {
					StationTrain st = new StationTrain();
					if (row.getRowNum() == 0) {
						continue;
					}
					st.setSerialNum(getCellValue(row.getCell(0, Row.CREATE_NULL_AS_BLANK)));
					st.setTrainNum(getCellValue(row.getCell(1, Row.CREATE_NULL_AS_BLANK)));
					st.setTrainType(getCellValue(row.getCell(2, Row.CREATE_NULL_AS_BLANK)));
					st.setChangeLong(Double.parseDouble(getCellValue(row.getCell(3, Row.CREATE_NULL_AS_BLANK))));
					st.setSelfWeight(Double.parseDouble(getCellValue(row.getCell(4, Row.CREATE_NULL_AS_BLANK))));
					st.setLoadWeight(Double.parseDouble(getCellValue(row.getCell(5, Row.CREATE_NULL_AS_BLANK))));
					st.setGoodsName(getCellValue(row.getCell(6, Row.CREATE_NULL_AS_BLANK)));
					st.setFromStation(getCellValue(row.getCell(7, Row.CREATE_NULL_AS_BLANK)));
					st.setFromBureau(getCellValue(row.getCell(8, Row.CREATE_NULL_AS_BLANK)));
					st.setToStation(getCellValue(row.getCell(9, Row.CREATE_NULL_AS_BLANK)));
					st.setToBureau(getCellValue(row.getCell(10, Row.CREATE_NULL_AS_BLANK)));
					st.setConsignee(getCellValue(row.getCell(11, Row.CREATE_NULL_AS_BLANK)));
					st.setFileId(fileId);
					st.setDate(upLoadTime);
					list.add(st);
				}
			}
			return list;
		} finally {
			in.close();
		}
	}

	/*
	 * 获取各种类型的单元格的值， 并转换成字符串
	 */
	private static String getCellValue(Cell cell) {
		String value = null;
		int cellType = cell.getCellType();
		switch (cellType) {
		case Cell.CELL_TYPE_NUMERIC:
			value = String.valueOf(cell.getNumericCellValue());
			if (value.endsWith(".0")) {
				value = value.split("\\.")[0];
			}
			break;
		case Cell.CELL_TYPE_BLANK:
			value = null;
			break;
		case Cell.CELL_TYPE_STRING:
			value = cell.getStringCellValue();
			break;
		case Cell.CELL_TYPE_FORMULA:
			value = cell.getCellFormula();
			break;
		default:
			break;
		}
		return value;
	}

}
