package com.dl.springcloud.system.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dl.springcloud.base.entity.Page;
import com.dl.springcloud.base.service.impl.BaseServiceImpl;
import com.dl.springcloud.system.dao.DictionaryDao;
import com.dl.springcloud.system.model.Dictionary;
import com.dl.springcloud.system.service.DicService;

@Service
public class DicServiceImpl extends BaseServiceImpl<Dictionary, Integer> implements DicService {

	@Autowired
	private DictionaryDao dicDao;
	
	@Override
	public List<Dictionary> getDicListData() {
		
		return dicDao.findAll();
	}

	@Override
	public void deleteByIds(String ids) {
		if(StringUtils.isBlank(ids)) {
			return;
		}
		String idArray [] = ids.split(",");
		if(ArrayUtils.isEmpty(idArray)) {
			return;
		}
		for (int i = 0; i < idArray.length; i++) {
			dicDao.delete(Integer.valueOf(idArray[i]));
		}
	}

	@Override
	public void getDicPageData(Page<Dictionary> page, Map<String, Object> params) {
		dicDao.findIncomeDailysByPage(params, page);
	}

	@Override
	public Dictionary getDictionary(Dictionary dictionary) {
		if(dictionary==null) {
			return null;
		}
		if(dictionary.getId()!=null && dictionary.getId()!=0) {
			return dicDao.findOne(dictionary.getId());
		}
		return null;
	}

	@Override
	public void saveDictionary(Dictionary dictionaryEntity) {
		dicDao.save(dictionaryEntity);
	}

}
