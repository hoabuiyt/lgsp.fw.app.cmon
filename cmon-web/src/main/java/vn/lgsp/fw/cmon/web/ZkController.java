package vn.lgsp.fw.cmon.web;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Transient;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zkoss.zk.ui.Executions;


@Controller
public class ZkController {

	
	@RequestMapping(value = "/login")
	public String login() {
		return "forward:/WEB-INF/zul/login.zul";
	}
	
	@RequestMapping(value = "/don-vi-hanh-chinh")
	public String qlDonviHanhChinh() {
		return "forward:/WEB-INF/zul/index.zul?file=/WEB-INF/zul/donvihanhchinh/list.zul";
	}
	
	
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
