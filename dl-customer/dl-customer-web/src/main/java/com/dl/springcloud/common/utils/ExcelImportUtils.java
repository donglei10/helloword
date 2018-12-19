package com.dl.springcloud.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * 导入转实体，
 *1. 不支持列名相同
 *2. 不支持合并
 *关于默认值的说明：
 *  sheet默认第一个
 *  head 默认第一列
 *  column 默认第一列
 * Excel导入工具
 * @author donglei
 *
 */
public class ExcelImportUtils {
	private static Logger log = Logger.getLogger(ExcelImportUtils.class);
	
	/*** 03格式EXcel */
	public static String XLS = "xls";
	/*** 07格式Excel*/
	public static String XLSX = "xlsx";
	/*** indexHead 导入excel的索引  */
	private Map<Integer,String> indexHead = null;
	/*** sheet 导入excel的sheet，默认第一个sheet  */
	private Sheet sheet;
	/*** sheet 必填列的Key   */
	private String[] notNullColumnIndex = new String[]{} ;
	/***  errorList 错误消息   */
	private List<ExcelErrorEntity> errorList = new ArrayList<ExcelErrorEntity>();
	/***  2014 日期正则处理   */
	private String year_eL = "[0-9]{4}";
	/***  2014-01  日期正则处理   */
	private String month_eL = "[0-9]{4}-[0-9]{2}";
	/***  2014-01-01  日期正则处理   */
	private String day_eL = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
	/***  2014-01-01 22:22  日期正则处理   */
	private String min_eL = "([0-9]{4}-[0-9]{2}-[0-9]{2})*([0-9]{2}(:)[0-9]{2})";
	/***  2014-01-01 22:22:22  日期正则处理   */
	private String ss_eL = "([0-9]{4}-[0-9]{2}-[0-9]{2})*([0-9]{2}(:)[0-9]{2}(:)[0-9])";
	/***  2014 日期格式化定义   */
	private SimpleDateFormat year_format = new SimpleDateFormat("yyyy");
	/***  2014-01 日期格式化定义   */
	private SimpleDateFormat month_format = new SimpleDateFormat("yyyy-MM");
	/***  2014-01-01 日期格式化定义   */
	private SimpleDateFormat day_format = new SimpleDateFormat("yyyy-MM-dd");
	/***  2014-01-01 22:22 日期格式化定义   */
	private SimpleDateFormat min_format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	/***  2014-01-01 22:22:22 日期格式化定义   */
	private SimpleDateFormat ss_format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 空构造函数
	 */
	public ExcelImportUtils(){
		
	}
	/**
	 * 初始化sheet
	 * 和非空列的索引数组
	 * @param stream 文件流程
	 * @param fileName 文件名
	 * @param sheetIndex sheet索引
	 * @param notNullColumnIndex
	 */
	public void init(InputStream stream,String fileName,Integer sheetIndex,String [] notNullColumnIndex){
		try {
			this.notNullColumnIndex = notNullColumnIndex;
			this.sheet = getExcelWorkbook(stream,fileName,sheetIndex);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 构造初始化 sheet
	 * @param stream Excel文件流程
	 * @param fileName 文件名
	 * @param sheetIndex 第几个sheet
	 */
	public ExcelImportUtils(InputStream stream,String fileName,Integer sheetIndex){
		init(stream,fileName,sheetIndex,null);
	}
	/**
	 * 构造初始化 sheet
	 * @param stream Excel文件流程
	 * @param fileName 文件名
	 * @param sheetIndex 第几个sheet
	 */
	public ExcelImportUtils(InputStream stream,String fileName,Integer sheetIndex,String [] col){
		init(stream,fileName,sheetIndex,col);
	}
	/**
	 * 构造初始化 sheet，，默认第一个sheet
	 * @param stream Excel文件流程
	 * @param fileName 文件名
	 */
	public ExcelImportUtils(InputStream stream,String fileName){
		init(stream,fileName,0,null);
	}
	/**
	 * 构造初始化 sheet，，默认第一个sheet
	 * @param stream Excel文件流程
	 * @param fileName 文件名
	 */
	public ExcelImportUtils(InputStream stream,String fileName,String [] col){
		init(stream,fileName,0,col);
	}
	/**
	 * 文件类型是否是Excel格式
	 * @param fileName 文件名
	 * @return
	 */
	public static boolean isExcelFileName(String fileName){
		if(fileName==null || fileName.length()<5){
			return false;
		}
		String extfilename = FileUtills.getExtensionName(fileName);
		if(XLS.equals(extfilename) || XLSX.equals(extfilename)){
			return true;
		}
		return false;
	}
	
	/**
	 * 根据excel、文件流程初始化excel workbook
	 * @param stream 文件流程
	 * @param fileName 文件名
	 * @return
	 * @throws IOException
	 */
	public static Workbook getExcelWorkbook(InputStream stream,String fileName) throws IOException  {
		if(fileName==null || fileName.length()<5){
			return null;
		}
		Workbook wb = null;
		//String extfilename = FileUtills.getExtensionName(fileName);
		wb = new HSSFWorkbook(stream);
		return wb;
	} 
	/**
	 * 根据excel、文件流程初始化excel workbook
	 * @param stream 文件流程
	 * @param fileName 文件名
	 * @return
	 * @throws IOException
	 */
	public static  Sheet getExcelWorkbook(InputStream stream,String fileName,Integer sheetIndex) throws IOException  {
		Workbook wb =  getExcelWorkbook(stream,fileName);
		if(wb!=null){
			return wb.getSheetAt(sheetIndex);
		}
		return null;
	} 
	/**
	 * 读取Cell数据
	 * @param cell
	 * @return
	 */
	public static String getCellValue(Cell cell) {
        String cellValue = "";
        DataFormatter formatter = new DataFormatter();
        if (cell != null) {
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        cellValue = formatter.formatCellValue(cell);
                    } else {
                        double value = cell.getNumericCellValue();
                        int intValue = (int) value;
                        cellValue = value - intValue == 0 ? String.valueOf(intValue) : String.valueOf(value);
                    }
                    break;
                case Cell.CELL_TYPE_STRING:
                    cellValue = cell.getStringCellValue();
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    cellValue = String.valueOf(cell.getBooleanCellValue());
                    break;
                case Cell.CELL_TYPE_FORMULA:
                    cellValue = String.valueOf(cell.getCellFormula());
                    break;
                case Cell.CELL_TYPE_BLANK:
                    cellValue = "";
                    break;
                case Cell.CELL_TYPE_ERROR:
                    cellValue = "";
                    break;
                default:
                    cellValue = cell.toString().trim();
                    break;
            }
        }
        return cellValue.trim();
    }

	/**
	 * 根据sheet 加载数据到list中去
	 * @param clz 类型
	 * @param header 表头
	 * @param sheet sheet 数据
	 * @param headFromIndex 从哪一行下一行开始读取数据标题
	 * @param cellFromIndex 从哪一列开始设置数据
	 * @return
	 */
	public <T> List<T> getExcelDataToList(Class<T> clz, Map<String, String> header, Sheet sheet,Integer headFromIndex,Integer cellFromIndex) {
		List<T> list = new ArrayList<T>();
		headFromIndex = headFromIndex==null?0:headFromIndex;
		cellFromIndex = cellFromIndex==null?0:cellFromIndex;
		if(sheet==null){
			return list;
		} 
		loadHeader(sheet,headFromIndex,cellFromIndex);
		int rowNum = sheet.getLastRowNum();
		log.debug("开始装载数据……");
		for(int i=headFromIndex+1;i<=rowNum;i++){
			Row row = sheet.getRow(i);
			int cellNum = row.getLastCellNum();
			try{
				T instance = clz.newInstance();
				for (int columns = cellFromIndex; columns < cellNum; columns++) {
					Cell cell = row.getCell(columns);
					String val = getCellValue(cell);
					if(ArrayUtils.contains(this.notNullColumnIndex,header.get(indexHead.get(columns))) && StringUtils.isBlank(val)){
						errorList.add(new ExcelErrorEntity((i+1),(columns+1),"【"+indexHead.get(columns)+"】不允许为空"));
						continue;
					}
					loadValue(clz,instance,header.get(indexHead.get(columns)),getCellValue(cell),i,columns);
				}
				list.add(instance);
			}catch(Exception ex){
				errorList.add(new ExcelErrorEntity((i+1),null,"出现数据异常！请检查数据格式！"));
				ex.printStackTrace();
			}
		}
		log.debug("结束装载数据……");
		try {
			sheet.getWorkbook().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 根据sheet 加载数据到list中去,,默认从第二行，第一列开始读取
	 * @param clz 类型
	 * @param header 表头
	 * @param sheet sheet 数据
	 * @return
	 */
	public <T> List<T> getExcelDataToList(Class<T> clz, Map<String, String> header)  {
		return this.getExcelDataToList(clz, header, sheet,null,null);
	}
	/**
	 * 根据sheet 加载数据到list中去,,默认从第二行，第一列开始读取
	 * @param clz 类型
	 * @param header 表头
	 * @param sheet sheet 数据
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public <T> List<T> getExcelDataToList(Class<T> clz, Map<String, String> header, Sheet sheet) {
		return this.getExcelDataToList(clz, header, sheet,null,null);
	}
	/**
	 * 根据sheet 加载数据到list中去,
	 * @param clz 类型
	 * @param header 表头
	 * @param headFromIndex 从哪一行下一行开始读取数据标题
	 * @param cellFromIndex 从哪一列开始设置数据
	 * @return
	 */
	public <T> List<T> getExcelDataToList(Class<T> clz, Map<String, String> header,Integer headFromIndex,Integer cellFromIndex)  {
		return this.getExcelDataToList(clz, header, sheet,headFromIndex,cellFromIndex);
	}
	/**
	 * Excel加载标题
	 * @param sheet Excel导入的sheet
	 * @param headFromIndex from哪一行导入
	 */
	private  void loadHeader(Sheet sheet,int headFromIndex,int cellIndex){
		indexHead = new HashMap<Integer, String>();
		Row row = sheet.getRow(headFromIndex);
		int columns = row.getLastCellNum();
		for (int i = cellIndex; i < columns; i++){
			log.debug("加载标题栏:" + row.getCell(i).getStringCellValue());
			String value = row.getCell(i).getStringCellValue();
			if (null == value) {
				throw new RuntimeException("第"+(i+1)+"列标题不能为空！");
			}
			indexHead.put(i, value);
		}
	}
	/**
	 * 反射给实体赋值
	 * @param clz 实体类型
	 * @param instance 初始化的实体
	 * @param attr 属性
	 * @param value 值
	 */
	private <T> void loadValue(Class<T> clz,T instance,String attr,String value,Integer rowindex,Integer columnindex)  {
		try{
			if(StringUtils.isBlank(attr)){
				return;
			}
			String getMethod = this.initGetMethod(attr);
			Class type = clz.getDeclaredMethod(getMethod,null).getReturnType();
			Method method = clz.getMethod(initSetMethod(attr), type);
			if(type == String.class ){
				method.invoke(instance, value);
			} else if (type == int.class || type == Integer.class){
				method.invoke(instance, Integer.parseInt(value));
			} else if (type == long.class || type == Long.class){
				method.invoke(instance, Long.parseLong(value));
			} else if (type == float.class || type == Float.class){
				method.invoke(instance, Float.parseFloat(value));
			} else if (type == double.class || type == Double.class){
				method.invoke(instance, Double.parseDouble(value));
			}else if (type == Date.class){
				method.invoke(instance, StringToDate(value,rowindex,columnindex));
			}
		}catch(Exception ex){
			errorList.add(new ExcelErrorEntity((rowindex+1),(columnindex+1),"【"+indexHead.get(columnindex)+"】出现数据异常!请检查数据格式！"));
			ex.printStackTrace();
		}
	}
	 
	/**
	 * 转换日期
	 * @param vs
	 * @return
	 */
	private Date StringToDate(String vs,Integer rowindex,Integer columnindex){
		Date date = null;
		if(StringUtils.isBlank(vs)){
			return date;
		}
		 try {
			 if(Pattern.compile(min_eL).matcher(vs).matches()){
				 return min_format.parse(vs);
			 }else if(Pattern.compile(ss_eL).matcher(vs).matches()){
				 return ss_format.parse(vs);
			 }else if(Pattern.compile(month_eL).matcher(vs).matches()){
				 return month_format.parse(vs);
			 }else if(Pattern.compile(day_eL).matcher(vs).matches()){
				 return day_format.parse(vs);
			 }else if(Pattern.compile(year_eL).matcher(vs).matches()){
				 return year_format.parse(vs);
			 }
		} catch (ParseException e) {
			errorList.add(new ExcelErrorEntity((rowindex+1),(columnindex+1),"【"+indexHead.get(columnindex)+"】出现日期类型数据异常!请检查数据格式！"));
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 初始化get方法
	 * @param field 属性
	 * @return
	 */
	private String initGetMethod(String field){
		return "get" + field.substring(0, 1).toUpperCase() + field.substring(1);
	}
	/**
	 * 初始化set方法
	 * @param field 属性
	 * @return
	 */
	private String initSetMethod(String field) {
		return "set" + field.substring(0, 1).toUpperCase() + field.substring(1);
	}
	/**
	 * @return the errorList
	 */
	public List<ExcelErrorEntity> getErrorList() {
		return errorList;
	}
	/**
	 * @param errorList the errorList to set
	 */
	public void setErrorList(List<ExcelErrorEntity> errorList) {
		this.errorList = errorList;
	}

}
