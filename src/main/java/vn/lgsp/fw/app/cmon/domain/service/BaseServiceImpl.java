package vn.lgsp.fw.app.cmon.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.lgsp.fw.app.cmon.domain.entity.BaseEntity;

@Service
@Transactional
public class BaseServiceImpl<T extends BaseEntity<T>> implements BaseService<T>{

	@Override
	public List<T> getAll() {
		return null;
	}

	@Override
	public T getOne(Long id) {
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
