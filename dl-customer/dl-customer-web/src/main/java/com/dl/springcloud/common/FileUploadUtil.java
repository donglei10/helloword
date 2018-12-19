package com.dl.springcloud.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dl.springcloud.base.entity.BaseFile;



/**
 * 文件上传工具
 * @author donglei
 *
 */
public class FileUploadUtil {
	/**
	 * 根据配置文件读取
	 * 文件上传的根目录路径
	 */
	private String KEY = "file_path";
	
	private   String [] extFileName = new String []{
			".text",".xml",".zip",
			".xls",".xlsx",".xlc",".xlm",".xlt",".xlw",
			".doc",".dot",
			".ppt",".pps",".pot",
			".png",".jpg",".jpeg",".gif"
			}; 
	
	private   boolean isCanUserFile(String fileName){
		String ext = fileName.substring(fileName.lastIndexOf("."));
		for (int i = 0; i < extFileName.length; i++) {
			if(extFileName[i].equals(ext)){
				return true;
			}
		}
		return false;
	}
	 
   /**
    * 单文件上传
    * @param request 请求
    * 	非页面传来的参数，需要两个，，modelName：管理上传模块的名称，fileName：上传文件时候的属性名
    * @return
    * @throws Exception
    */
    public BaseFile fileUpload(MultipartFile file,String modelName) throws Exception {  
    	BaseFile baseFile =null;
        // 获得文件名：   
    	if(file!=null){
			baseFile = new BaseFile();
    		String fileName = file.getOriginalFilename();  
    		if(StringUtils.isBlank(fileName)){
    			return null;
    		}
    		if(!isCanUserFile(fileName)){
    			return null;
    		}
    		String path = getNeedPath(fileName,modelName);
    		File uploadFile = new File(path);  
    		file.transferTo(uploadFile);  
    		initBaseFile(baseFile, fileName, uploadFile,modelName,path);
    	}
        return baseFile;
    } 
    
    /**
     * 多文件上传
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public List<BaseFile> fileUploads(String modelName,MultipartHttpServletRequest multrequest) throws Exception {  
    	List<BaseFile> list= new ArrayList<BaseFile>();
    	BaseFile baseFile =null;
    	Map<String, MultipartFile> fileMap = multrequest.getFileMap();  
    	String fileName = null;  
    	for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {  
    		baseFile = new BaseFile();
    		MultipartFile mf = entity.getValue();  
    		fileName = mf.getOriginalFilename();  
    		if(StringUtils.isBlank(fileName)){
    			continue;
    		}
    		if(!isCanUserFile(fileName)){
    			continue;
    		}
    		String path = getNeedPath(fileName,modelName);
    		File uploadFile = new File(path);  
    		mf.transferTo(uploadFile);
    		initBaseFile(baseFile, fileName, uploadFile,modelName,path);
    		list.add(baseFile);
    	}  
    	return list;
    }
    /**
     * 设置文件参数
     * @param baseFile 文件对象
     * @param fileName 文件名
     * @param uploadFile 上传文件
     */
	private void initBaseFile(BaseFile baseFile, String fileName,
			File uploadFile ,String modelName,String path) {
		baseFile.setFileName(fileName);
		baseFile.setFileId(null);
		baseFile.setCreateTime(new Date());
		baseFile.setState(SystemConstant.STATE_INSERT);
		baseFile.setFileExt(null==fileName?"":fileName.substring(fileName.lastIndexOf(".")));
		baseFile.setFilePath(path);
		baseFile.setFileSize(uploadFile.length());
		baseFile.setModelName(modelName);
	}
    
	/**
	 * 保存路径
	 * @param realFileName 上传的文件名
	 * @param modelName 
	 * @return
	 */
	private String getNeedPath(String realFileName, String modelName){
		if(StringUtils.isBlank(realFileName)){
			return null;
		}
		// 获取路径  
        Date date = new Date();
        SimpleDateFormat dayformat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat timeformat = new SimpleDateFormat("HHmmss");
        String ctxPath = PropertiesUtil.getProperty(KEY) + "/" + modelName+ "/"+ dayformat.format(date)+"/"+ timeformat.format(date)  ;
        File file = new File(ctxPath);  
        if (!file.exists()  && !file.isDirectory()) {  
            file.mkdirs();  
        }
        ctxPath += "/"+ UUID.randomUUID()+realFileName.substring(realFileName.lastIndexOf("."));  
        return ctxPath;
	}
	
	/**
	 * 文件上传
	 * @param inputStream 文件流程
	 * @param modelName 模块名称
	 * @param filename 文件名
	 * @return
	 */
	public BaseFile fileUpload(InputStream inputStream, String modelName,
			String fileName) {
		if(fileName==null || "".equals(fileName)){
			return null;
		}
		BaseFile baseFile = new BaseFile();
		String path = getNeedPath(fileName,modelName);
		File uploadFile = new File(path);  
		try {
			inputStreamToFile(inputStream,uploadFile);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		initBaseFile(baseFile, fileName, uploadFile,modelName,path);
		return baseFile;
	}
   
	/**
	 * 文件流程转文件
	 * @throws IOException 
	 * 
	 */
	private void inputStreamToFile(InputStream in,File file) throws IOException{
		FileOutputStream outfile = new FileOutputStream(file);
		byte[] data = new byte[1024];
		int count = -1;
		while((count = in.read(data,0,1024)) != -1){
			outfile.write(data, 0, count);
		}
		outfile.flush();
		outfile.close();
	}

	 /**
	    * 单文件上传
	    * @param request 请求
	    * 	非页面传来的参数，需要两个，，modelName：管理上传模块的名称，fileName：上传文件时候的属性名
	    * @return
	    * @throws Exception
	    */
	public BaseFile fileUpload(MultipartFile file, String modelName,
			Map<String, Object> data) throws Exception {  
	    	BaseFile baseFile =null;
	        // 获得文件名：   
	    	if(file!=null){
				baseFile = new BaseFile();
	    		String fileName = file.getOriginalFilename();  
	    		if(StringUtils.isBlank(fileName)){
	    			return null;
	    		}
	    		if(!isCanUserFile(fileName)){
	    			return null;
	    		}
	    		String path = getNeedPath(fileName,modelName);
	    		File uploadFile = new File(path);  
	    		file.transferTo(uploadFile);  
	    		baseFile.setFileName(fileName);
	    		baseFile.setFileId(null);
	    		baseFile.setCreateTime(new Date());
	    		baseFile.setState(SystemConstant.STATE_INSERT);
	    		baseFile.setFileExt(null==fileName?"":fileName.substring(fileName.lastIndexOf(".")));
	    		baseFile.setFilePath(path);
	    		baseFile.setFileSize(uploadFile.length());
	    		baseFile.setModelName(modelName);
	    		baseFile.setFileRemark((String)data.get("remark"));
	    	}
	        return baseFile;
	    } 
}
