package vn.lgsp.fw.app.cmon.web.rest.donvihanhchinh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import vn.lgsp.fw.app.cmon.domain.entity.CmonDonViHanhChinh;
import vn.lgsp.fw.app.cmon.web.rest.exception.EntityNotFoundException;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class CmonDonViHanhChinhResourceAssembler extends ResourceAssemblerSupport<CmonDonViHanhChinh, CmonDonViHanhChinhResource>{

	@Autowired
    protected EntityLinks entityLinks;
	
	//TODO add REL ENUM
	//private static final String UPDATE_REL = "update";
    //private static final String DELETE_REL = "delete";
    
	public CmonDonViHanhChinhResourceAssembler() {
		super(CmonDonViHanhChinhRestController.class, CmonDonViHanhChinhResource.class);
	}

	@Override
	public CmonDonViHanhChinhResource toResource(CmonDonViHanhChinh entity) {
		CmonDonViHanhChinhResource resource = new CmonDonViHanhChinhResource(entity);
		final Link selfLink = entityLinks.linkToSingleResource(entity);
        resource.add(selfLink.withSelfRel());
        try {
        	if(entity.getCha()!=null) {
        		resource.add(linkTo(methodOn(CmonDonViHanhChinhRestController.class).getOne(entity.getCha().getId())).withRel("cha"));
        	}
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
        resource.add(linkTo(methodOn(CmonDonViHanhChinhRestController.class).loadChildren(entity.getId())).withRel("children"));
        //resource.add(selfLink.withRel(UPDATE_REL));
        //resource.add(selfLink.withRel(DELETE_REL));
		return resource;
	}

}
