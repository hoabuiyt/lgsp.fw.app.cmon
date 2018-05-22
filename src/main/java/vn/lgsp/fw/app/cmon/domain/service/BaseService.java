package vn.lgsp.fw.app.cmon.domain.service;

import java.util.List;

import vn.lgsp.fw.app.cmon.domain.entity.BaseEntity;

public interface BaseService<T extends BaseEntity<T>> {

	List<T> getAll();
	
	T getOne(Long id);

	T save(T ethnicity);

	T update(Long id, T ethnicity);

	void delete(Long id);
}
