package com.dl.springcloud.system;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dl.springcloud.base.BaseController;
import com.dl.springcloud.base.entity.Page;
import com.dl.springcloud.system.model.Dictionary;
import com.dl.springcloud.system.service.DicService;

 
/**
 * Created by Administrator on 2017/10/29.
 */
//@RestController 
//@RefreshScope
@Controller
public class DicController extends BaseController<Dictionary> {

	@Resource(name="dicServiceImpl")
	private DicService dicService;
	
	@RequestMapping("/dic/list")
    public String index(Model model, HttpServletResponse response) {
        model.addAttribute("name", "simonsfan");
        System.out.println("-->");
        return "system/dic_list";
    }
    
	@RequestMapping("/dic/listData")
	@ResponseBody
	public void listData(@RequestBody Map<String,Object> reqMap) {
		Page page = ininPage();
		dicService.getDicPageData(page,reqMap);
		convertObjToJson(page);
	}
	 
	@RequestMapping("/dic/deleteByIds")
	@ResponseBody
	public void deleteByIds(Model model, HttpServletRequest req) {
		
		dicService.deleteByIds((String)req.getAttribute("ids"));
	}
	
	@RequestMapping("/dic/edit")
    public String edit(Model model, Dictionary dictionary) {
		Dictionary dictionaryEntity = dicService.getDictionary(dictionary);
		if(dictionaryEntity==null) {
			dictionaryEntity = new Dictionary();
		}
		model.addAttribute("dictionary", dictionaryEntity);
        return "system/dic_edit";
    }
	
	
	@RequestMapping("/dic/editSave")
	@ResponseBody
    public void editSave(Model model, Dictionary dictionary) {
		Dictionary dictionaryEntity = dicService.getDictionary(dictionary);
		if(dictionaryEntity==null) {
			dictionaryEntity = new Dictionary();
		}
		dicService.saveDictionary(dictionaryEntity);
    }
	

	private Map<String,Object> initParams(Map<String,Object> params){
		System.out.println("s-->"+params.get("tCode"));
//		Map<String,Object> params = new HashMap<String,Object>();
//		params.put("tCode", req.getParameter("tCode"));
//		params.put("tText", req.getParameter("tText"));
//		System.out.println("tCode-->"+req.getParameter("tCode"));
		return params;
	}
}
