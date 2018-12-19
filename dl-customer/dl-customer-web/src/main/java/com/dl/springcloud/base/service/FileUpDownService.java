package com.dl.springcloud.base.service;

import java.util.List;
import java.util.Map;

import com.dl.springcloud.base.entity.BaseFile;
import com.dl.springcloud.system.entity.BaseUser;


 

/**
 * 文件上传下载业务处理
 * @author donglei
 *
 */
public interface FileUpDownService extends BaseService<BaseFile, Integer> {

	/**
	 * 文件下载，参数查询
	 * @param map
	 * 	id 文件id
	 *  bzid 业务id
	 * @param baseUser
	 * @return
	 */
	BaseFile getFileParamsMap(Map<String, Object> map,
			BaseUser baseUser);
	/**
	 * 查询文件列表
	 * @param map 参数
	 * @param baseUser 对象
	 * @return
	 */
	List<BaseFile> getFileListParamsMap(Map<String, Object> map,
			BaseUser baseUser);
	/**
	 * 删除文件
	 * @param map
	 * @param baseUser
	 */
	boolean delFileParamsMap(Map<String, Object> map, BaseUser baseUser);

}
