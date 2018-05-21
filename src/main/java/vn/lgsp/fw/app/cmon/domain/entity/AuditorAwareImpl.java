package vn.lgsp.fw.app.cmon.domain.entity;

import org.springframework.data.domain.AuditorAware;

public class AuditorAwareImpl implements AuditorAware<String> {

	@Override
	public String getCurrentAuditor() {
		return "admin";
		//return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()
	}

}
