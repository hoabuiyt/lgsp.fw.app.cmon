package vn.lgsp.fw.app.cmon.domain.service.ethnicity;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.lgsp.fw.app.cmon.domain.entity.BaseEntity;
import vn.lgsp.fw.app.cmon.domain.entity.CmonEthnicity;
import vn.lgsp.fw.app.cmon.domain.service.BaseService;

public interface CmonEthnicityService<T extends BaseEntity<T>> extends BaseService<T>{

	List<CmonEthnicity> load();
	
	CmonEthnicity findOne(Long id);
	
}
