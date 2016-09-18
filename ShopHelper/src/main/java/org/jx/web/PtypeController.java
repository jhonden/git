package org.jx.web;

import org.jx.repository.bean.Page;
import org.jx.repository.bean.Ptype;
import org.jx.service.PtypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@RequestMapping("/ptype")
@Controller
public class PtypeController {
	@Autowired
	PtypeService ptypeService;
	
	@RequestMapping("/index")
	public String home(Model model){
		model.addAttribute("ptypes", ptypeService.findAll());
		return "buy/ptype/index";
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Object add(@RequestBody Ptype ptype){
		return new JsonResult(ptypeService.add(ptype), "");
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(Ptype ptype){
		return new JsonResult("" + (ptypeService.delete(ptype) > 0 ? "0" : "1"), "");
	}
	
	@RequestMapping("/load/{id}")
	public Object load(@PathVariable int id, Model model){
		model.addAttribute("ptype",ptypeService.load(new Ptype(id)));
		return "buy/ptype/update";
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Object update(@RequestBody Ptype ptype){
		return new JsonResult("" + (ptypeService.update(ptype) > 0 ? "0" : "1"), "");
	}
	
	@RequestMapping("/pages")
	@ResponseBody
	public Page findPages(Page page){
		ptypeService.findPages(page);
		return page;
	}
	
}
