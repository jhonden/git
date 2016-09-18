package org.jx.web;

import java.util.HashMap;
import java.util.Map;

import org.jx.repository.bean.Account;
import org.jx.repository.bean.Page;
import org.jx.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@RequestMapping("/account")
@Controller
public class AccountController {
	@Autowired
	AccountService accountService;
	
	@RequestMapping("/log")
	public String home(Model model){
		return "account/accountFlow";
	}
	
	@RequestMapping("/load")
	@ResponseBody
	public Object load(){
		Map<String,Account> data = new HashMap<String,Account>();
		data.put("account", accountService.load());
		data.put("todayInAccount", accountService.todayInAccount());
		data.put("todayOutAccount", accountService.todayOutAccount());
		return new JsonResult("0", data);
	}
	
	@RequestMapping("/pages")
	@ResponseBody
	public Page findPages(Page page){
		accountService.findPages(page);
		return page;
	}
	
}
