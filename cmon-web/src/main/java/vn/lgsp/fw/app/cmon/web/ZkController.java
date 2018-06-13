package vn.lgsp.fw.app.cmon.web;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Transient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.zkoss.zk.ui.Executions;

@Configuration
@EnableWebMvc
@Controller
public class ZkController {

	@Bean
	public ViewResolver zulViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver("/WEB-INF/zul/",  ".zul");
		resolver.setOrder(InternalResourceViewResolver.LOWEST_PRECEDENCE);
		return resolver;
	}
	
	@GetMapping(value = "/login")
	public String login() {
		return "login-page";
	}
	
	/*@GetMapping(value = "/don-vi-hanh-chinh")
	public String qlDonviHanhChinh() {
		return "index-page?file=/WEB-INF/zul/donvihanhchinh/list.zul";
	}*/
	
	@GetMapping(value = "/don-vi-hanh-chinh")
	public String qlDonviHanhChinh(Model model) {
		model.addAttribute("file", "/WEB-INF/zul/donvihanhchinh/list.zul");
		return "index-page";
	}
	
/*
	@RequestMapping(value = "/dang-nhap")
	public String login() {
		return "forward:/WEB-INF/zul/login.zul";
	}
	
	@RequestMapping(value = "/don-vi-hanh-chinh")
	public String qlDonviHanhChinh() {
		return "forward:/WEB-INF/zul/index.zul?file=/WEB-INF/zul/donvihanhchinh/list.zul";
	}*/
	
	private transient Map<Object, Object> arg = Collections.emptyMap();
	@Transient
	public Map<Object, Object> getArg() {
		if (arg == Collections.emptyMap()) {
			arg = new HashMap<Object, Object>();
			arg.put("pagesize", Integer.valueOf(10));
			if (Executions.getCurrent() != null) {
				for (final Map.Entry<String, String[]> entry : Executions.getCurrent().getParameterMap().entrySet()) {
					arg.put(entry.getKey(), entry.getValue().length > 0 ? entry.getValue()[0] : "");
				}
			}
		}
		return arg;
	}
}
