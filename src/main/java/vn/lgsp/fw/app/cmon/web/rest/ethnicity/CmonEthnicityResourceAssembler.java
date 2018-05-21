package vn.lgsp.fw.app.cmon.web.rest.ethnicity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import vn.lgsp.fw.app.cmon.domain.entity.CmonEthnicity;

@Component
public class CmonEthnicityResourceAssembler extends ResourceAssemblerSupport<CmonEthnicity, CmonEthnicityResource>{

	@Autowired
    protected EntityLinks entityLinks;
	
	//TODO add REL ENUM
	//private static final String UPDATE_REL = "update";
    //private static final String DELETE_REL = "delete";
    
	public CmonEthnicityResourceAssembler() {
		super(CmonEthnicityRestController.class, CmonEthnicityResource.class);
	}

	@Override
	public CmonEthnicityResource toResource(CmonEthnicity entity) {
		CmonEthnicityResource resource = new CmonEthnicityResource(entity);
		final Link selfLink = entityLinks.linkToSingleResource(entity);
        resource.add(selfLink.withSelfRel());
        //resource.add(selfLink.withRel(UPDATE_REL));
        //resource.add(selfLink.withRel(DELETE_REL));
		return resource;
	}

}
