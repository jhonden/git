package org.jx.web;

import org.jx.repository.bean.Page;
import org.jx.repository.bean.Product;
import org.jx.service.ProductService;
import org.jx.service.PtypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@RequestMapping("/product")
@Controller
public class ProductController {
	@Autowired
	ProductService productService;
	@Autowired
	PtypeService ptypeService;
	
	@RequestMapping("/index")
	public String home(Model model){
		model.addAttribute("ptypes", ptypeService.findAll());
		return "buy/product/index";
	}
	
	@RequestMapping("/toAdd")
	public String toAdd(Model model){
		model.addAttribute("ptypes", ptypeService.findAll());
		return "buy/product/add";
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Object add(@RequestBody Product product){
		return new JsonResult(productService.add(product), "");
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(Product product){
		return new JsonResult("" + (productService.delete(product) > 0 ? "0" : "1"), "");
	}
	
	@RequestMapping("/load/{id}")
	public Object load(@PathVariable int id, Model model){
		model.addAttribute("ptypes", ptypeService.findAll());
		model.addAttribute("product",productService.load(new Product(id)));
		return "buy/product/update";
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Object update(@RequestBody Product product){
		return new JsonResult("" + (productService.update(product) > 0 ? "0" : "1"), "");
	}
	
	@RequestMapping("/pages")
	@ResponseBody
	public Page findPages(Page page){
		productService.findPages(page);
		return page;
	}
	
}
