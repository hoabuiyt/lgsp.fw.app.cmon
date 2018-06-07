package vn.lgsp.fw.app.cmon.web.rest.dantoc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import vn.lgsp.fw.app.cmon.domain.entity.CmonDanToc;

@Component
public class CmonDanTocResourceAssembler extends ResourceAssemblerSupport<CmonDanToc, CmonDanTocResource>{

	@Autowired
    protected EntityLinks entityLinks;
	
	public CmonDanTocResourceAssembler() {
		super(CmonDanTocRestController.class, CmonDanTocResource.class);
	}

	@Override
	public CmonDanTocResource toResource(CmonDanToc entity) {
		CmonDanTocResource resource = new CmonDanTocResource(entity);
		final Link selfLink = entityLinks.linkToSingleResource(entity);
        resource.add(selfLink.withSelfRel());
		return resource;
	}

}
