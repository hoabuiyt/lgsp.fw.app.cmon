package vn.lgsp.fw.app.cmon.domain.repository.donvihanhchinh;

import org.springframework.data.rest.core.config.Projection;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import vn.lgsp.fw.app.cmon.domain.entity.CmonDonViHanhChinh;
import vn.lgsp.fw.app.cmon.domain.enums.ECapDonViHanhChinh;

@JsonPropertyOrder({ "id" })
@Projection(name = "cmonDonViHanhChinhProjection", types = { CmonDonViHanhChinh.class }) 
interface CmonDonViHanhChinhProjection {
	
	String getId();
	String getTen();
	String getMa();
	ECapDonViHanhChinh getCap();
	@JsonUnwrapped
	CmonDonViHanhChinh getCha();

}
