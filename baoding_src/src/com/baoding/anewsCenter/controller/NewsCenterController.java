package com.baoding.anewsCenter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baoding.common.BaseController;


@Controller
@RequestMapping("/news_center/")
public class NewsCenterController  extends BaseController{

	public NewsCenterController() {
		VIEW_PATH = "a_news_center/";
	}
}
