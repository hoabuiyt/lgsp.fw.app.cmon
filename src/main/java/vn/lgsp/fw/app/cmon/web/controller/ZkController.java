package vn.lgsp.fw.app.cmon.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class ZkController {

	@RequestMapping(value = "/test")
	public String test() {
		return "forward:/WEB-INF/zul/test.zul";
	}
	
}