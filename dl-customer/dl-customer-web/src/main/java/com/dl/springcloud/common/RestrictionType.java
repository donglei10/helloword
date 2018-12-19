package com.dl.springcloud.common;

/**
 * 扩展map定义查询接口
 * @author donglei
 *
 * @param <T> 查询类型
 * @param <K> map key 值
 * @param <V> map value 值
 */
public  class RestrictionType<K,V,T>  {
	
	public RestrictionType(){
		
	}
	public RestrictionType(K key,V value,T type){
		this.key = key;
		this.value = value;
		this.type = type;
	}
	K key;
	V value;
	T type;
	
	/**
	 * @return the key
	 */
	public K getKey() {
		return key;
	}
	/**
	 * @param key the key to set
	 */
	public void setKey(K key) {
		this.key = key;
	}
	/**
	 * @return the value
	 */
	public V getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(V value) {
		this.value = value;
	}
	/**
	 * @return the type
	 */
	public T getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(T type) {
		this.type = type;
	}
	
	
}
