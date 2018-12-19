package com.dl.springcloud.common;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.dl.springcloud.base.entity.BaseFile;



/**
 * 文件下载
 * @author donglei
 *
 */
public class FileDownLoadUtil {

	/**
	 * 文件下载
	 * @param id 文件id
	 * @return 
	 * @throws IOException
	 */
    public ResponseEntity<byte[]> download(BaseFile baseFile) throws IOException { 
        HttpHeaders headers = new HttpHeaders();  
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);  
        headers.setContentDispositionFormData(baseFile.getFileName(), baseFile.getFileName());  
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(new File(baseFile.getFilePath())),headers,HttpStatus.CREATED);  
    }  
    
    /**
     * 文件下载
     * @param params
     * @param request
     * @param response
     * @throws Exception
     */
    public void download(BaseFile baseFile, HttpServletResponse response)  
            throws Exception {  
    	if(baseFile==null){
    		return;
    	}
    	OutputStream os = response.getOutputStream();
            try {
            	response.reset();
            	if(baseFile!=null && baseFile.getBaseFileUpDown()!=null && "headimage".equals(baseFile.getBaseFileUpDown().getType())){
            	}else{
            		response.setHeader("Content-Disposition", "attachment; filename=\""+ java.net.URLEncoder.encode(baseFile.getFileName(), "UTF-8") +"\"");
            		response.setContentType("application/octet-stream; charset=utf-8");
            	}
            	response.setContentLength(baseFile.getFileSize().intValue());
                os.write(FileUtils.readFileToByteArray(new File(baseFile.getFilePath())));
                os.flush();
            } finally {
                if (os != null) {
                    os.close();
                }
         }
    }  
    
    

    /**
     * 文件下载
     * @param params
     * @param request
     * @param response
     * @throws Exception
     */
    public void download(Workbook workbook, HttpServletResponse response, HttpServletRequest request)  
            throws Exception {  
    	if(workbook==null){
    		return;
    	}
    	OutputStream out = null;
        try {
        	response.reset();
        	String fileName =encodingFileName(request.getParameter("title"))+".xls";
        	response.setContentType("application/octet-stream; charset=utf-8");
        	response.setHeader("Content-type","application/x-msdownload;charset=utf-8");
        	response.setHeader("Content-Disposition", "attachment; filename=\""+  fileName +"\"");
        	out = response.getOutputStream();
        	workbook.write(out);
        	workbook.close();
        	out.flush();
        } finally {
	        if (out != null) {
	        	out.close();
	        }
        }
    }  
    
    public   String encodingFileName(String fileName) {
        String returnFileName = "";
        try {
            returnFileName = URLEncoder.encode(fileName, "UTF-8");
            returnFileName = StringUtils.replace(returnFileName, "+", "%20");
            if (returnFileName.length() > 150) {
                returnFileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
                returnFileName = StringUtils.replace(returnFileName, " ", "%20");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnFileName;
    }
}
