package com.train.excel.dao;

import java.util.List;

import com.train.excel.domain.StationTrain;

/**
 * 
 * @author and04
 *
 */
public interface StationTrainDao {

	/**
	 * save
	 * 
	 * @param st
	 */
	void save(StationTrain st);

	/**
	 * 根据文件Id删除
	 * 
	 * @param fileId
	 */
	void deleteByFileId(String fileId);
	
	/**
	 * 
	 * @param fileId
	 * @return
	 */
	List<StationTrain> getByFileId(String fileId);
}
