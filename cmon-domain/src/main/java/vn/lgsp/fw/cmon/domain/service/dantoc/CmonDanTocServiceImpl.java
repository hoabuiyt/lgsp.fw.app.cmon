package vn.lgsp.fw.cmon.domain.service.dantoc;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;

import vn.lgsp.fw.cmon.domain.entity.CmonDanToc;
import vn.lgsp.fw.cmon.domain.entity.QCmonDanToc;
import vn.lgsp.fw.cmon.domain.repository.dantoc.CmonDanTocRepository;
import vn.lgsp.fw.cmon.domain.repository.dantoc.CmonDanTocSearchCriteria;

@Service
@Transactional
public class CmonDanTocServiceImpl implements CmonDanTocService {

	private static final QCmonDanToc CMON_DANTOC = QCmonDanToc.cmonDanToc;

	BooleanExpression base = CMON_DANTOC.deleted.isFalse();

	@Autowired
	CmonDanTocRepository repository;

	@Override
	public Page<CmonDanToc> findPage(Predicate predicate, Pageable pageable, OrderSpecifier<?>... orders) {
		return findPage(predicate, pageable, orders);
	}

	
	@Override
	public List<CmonDanToc> findAll(Predicate predicate, Pageable pageable, OrderSpecifier<?>... orders) {
		return findAll(predicate, pageable, orders);
	}
	
	@Override
	public CmonDanToc findOneById(Long id) {
		return findOneById(id);
	}

	@Override
	public boolean existsById(Long id) {
		return repository.existsById(id);
	}
	
	@Override
	public CmonDanToc save(CmonDanToc danToc) {
		return save(danToc);
	}

	@Override
	public CmonDanToc update(Long id, CmonDanToc danToc) {
		if(id.equals(danToc.getId())) {
			boolean exist = existsById(id);
			if (exist) {
				return save(danToc);
			}
		}
		return null;
	}

	@Override
	public void delete(Long id) {
		boolean exist = existsById(id);
		if (exist) {
			delete(id);
		}
	}

	public Page<CmonDanToc> findPageBySearchCriteria(CmonDanTocSearchCriteria search, Pageable pageable) {
		return repository.findAll(predicateFindAll(search), pageable);
	}

	private Predicate predicateFindAll(CmonDanTocSearchCriteria search) {
		BooleanExpression predicate = base;
		if (search != null) {
			if (search.getTen() != null && StringUtils.isNoneBlank(search.getTen().trim())) {
				predicate = predicate.and(CMON_DANTOC.ten.contains(search.getTen()));
			}
			if (search.getMa() != null && StringUtils.isNoneBlank(search.getMa().trim())) {
				predicate = predicate.and(CMON_DANTOC.ma.contains(search.getMa()));
			}

		}
		return predicate;
	}


	
}
