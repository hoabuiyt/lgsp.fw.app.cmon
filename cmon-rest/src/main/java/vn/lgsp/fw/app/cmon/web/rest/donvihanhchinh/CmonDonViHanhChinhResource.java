package vn.lgsp.fw.app.cmon.web.rest.donvihanhchinh;

import org.springframework.hateoas.core.Relation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import vn.lgsp.fw.app.cmon.domain.entity.CmonDonViHanhChinh;
import vn.lgsp.fw.app.cmon.web.rest.BaseResource;

@JsonPropertyOrder({ "id", "cha"})
@Relation(collectionRelation = "donViHanhChinhs")
public class CmonDonViHanhChinhResource extends BaseResource<CmonDonViHanhChinh>{
	
	public CmonDonViHanhChinhResource(CmonDonViHanhChinh entity) {
		super(entity);
	}
	
	@JsonProperty("cha")
    public CmonDonViHanhChinh getCha() {
		if(entity.getCha()!=null) {
			return entity.getCha();
		}
		return null;
    }

}
