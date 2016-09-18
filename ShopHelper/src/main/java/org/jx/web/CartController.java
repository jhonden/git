package org.jx.web;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jx.repository.bean.Product;
import org.jx.repository.bean.User;
import org.jx.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@RequestMapping("/cart")
@Controller
public class CartController {
	private static Log log = LogFactory.getLog(CartController.class);
	@Autowired
	CartService cartService;
	
	@RequestMapping("/add")
	@ResponseBody
	public Object add(@RequestBody Product product,HttpSession session){
		String status = cartService.addProduct(product,(User)session.getAttribute("user"));
		return new JsonResult(status, "");
	}
	
	@RequestMapping("/change")
	@ResponseBody
	public Object change(@RequestBody Product product,HttpSession session){
		return new JsonResult(cartService.updatePcount(product,(User)session.getAttribute("user")), "");
	}
	
	@RequestMapping("/delProduct")
	@ResponseBody
	public Object delProduct(@RequestBody Product product,HttpSession session){
		cartService.deleteByProduct(product,(User)session.getAttribute("user"));
		return new JsonResult("0", "");
	}
	
	@RequestMapping("/clearCart")
	@ResponseBody
	public Object clearCart(HttpSession session){
		cartService.deleteByUser((User)session.getAttribute("user"));
		return new JsonResult("0", "");
	}
	
	@RequestMapping("/iterms")
	@ResponseBody
	public Object findAll(HttpSession session){
		return new JsonResult("0", cartService.fingMyCart((User)session.getAttribute("user")));
	}
	
	@RequestMapping("/cash")
	@ResponseBody
	public Object cashCart(HttpSession session){
		try {
			cartService.cashMyCart((User)session.getAttribute("user"));
			return new JsonResult("0", "");
		} catch (Exception e) {
			if(log.isErrorEnabled())
				log.error("结算失败", e);
			return new JsonResult("1", "");
		}
		
	}
	
}
