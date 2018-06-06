package vn.lgsp.fw.app.cmon.web.rest.dantoc;

import org.springframework.hateoas.core.Relation;

import vn.lgsp.fw.app.cmon.domain.entity.CmonDanToc;
import vn.lgsp.fw.app.cmon.web.rest.BaseResource;

@Relation(collectionRelation = "danTocs")
public class CmonDanTocResource extends BaseResource<CmonDanToc>{

	public CmonDanTocResource(CmonDanToc entity) {
		super(entity);
	}
	
}
