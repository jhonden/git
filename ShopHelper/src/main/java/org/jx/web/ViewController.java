package org.jx.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ViewController {
		@RequestMapping("/view/{viewid}")
		public String view(@PathVariable String viewid){
			return viewid.replaceAll("@", "/");
		}
}
