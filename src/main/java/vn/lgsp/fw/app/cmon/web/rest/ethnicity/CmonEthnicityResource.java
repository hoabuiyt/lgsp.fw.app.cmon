package vn.lgsp.fw.app.cmon.web.rest.ethnicity;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import vn.lgsp.fw.app.cmon.domain.entity.CmonEthnicity;

public class CmonEthnicityResource extends ResourceSupport{
	
	@JsonUnwrapped
	private final CmonEthnicity ethnicity;
	
	public CmonEthnicityResource(CmonEthnicity ethnicity) {
		this.ethnicity = ethnicity;
	}

}
