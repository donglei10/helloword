package com.dl.springcloud.common.cache;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/21.
 */
public class CacheModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private String key;

    private Map valueMap;

    private List valueList;

    public CacheModel(Map valueMap){
        this.valueMap = valueMap;
    }

    public CacheModel(List valueList){
        this.valueList = valueList;
    }

    public CacheModel(){
    }

    public CacheModel(String key){
        this.key = key;
    }

    public CacheModel(String key, Map valueMap){
       this.key = key;
       this.valueMap = valueMap;
    }
    public CacheModel(Map valueMap, List valueList){
        this.valueList = valueList;
        this.valueMap = valueMap;
    }
    public CacheModel(String key, List valueList){
        this.key = key;
        this.valueList = valueList;
    }

    public CacheModel(String key, Map valueMap, List valueList){
        this.key = key;
        this.valueMap = valueMap;
        this.valueList = valueList;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Map getValueMap() {
        return valueMap;
    }

    public void setValueMap(Map valueMap) {
        this.valueMap = valueMap;
    }

    public List getValueList() {
        return valueList;
    }

    public void setValueList(List valueList) {
        this.valueList = valueList;
    }
}
