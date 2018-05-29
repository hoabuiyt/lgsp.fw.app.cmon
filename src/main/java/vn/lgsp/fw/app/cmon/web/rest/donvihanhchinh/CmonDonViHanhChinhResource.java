package vn.lgsp.fw.app.cmon.web.rest.donvihanhchinh;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import vn.lgsp.fw.app.cmon.domain.entity.CmonDonViHanhChinh;
import vn.lgsp.fw.app.cmon.web.rest.BaseResource;

@JsonPropertyOrder({ "id","cha" })
public class CmonDonViHanhChinhResource extends BaseResource<CmonDonViHanhChinh>{
	
	@JsonProperty("cha")
    public CmonDonViHanhChinh getCha() {
		return entity.getCha();
    }
	
	public CmonDonViHanhChinhResource(CmonDonViHanhChinh entity) {
		super(entity);
	}
	
	
}
