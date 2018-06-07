package vn.lgsp.fw.app.cmon.domain.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.lgsp.fw.app.cmon.domain.entity.BaseEntity;
import vn.lgsp.fw.app.cmon.web.rest.exception.EntityNotFoundException;
import vn.lgsp.fw.app.cmon.web.rest.exception.UpdateEntityMismatchException;

public interface BaseService<T extends BaseEntity<T>> {

	List<T> getAll();
	
	List<T> getAll(Pageable pageable);
	
	Page<T> findAllWithPaging(Pageable pageable);
	
	T getOne(Long id) throws EntityNotFoundException;

	T save(T ethnicity);

	T update(Long id, T ethnicity) throws EntityNotFoundException, UpdateEntityMismatchException;

	void delete(Long id) throws EntityNotFoundException;

}