package org.jx.web;

import javax.servlet.http.HttpSession;

import org.jx.repository.bean.Page;
import org.jx.repository.bean.User;
import org.jx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class UserController {
	@Autowired
	UserService userService;
	
	@RequestMapping("/login")
	public String home(){
		return "login";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.removeAttribute("user");
		return "login";
	}
	
	@RequestMapping("/home")
	public String login(User user, Model model, HttpSession session){
		user = userService.login(user);
		if(user == null){
			model.addAttribute("loginError", "用户名或密码错误");
			return "login";
		}else{
			session.setAttribute("user", user);
			return "index";
		}
	}
	
	@RequestMapping("/user/pages")
	@ResponseBody
	public Page findPages(Page page){
		userService.findPages(page);
		return page;
	}
	
	
}
