package vn.lgsp.fw.app.cmon.domain.service.ethnicity;

import java.util.List;

import com.querydsl.core.types.Order;

import vn.lgsp.fw.app.cmon.domain.entity.BaseEntity;
import vn.lgsp.fw.app.cmon.domain.entity.CmonEthnicity;
import vn.lgsp.fw.app.cmon.domain.service.BaseService;

public interface CmonEthnicityService<T extends BaseEntity<T>> extends BaseService<T>{
	
	List<CmonEthnicity> load(Order order);
	
	CmonEthnicity findOne(Long id);

	CmonEthnicity save(CmonEthnicity ethnicity);

	CmonEthnicity update(Long id, CmonEthnicity ethnicity);

	void delete(Long id);
	
}
