package vn.lgsp.fw.app.cmon.domain.service.ethnicity;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import vn.lgsp.fw.app.cmon.domain.entity.CmonEthnicity;
import vn.lgsp.fw.app.cmon.domain.entity.QCmonEthnicity;
import vn.lgsp.fw.app.cmon.domain.repository.ethnicity.CmonEthnicityRepository;
import vn.lgsp.fw.app.cmon.domain.repository.ethnicity.CmonEthnicitySearchCriteria;

@Service
public class CmonEthnicityServiceImpl implements CmonEthnicityService {

	private static final QCmonEthnicity CMON_ETHNICITY = QCmonEthnicity.cmonEthnicity;

	BooleanExpression base = CMON_ETHNICITY.deleted.isFalse();

	@Autowired
	CmonEthnicityRepository cmonEthnicityRepository;

	@Override
	public List<CmonEthnicity> getAll() {
		return cmonEthnicityRepository.findAllListResult(base, null, new OrderSpecifier<>(Order.DESC,
				Expressions.dateTimePath(LocalDateTime.class, CMON_ETHNICITY, "createdAt")));
	}

	@Override
	public CmonEthnicity getOne(Long id) {
		return cmonEthnicityRepository.findOne(predicateFindOne(id));
	}

	@Override
	public CmonEthnicity save(CmonEthnicity ethnicity) {
		return cmonEthnicityRepository.save(ethnicity);
	}

	@Override
	public CmonEthnicity update(Long id, CmonEthnicity ethnicity) {
		boolean exist = cmonEthnicityRepository.exists(predicateFindOne(id));
		if (exist) {
			return cmonEthnicityRepository.save(ethnicity);
		} else {
			return null;
		}
	}

	@Override
	public void delete(Long id) {
		cmonEthnicityRepository.delete(id);
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
