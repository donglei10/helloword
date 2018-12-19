package com.dl.springcloud.base.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页对象
 * @author donglei
 *
 * @param <T>
 */
public class Page<T> {

	/**
	 *总记录数，在分页控件创建时初始的值。
	 */
	private Integer total = 0;
	/**
	 * 页面大小。
	 */
	private Integer pageSize = 10;
	/**
	 * 在分页控件创建的时候显示的页数。
	 */
	private Integer pageNumber = 1;
	/**
	 * 总页数
	 */
	private Integer pageAllNumber = 0;
	
	/**
	 * 数据展示
	 */
	private List<T> rows =  new ArrayList<T>();
	/**
	 * @return the total
	 */
	public Integer getTotal() {
		return total;
	}
	
	/**
	 * @return the total
	 */
	public Integer getStartRows() {
		return (this.getPageNumber() - 1) * this.getPageSize();
	}
	
	/**
	 * @return the total
	 */
	public Integer getEndRows() {
		return this.getPageNumber() * this.getPageSize();
	}
	
	
	/**
	 * @param total the total to set
	 */
	public void setTotal(Integer total) {
		this.total = total;
	}
	/**
	 * @return the pageSize
	 */
	public Integer getPageSize() {
		return pageSize;
	}
	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	/**
	 * @return the pageNumber
	 */
	public Integer getPageNumber() {
		return pageNumber;
	}
	/**
	 * @param pageNumber the pageNumber to set
	 */
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	/**
	 * @return the rows
	 */
	public List<T> getRows() {
		return rows;
	}
	/**
	 * @param rows the rows to set
	 */
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	/**
	 * @return the pageAllNumber
	 */
	public Integer getPageAllNumber() {
		pageAllNumber = this.getTotal()%this.getPageSize();
		return pageAllNumber;
	}
	/**
	 * @param pageAllNumber the pageAllNumber to set
	 */
	public void setPageAllNumber(Integer pageAllNumber) {
		this.pageAllNumber = pageAllNumber;
	}
	
}
