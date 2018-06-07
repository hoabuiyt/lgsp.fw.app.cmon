package vn.lgsp.fw.app.cmon.web;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Transient;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zkoss.zk.ui.Executions;

@Configuration
@Controller
public class ZkController {

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
