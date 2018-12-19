package com.dl.springcloud.common.utils;
/**
 * Excel导入错误消息
 * @author donglei
 *
 */
public class ExcelErrorEntity {

	public ExcelErrorEntity(){
		
	}
	/**
	 * 构建提示实体
	 * @param rownum
	 * @param cellnum
	 * @param message
	 */
	public ExcelErrorEntity(Integer rownum,Integer cellnum,String message){
		this.rownum = rownum;
		this.cellnum = cellnum;
		this.message = message;
		this.rownumStr = this.getRownumStr();
		this.cellnumStr = this.getCellnumStr();
	}
	private String rownumStr;
	
	private String cellnumStr;
	
	private Integer rownum;
	
	private Integer cellnum;
	
	private String message;
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		if(rownum!=null){
			str.append(rownumStr);
		}
		if(cellnum!=null){
			str.append(cellnumStr);
		}
		str.append(getMessage());
		return str.toString();
	}
	/**
	 * @return the rownum
	 */
	public Integer getRownum() {
		return rownum;
	}
	/**
	 * @param rownum the rownum to set
	 */
	public void setRownum(Integer rownum) {
		this.rownumStr = "第"+rownum+"行";
		this.rownum = rownum;
	}
	/**
	 * @return the cellnum
	 */
	public Integer getCellnum() {
		return cellnum;
	}
	/**
	 * @param cellnum the cellnum to set
	 */
	public void setCellnum(Integer cellnum) {
		this.cellnumStr = "第"+cellnum+"列";
		this.cellnum = cellnum;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the rownumStr
	 */
	public String getRownumStr() {
		if((rownumStr==null || "".equals(rownumStr))&&null!=getRownum()){
			return "第"+getRownum()+"行";
		}
		return rownumStr;
	}
	/**
	 * @param rownumStr the rownumStr to set
	 */
	public void setRownumStr(String rownumStr) {
		if((rownumStr==null || "".equals(rownumStr))&&null!=getRownum()){
			this.rownumStr = "第"+getRownum()+"行";
		}
		this.rownumStr = rownumStr;
	}
	/**
	 * @return the cellnumStr
	 */
	public String getCellnumStr() {
		if((cellnumStr==null || "".equals(cellnumStr))&&null!=getCellnum()){
			cellnumStr = "第"+getCellnum()+"列";
		}
		return cellnumStr;
	}
	/**
	 * @param cellnumStr the cellnumStr to set
	 */
	public void setCellnumStr(String cellnumStr) {
		if((cellnumStr==null || "".equals(cellnumStr))&&null!=getCellnum()){
			this.cellnumStr = "第"+getCellnum()+"列";
		}
		this.cellnumStr = cellnumStr;
	}
	
	
	
}
