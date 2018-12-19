package com.dl.springcloud.system.service.impl;


import java.util.List;

import org.springframework.stereotype.Service;

import com.dl.springcloud.base.entity.Page;
import com.dl.springcloud.base.service.impl.BaseServiceImpl;
import com.dl.springcloud.system.entity.BaseDic;
import com.dl.springcloud.system.service.BaseDicService;

 

/**
 * 
 * @author donglei	
 *
 */
@Service
public class BaseDicServiceImpl extends BaseServiceImpl<BaseDic, Integer> implements BaseDicService {
	 
	
	
	/* (non-Javadoc)
	 * @see com.ustctech.base.service.BaseServiceImpl#getPageList(java.lang.Object, com.ustctech.base.entity.Page)
	 */
	public Page<BaseDic> getPageList(BaseDic baseDic, Page<BaseDic> page) {
		
		return null;//dao.getPageList(baseDic,page);
	}

 
	/* (non-Javadoc)
	 * @see com.ustctech.base.service.BaseServiceImpl#saveEntity(java.lang.Object)
	 */
	public BaseDic saveEntity(BaseDic baseDic) {
		
		return baseDic;
	}

	@Override
	public BaseDic getEntityByCodeAndType(String dicCode, String dicType) {
		
		return null;//dao.getEntityByCodeAndType(dicCode,dicType);
	}

	@Override
	public List<BaseDic> getListDic(String dicType) {
		 
		return null;//dao.getListDic(dicType);
	}
	
	

}
