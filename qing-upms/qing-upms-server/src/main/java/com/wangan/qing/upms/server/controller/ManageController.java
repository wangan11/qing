package com.wangan.qing.upms.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 后台controller
 * Created by ZhangShuzheng on 2017/01/19.
 */
@Controller
@RequestMapping("/manage")
public class ManageController  {

	private static final Logger LOGGER = LoggerFactory.getLogger(ManageController.class);



	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(ModelMap modelMap) {

		return "/manage/index.jsp";
	}

}