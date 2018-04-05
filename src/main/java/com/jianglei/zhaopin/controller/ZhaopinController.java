package com.jianglei.zhaopin.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jianglei.zhaopin.model.Job;
import com.jianglei.zhaopin.utils.ZhiLianUtils;

import net.sf.json.JSONArray;

/** 
* @author 蒋磊
* @version 创建时间：2018年4月5日 下午4:10:27 
* 
*/
@Controller
@RequestMapping("/")
public class ZhaopinController {
	
	@RequestMapping("/")
	public String hello(Model model,@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
		model.addAttribute("name", name);
		return "index";
	}
	
	//李四修改了
	@RequestMapping("/getMap")
	public String toIndex(Model model,String url){
		List<Job> list;
		JSONArray json = null;
		try {
			list = ZhiLianUtils.getJobsAndCompanys(url);
			System.out.println(list.size());
			json = JSONArray.fromObject(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("json", json);
		System.out.println(json);
		return "success";
	}
}
