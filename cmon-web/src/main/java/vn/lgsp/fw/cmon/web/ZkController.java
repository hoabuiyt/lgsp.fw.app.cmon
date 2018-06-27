package vn.lgsp.fw.cmon.web;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Transient;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.pac4j.core.context.J2EContext;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.exception.HttpAction;
import org.pac4j.core.profile.CommonProfile;
import org.pac4j.core.profile.ProfileManager;
import org.pac4j.saml.profile.SAML2Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zkoss.zk.ui.Executions;


@Controller
public class ZkController {
	
	private static final String PROFILES = "profiles";
    private static final String SESSION_ID = "sessionId";
	
	@RequestMapping("/")
    public String root(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) throws HttpAction {
		System.out.println("root:"+request.getServletContext());
        return index(request, response, map);
    }

    @RequestMapping("/index")
    public String index(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) throws HttpAction {
        final WebContext context = new J2EContext(request, response);
        map.put(PROFILES, getProfiles(context));
        map.put(SESSION_ID, context.getSessionStore());
        System.out.println("index:"+request.getServletContext());
        return "index.zul";
    }
	
	@RequestMapping(value = "/login")
	public String login() {
		return "forward:/WEB-INF/zul/login.zul";
	}
	
	@RequestMapping(value = "/admin/don-vi-hanh-chinh")
	public String qlDonviHanhChinh(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {
		final WebContext context = new J2EContext(request, response);
        map.put(PROFILES, getProfiles(context));
        map.put(SESSION_ID, context.getSessionStore());
        System.out.println(context);
        System.out.println(getProfiles(context));
        System.out.println(context.getSessionStore());
        System.out.println("qlDonviHanhChinh:"+request.getServletContext());
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
	
	 private List<SAML2Profile> getProfiles(final WebContext context) {
	        final ProfileManager<SAML2Profile> manager = new ProfileManager<>(context);
	        System.out.println("getProfiles:::::::"+manager.isAuthenticated());
	        return manager.getAll(true);
	    }
	 
}
