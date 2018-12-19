package com.dl.springcloud.base.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dl.springcloud.base.entity.BaseFile;
import com.dl.springcloud.base.entity.Page;
import com.dl.springcloud.common.FileDownLoadUtil;
import com.dl.springcloud.common.FileUploadUtil;
import com.dl.springcloud.system.entity.BaseUser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * 基础跳转
 * @author donglei
 *
 * @param <T>
 */
public abstract class BaseController<T> {
	
	public Logger logger = Logger.getLogger(this.getClass());
	public String datefomatSecond = "yyyy-MM-dd HH:mm:ss";
	public String datefomatMin = "yyyy-MM-dd HH:mm";
	public String datefomatDay = "yyyy-MM-dd";
	public String datefomatMonth = "yyyy-MM";
	private Page<T> page = new Page<T>();
	private BaseUser baseUser;
	protected String EXCEL =  "/common/excel_tip_list";
	@Autowired
	protected HttpServletRequest request;
	@Autowired
	protected HttpServletResponse response;
//	@InitBinder
//	protected void ininBinder(WebDataBinder binder ) {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
//	}
	
	/**
	 * 初始化分页对象
	 * @return
	 */
	protected Page<T> ininPage(){
		String pageNumber = request.getParameter("page");
		String pageSize = request.getParameter("rows");
		page = new Page<T>();
		if(StringUtils.isNotBlank(pageSize)){
			page.setPageSize(Integer.valueOf(pageSize));
		}
		if(StringUtils.isNotBlank(pageNumber)){
			page.setPageNumber(Integer.valueOf(pageNumber));
		}
		if(page.getPageNumber() == null || page.getPageNumber()  ==0){
			page.setPageNumber(1);
		}
		return page;
	}
	
	/**
	 * 将单个对象转换成JSON对象
	 * 默认日期转换：2014-01-01 12:00:00
	 * @param obj
	 *            Object
	 */
	protected String convertObjToJsonStr(Object obj) {
		try {
			Gson gson = new GsonBuilder().setDateFormat(this.datefomatSecond).create();
			String gstr = gson.toJson(obj);
			System.out.println("-------------->>转换后的结果是："+gstr);
			return gstr;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("处理JSON对象出错", e);
			return "";
		}
	}
	/**
	 * 将单个对象转换成JSON对象
	 * 默认日期转换：2014-01-01 12:00:00
	 * @param obj
	 *            Object
	 */
	protected String convertObjToJsonStr(Object obj,String dateFomat) {
		try {
			String fomat =this.datefomatSecond;
			if(StringUtils.isNotBlank(dateFomat)){
				fomat = dateFomat;
			}
			Gson gson = new GsonBuilder().setDateFormat(fomat).create();

			return gson.toJson(obj);
		} catch (Exception e) {
			logger.error("处理JSON对象出错", e);
			return "";
		}
	}

	/**
	 * 将单个对象转换成JSON对象
	 *  默认日期转换：2014-01-01 12:00:00
	 * @param obj
	 *            Object
	 */
	protected void convertObjToJson(Object obj) {
		try {
			response.setContentType("application/json;charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			Gson gson = new GsonBuilder().setDateFormat(this.datefomatSecond)
					.create();
			PrintWriter out = response.getWriter();
			out.print(gson.toJson(obj));
			out.flush();
		} catch (IOException e) {
			logger.error("处理JSON对象出错", e);
		}
	}
	/**
	 * 将单个对象转换成JSON对象
	 *  默认日期转换：2014-01-01 12:00:00
	 * @param obj
	 *            Object
	 */
	protected void convertObjToJson(Object obj,String dateFomat) {
		try {
			response.setContentType("application/json;charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			String fomat =this.datefomatSecond;
			if(StringUtils.isNotBlank(dateFomat)){
				fomat = dateFomat;
			}
			Gson gson = new GsonBuilder().setDateFormat(fomat).create();
			PrintWriter out = response.getWriter();
			out.print(gson.toJson(obj));
			out.flush();
		} catch (IOException e) {
			logger.error("处理JSON对象出错", e);
		}
	}
	

	/**
	 * 向界面输出html
	 * 
	 * @param html
	 *            String
	 * @param isCache
	 *            boolean
	 * @throws IOException
	 */
	protected void outPrint(String html, boolean isCache) throws IOException {
		if (isCache) {
			nocache();
		}
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(html);
		out.flush();
	}

	/**
	 * 去除缓存
	 */
	protected void nocache() {
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
	}

	
	/**
	 * 文件上传
	 * @param modelName 文件划分的模块名称
	 * @return
	 * @throws Exception
	 */
	protected BaseFile upload(String modelName,MultipartFile file) throws Exception {  
        FileUploadUtil fileUploadUtil = new FileUploadUtil();
        request.setAttribute("modelName", modelName);
        return  fileUploadUtil.fileUpload(file,modelName);
    }
	
	/**
	 * 文件上传
	 * @param modelName 文件划分的模块名称
	 * @return
	 * @throws Exception
	 */
	protected List<BaseFile> uploads(String modelName,MultipartHttpServletRequest multrequest) throws Exception {  
        FileUploadUtil fileUploadUtil = new FileUploadUtil();
        request.setAttribute("modelName", modelName);
        return  fileUploadUtil.fileUploads(modelName,multrequest);
    }
	
	
    /**
     * 文件下载
     * @param params 下载参数
     *   filePath 包含路径的文件
     *	 fileName   文件名含扩展名
     *	 name 文件名
     * @throws IOException
     */
    protected ResponseEntity<byte[]> downloadMvc(BaseFile baseFile) throws IOException{
    	FileDownLoadUtil downLoadUtil = new FileDownLoadUtil();
    	return downLoadUtil.download(baseFile);
    }
    /**
     * 
     * @param params 下载参数
     * 	 filePath 包含路径的文件
     *	 fileName   文件名含扩展名
     *	 name 文件名
     * @param request 请求
     * @param response 响应
     * @throws Exception 
     */
    protected void download(BaseFile baseFile) throws Exception{
    	FileDownLoadUtil downLoadUtil = new FileDownLoadUtil();
    	downLoadUtil.download(baseFile,getResponse());
    }
    
    /**
     * 
     * @param params 下载参数
     * 	 filePath 包含路径的文件
     *	 fileName   文件名含扩展名
     *	 name 文件名
     * @param request 请求
     * @param response 响应
     * @throws Exception 
     */
    protected void downloadExcel(Workbook workbook) throws Exception{
    	FileDownLoadUtil downLoadUtil = new FileDownLoadUtil();
    	downLoadUtil.download(workbook,response,request);
    }
    
	/**
	 * 定义Excel导出方法
	 * @return
	 */
	public List<T> getExcelData() {
		return null;
	}
	
	/**
	 * @return the page
	 */
	public Page<T> getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(Page<T> page) {
		this.page = page;
	}

	/**
	 * @return the request
	 */
	public HttpServletRequest getRequest() {
		return request;
	}

	/**
	 * @param request the request to set
	 */
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * @return the response
	 */
	public HttpServletResponse getResponse() {
		return response;
	}

	/**
	 * @param response the response to set
	 */
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	/**
	 * @return the baseUser
	 */
	public BaseUser getBaseUser() {
		baseUser = (BaseUser) this.getRequest().getSession().getAttribute("WEB_LOGIN_USER");
		return baseUser;
	}

	/**
	 * @param baseUser the baseUser to set
	 */
	public void setBaseUser(BaseUser baseUser) {
		this.baseUser = baseUser;
	}
	
	
}