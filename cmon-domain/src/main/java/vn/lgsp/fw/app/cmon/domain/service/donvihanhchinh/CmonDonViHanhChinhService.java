package vn.lgsp.fw.app.cmon.domain.service.donvihanhchinh;

import java.util.List;

import org.springframework.data.repository.query.Param;

import vn.lgsp.fw.app.cmon.domain.entity.CmonDonViHanhChinh;
import vn.lgsp.fw.app.cmon.domain.service.BaseService;

public interface CmonDonViHanhChinhService extends BaseService<CmonDonViHanhChinh>{
	
	List<CmonDonViHanhChinh> findAllDonViHanhChinhChildren(@Param("id") Long id);

}
