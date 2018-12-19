package com.dl.springcloud.system.service;

import java.util.List;

import com.dl.springcloud.base.service.BaseService;
import com.dl.springcloud.system.entity.BaseDic;

 
/**
 * 字典
 * @author donglei
 *
 */
public interface BaseDicService extends BaseService<BaseDic, Integer>{
	
	/**
	 * 根据字段类型和字典编码查询字典值
	 * @param dicCode 字典编码
	 * @param dicType 字典类型
	 * @return
	 */
	BaseDic getEntityByCodeAndType(String dicCode, String dicType);
	/**
	 * 根据字典类型查询 字典编码
	 * @param dicType 字典类型
	 * @return
	 */
	List<BaseDic> getListDic(String dicType);

}
