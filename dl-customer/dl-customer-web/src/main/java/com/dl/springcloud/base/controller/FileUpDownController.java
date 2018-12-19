package com.dl.springcloud.base.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dl.springcloud.base.entity.BaseFile;
import com.dl.springcloud.base.entity.BaseFileUpDown;
import com.dl.springcloud.base.service.FileUpDownService;


/**
 * 文件上传和下载
 * @author donglei
 *
 */
@Controller
@RequestMapping("/fileUpDownController")
public class FileUpDownController extends BaseController<BaseFileUpDown> {
	
	@Autowired
	private FileUpDownService fileUpDownService;
	
	/**
	 * 文件上传
	 * @throws Exception 
	 */
	@RequestMapping(value="/upload", method = RequestMethod.POST)
	@ResponseBody
	public String uploadFile() throws Exception{
		
		return "success";
	}
	/**
	 * 文件上传
	 * @throws Exception 
	 */
	@RequestMapping(value="/showBigImage", method = {RequestMethod.POST,RequestMethod.GET})
	public String showBigImage(String pathImage) throws Exception{
		getRequest().setAttribute("pathImage", pathImage);
		return "/sys/base_big_image";
	}
	
	/**
	 * 文件下载
	 * @throws IOException 
	 */
	@RequestMapping(value="/download/{id}", method ={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView downloadFile(@PathVariable int id,int bzid) throws Exception{
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("bzid",bzid);
		BaseFile baseFile = fileUpDownService.getFileParamsMap(map,getBaseUser());
		super.download(baseFile);
	    return null;
	   }
	
	/**
	 * 删除文件
	 * @throws IOException 
	 */
	@RequestMapping(value="/delete/{id}", method ={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String deleteFile(@PathVariable int id,int bzid) throws Exception{
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("bzid",bzid);
		boolean bl = fileUpDownService.delFileParamsMap(map,getBaseUser());
		if(!bl){
			return "fault";
		}
		return "success";
	}  
	

	/**
	 * 获取文件列表
	 * @throws IOException 
	 */
	@RequestMapping(value="/model/{bzid}/{model}", method ={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public void downloadFiles(@PathVariable String model,@PathVariable int bzid) throws Exception{
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("model", model);
		map.put("bzid",bzid);
		List<BaseFile> baseFiles = fileUpDownService.getFileListParamsMap(map,getBaseUser());
		convertObjToJson(baseFiles);
	  }  
	 
	 
	
}
