package org.jx.web;

import org.jx.repository.bean.Page;
import org.jx.repository.bean.Product;
import org.jx.service.CartService;
import org.jx.service.ProductService;
import org.jx.service.PtypeService;
import org.jx.service.SellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@RequestMapping("/sell")
@Controller
public class SellController {
	@Autowired
	ProductService productService;
	@Autowired
	SellService sellService;
	@Autowired
	PtypeService ptypeService;
	@Autowired
	CartService cartService;
	
	@RequestMapping("/index")
	public String home(Model model){
		model.addAttribute("ptypes", ptypeService.findAll());
		return "sell/sell";
	}
	
	
	@RequestMapping("/addToCar")
	@ResponseBody
	public Object add(@RequestBody Product product){
		return new JsonResult(sellService.add(product), "");
	}
	
	@RequestMapping("/pages")
	@ResponseBody
	public Page findPages(Page page,Model model){
		productService.findPages(page);
		return page;
	}
	
}
