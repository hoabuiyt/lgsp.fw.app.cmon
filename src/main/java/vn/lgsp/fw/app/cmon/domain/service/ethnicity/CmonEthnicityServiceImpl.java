package vn.lgsp.fw.app.cmon.domain.service.ethnicity;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import vn.lgsp.fw.app.cmon.domain.entity.CmonEthnicity;
import vn.lgsp.fw.app.cmon.domain.entity.QCmonEthnicity;
import vn.lgsp.fw.app.cmon.domain.repository.ethnicity.CmonEthnicityRepository;
import vn.lgsp.fw.app.cmon.domain.repository.ethnicity.CmonEthnicitySearchCriteria;
import vn.lgsp.fw.app.cmon.web.rest.exception.EntityNotFoundException;
import vn.lgsp.fw.app.cmon.web.rest.exception.UpdateEntityMismatchException;

@Service
@Transactional
public class CmonEthnicityServiceImpl implements CmonEthnicityService {

	private static final QCmonEthnicity CMON_ETHNICITY = QCmonEthnicity.cmonEthnicity;

	BooleanExpression base = CMON_ETHNICITY.deleted.isFalse();

	@Autowired
	CmonEthnicityRepository cmonEthnicityRepository;

	@Override
	public List<CmonEthnicity> getAll() throws EntityNotFoundException {
		List<CmonEthnicity> list = cmonEthnicityRepository.findAllListResult(base, null, new OrderSpecifier<>(
				Order.DESC, Expressions.dateTimePath(LocalDateTime.class, CMON_ETHNICITY, "createdAt")));
		if (list == null || list.isEmpty()) {
			throw new EntityNotFoundException(CmonEthnicity.class);
		}
		return list;
	}

	@Override
	public CmonEthnicity getOne(Long id) throws EntityNotFoundException {
		CmonEthnicity entity = cmonEthnicityRepository.findOne(predicateFindOne(id));
		if (entity == null) {
			throw new EntityNotFoundException(CmonEthnicity.class, "id", id.toString());
		}
		return entity;
	}

	@Override
	public CmonEthnicity save(CmonEthnicity ethnicity) {
		return cmonEthnicityRepository.save(ethnicity);
	}

	@Override
	public CmonEthnicity update(Long id, CmonEthnicity ethnicity) throws EntityNotFoundException, UpdateEntityMismatchException {
		if(id.equals(ethnicity.getId())) {
			boolean exist = cmonEthnicityRepository.exists(predicateFindOne(id));
			if (exist) {
				return cmonEthnicityRepository.save(ethnicity);
			} else {
				throw new EntityNotFoundException(CmonEthnicity.class, "id", id.toString());
			}
		}
		throw new UpdateEntityMismatchException(CmonEthnicity.class, id, ethnicity.getId());
	}

	@Override
	public void delete(Long id) throws EntityNotFoundException {
		boolean exist = cmonEthnicityRepository.exists(predicateFindOne(id));
		if (exist) {
			cmonEthnicityRepository.delete(id);
		} else {
			throw new EntityNotFoundException(CmonEthnicity.class, "id", id.toString());
		}
	}

	public Page<CmonEthnicity> findAllWithPaging(CmonEthnicitySearchCriteria search, Pageable pageable) {
		return cmonEthnicityRepository.findAll(predicateFindAll(search), pageable);
	}

	private Predicate predicateFindOne(Long id) {
		return base.and(CMON_ETHNICITY.id.eq(id));
	}

	private Predicate predicateFindAll(CmonEthnicitySearchCriteria search) {
		BooleanExpression predicate = base;
		if (search != null) {
			if (search.getName() != null && StringUtils.isNoneBlank(search.getName().trim())) {
				predicate = predicate.and(CMON_ETHNICITY.name.contains(search.getName()));
			}
			if (search.getCode() != null && StringUtils.isNoneBlank(search.getCode().trim())) {
				predicate = predicate.and(CMON_ETHNICITY.code.contains(search.getCode()));
			}

		}
		return predicate;
	}

}
