package com.dl.springcloud.common.cache;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CabletechListenerObject implements CacheEventListener {

    public Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public void onEvent(CacheEvent cacheEvent) {
        //logger.info(cacheEvent.getKey()+">old:"+cacheEvent.getOldValue()+",new:"+cacheEvent.getNewValue()+",type:"+cacheEvent.getType());
    }

}
