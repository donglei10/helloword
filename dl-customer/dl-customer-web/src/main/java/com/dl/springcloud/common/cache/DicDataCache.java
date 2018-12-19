package com.dl.springcloud.common.cache;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import org.ehcache.PersistentUserManagedCache;
import org.ehcache.config.builders.CacheEventListenerConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.builders.UserManagedCacheBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.core.spi.service.LocalPersistenceService;
import org.ehcache.event.EventType;
import org.ehcache.impl.config.persistence.DefaultPersistenceConfiguration;
import org.ehcache.impl.config.persistence.UserManagedPersistenceContext;
import org.ehcache.impl.persistence.DefaultLocalPersistenceService;

public class DicDataCache  {
	

    public static PersistentUserManagedCache<String, CacheModel> cache = null;
    private  static LocalPersistenceService persistenceService;
    public static String path = "";
    
    public static  void init(){
        if(path==null || path.equals("")){
            System.out.println("初始化失败！");
            return;
        }
        persistenceService = new DefaultLocalPersistenceService(new DefaultPersistenceConfiguration(new File(path, "chacedata")));
        cache = UserManagedCacheBuilder.newUserManagedCacheBuilder(String.class, CacheModel.class)
                .with(new UserManagedPersistenceContext<String, CacheModel>("cache-name", persistenceService))
                .withEventExecutors(Executors.newSingleThreadExecutor(), Executors.newFixedThreadPool(5))
                .withEventListeners(CacheEventListenerConfigurationBuilder.newEventListenerConfiguration(new CabletechListenerObject(), EventType.CREATED, EventType.UPDATED, EventType.REMOVED).asynchronous() .unordered())
                .withResourcePools(ResourcePoolsBuilder.newResourcePoolsBuilder()
                        .heap(500L,EntryUnit.ENTRIES)
                        .disk(2L,MemoryUnit.GB, true)
                )
                .build(true);
    }

    public  static void close(){
        cache.close();
        persistenceService.stop();
    }
	
	
}
