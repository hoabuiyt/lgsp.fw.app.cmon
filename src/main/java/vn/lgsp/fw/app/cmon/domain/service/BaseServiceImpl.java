package vn.lgsp.fw.app.cmon.domain.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.lgsp.fw.app.cmon.domain.entity.BaseEntity;

@Service
@Transactional
public class BaseServiceImpl<T extends BaseEntity<T>> implements BaseService<T>{

	@Override
	public List<T> findAll() {
		return null;
	}

	@Override
	public List<T> findAll(Pageable pageable) {
		return null;
	}
	
	@Override
	public Page<T> findPage(Pageable pageable) {
		return null;
	}
	
	@Override
	public T findOneById(Long id) {
		return null;
	}

	@Override
	public T save(T entity) {
		return null;
	}

	@Override
	public T update(Long id, T entity) {
		return null;
	}

	@Override
	public void delete(Long id) {
	}

}
