package org.jx.web;

import org.jx.repository.bean.BuyLog;
import org.jx.repository.bean.Page;
import org.jx.service.BuyLogService;
import org.jx.service.ProductService;
import org.jx.service.PtypeService;
import org.jx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/buyLog")
@Controller
public class BuyLogController {
		
	@Autowired
	ProductService productService;
	@Autowired
	PtypeService ptypeService;
	@Autowired
	BuyLogService buyLogService;
	@Autowired
	UserService userService;
	
	@RequestMapping("/index")
	public String home(Model model){
		model.addAttribute("users", userService.findAll());
		return "buy/buyLog/index";
	}
	
	@RequestMapping("/toAdd")
	public String toAdd(Model model){
		model.addAttribute("products", productService.findAll());
		model.addAttribute("users", userService.findAll());
		return "buy/buyLog/add";
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Object add(@RequestBody BuyLog buyLog){
		return new JsonResult(buyLogService.add(buyLog), "");
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(BuyLog buyLog){
		return new JsonResult("" + (buyLogService.delete(buyLog) > 0 ? "0" : "1"), "");
	}
	
	@RequestMapping("/load/{id}")
	public Object load(@PathVariable int id, Model model){
		model.addAttribute("products", productService.findAll());
		model.addAttribute("buyLog",buyLogService.load(new BuyLog(id)));
		return "buy/buyLog/update";
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Object update(@RequestBody BuyLog buyLog){
		return new JsonResult("" + (buyLogService.update(buyLog) > 0 ? "0" : "1"), "");
	}
	
	@RequestMapping("/pages")
	@ResponseBody
	public Page findPages(Page page){
		buyLogService.findPages(page);
		return page;
	}
}
