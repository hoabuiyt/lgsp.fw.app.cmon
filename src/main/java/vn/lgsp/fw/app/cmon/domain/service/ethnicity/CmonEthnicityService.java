package vn.lgsp.fw.app.cmon.domain.service.ethnicity;

import java.util.List;

import com.querydsl.core.types.Order;

import vn.lgsp.fw.app.cmon.domain.entity.BaseEntity;
import vn.lgsp.fw.app.cmon.domain.entity.CmonEthnicity;
import vn.lgsp.fw.app.cmon.domain.service.BaseService;

public interface CmonEthnicityService<T extends BaseEntity<T>> extends BaseService<T>{
	
	CmonEthnicity findOne(Long id);

	List<CmonEthnicity> load(Order order);
	
}
