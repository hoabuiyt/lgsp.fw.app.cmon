package vn.lgsp.fw.app.cmon.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.lgsp.fw.app.cmon.domain.entity.BaseEntity;

@Service
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
	public T save(T ethnicity) {
		return null;
	}

	@Override
	public T update(Long id, T ethnicity) {
		return null;
	}

	@Override
	public void delete(Long id) {
	}

}
