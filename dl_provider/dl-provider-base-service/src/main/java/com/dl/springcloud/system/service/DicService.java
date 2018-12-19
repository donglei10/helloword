package com.dl.springcloud.system.service;

import java.util.List;
import java.util.Map;

import com.dl.springcloud.base.entity.Page;
import com.dl.springcloud.system.model.Dictionary;

public interface DicService {

	List<Dictionary> getDicListData();
	/**
	 * 根据 ids 删除
	 * @param ids
	 */
	void deleteByIds(String ids);
	/**
	 * 查询对象
	 * @param ininPage
	 * @param initParams
	 */
	void getDicPageData(Page<Dictionary> ininPage, Map<String, Object> initParams);
	/**
	 * 查询字典对象
	 * @param dictionary
	 * @return
	 */
	Dictionary getDictionary(Dictionary dictionary);
	/**
	 * 保存字典对象
	 * @param dictionary
	 * @return
	 */
	void saveDictionary(Dictionary dictionaryEntity);
}
