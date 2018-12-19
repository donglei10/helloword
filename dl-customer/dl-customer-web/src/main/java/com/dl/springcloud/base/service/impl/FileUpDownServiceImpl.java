package com.dl.springcloud.base.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dl.springcloud.base.entity.BaseFile;
import com.dl.springcloud.base.service.FileUpDownService;
import com.dl.springcloud.system.entity.BaseUser;



/**
 * 文件上传下载
 * @author donglei
 *
 */
@Service
public class FileUpDownServiceImpl extends BaseServiceImpl<BaseFile, Integer> implements FileUpDownService {

	
	@Override
	public BaseFile getFileParamsMap(Map<String, Object> map,
			BaseUser baseUser) {
		Integer id =(Integer) map.get("id");
		Integer bzid =(Integer) map.get("bzid");
		return null;//dao.getBaseFileByIdAndBzid(id,bzid);
	}


	@Override
	public List<BaseFile> getFileListParamsMap(Map<String, Object> map,
			BaseUser baseUser) {
		Integer bzid =(Integer) map.get("bzid");
		String model = (String)map.get("model");
		return null;//dao.getBaseFileByIdAndBzid(model,bzid);
	}

	@Override
	public boolean delFileParamsMap(Map<String, Object> map, BaseUser baseUser) {
		Integer id =(Integer) map.get("id");
		Integer bzid =(Integer) map.get("bzid");
		BaseFile bf = null;//dao.getBaseFileByIdAndBzid(id,bzid);
		//dao.delete(bf);
		//bf = dao.getBaseFileByIdAndBzid(id,bzid);
		if(bf==null){
			return true;
		}
		return false;
	}

}
