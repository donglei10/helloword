package com.dl.springcloud.common.cache;


public enum CabletechModel {
    C30_WPLAN("C30_WPLAN") //传输巡检
    ;


    private String key;
    private CabletechModel(String key){
        this.key = key;
    }
    public String getKey(){
        return this.key;
    }
}