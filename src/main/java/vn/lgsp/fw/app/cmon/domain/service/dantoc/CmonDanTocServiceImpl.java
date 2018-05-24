package vn.lgsp.fw.app.cmon.domain.service.dantoc;

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

import vn.lgsp.fw.app.cmon.domain.entity.CmonDanToc;
import vn.lgsp.fw.app.cmon.domain.entity.QCmonDanToc;
import vn.lgsp.fw.app.cmon.domain.repository.dantoc.CmonDanTocRepository;
import vn.lgsp.fw.app.cmon.domain.repository.dantoc.CmonDanTocSearchCriteria;
import vn.lgsp.fw.app.cmon.web.rest.exception.EntityNotFoundException;
import vn.lgsp.fw.app.cmon.web.rest.exception.UpdateEntityMismatchException;

@Service
@Transactional
public class CmonDanTocServiceImpl implements CmonDanTocService {

	private static final QCmonDanToc CMON_DANTOC = QCmonDanToc.cmonDanToc;

	BooleanExpression base = CMON_DANTOC.deleted.isFalse();

	@Autowired
	CmonDanTocRepository repository;

	@Override
	public List<CmonDanToc> getAll(){
		List<CmonDanToc> list = repository.findAllListResult(base, null, new OrderSpecifier<>(
				Order.DESC, Expressions.dateTimePath(LocalDateTime.class, CMON_DANTOC, "ngaySua")));
		return list;
	}

	@Override
	public CmonDanToc getOne(Long id) throws EntityNotFoundException {
		CmonDanToc entity = repository.findById(id);
		if (entity == null) {
			throw new EntityNotFoundException(CmonDanToc.class, "id", id.toString());
		}
		return entity;
	}

	@Override
	public CmonDanToc save(CmonDanToc danToc) {
		return repository.save(danToc);
	}

	@Override
	public CmonDanToc update(Long id, CmonDanToc danToc) throws EntityNotFoundException, UpdateEntityMismatchException {
		if(id.equals(danToc.getId())) {
			boolean exist = repository.exists(id);
			if (exist) {
				return repository.save(danToc);
			} else {
				throw new EntityNotFoundException(CmonDanToc.class, "id", id.toString());
			}
		}
		throw new UpdateEntityMismatchException(CmonDanToc.class, id, danToc.getId());
	}

	@Override
	public void delete(Long id) throws EntityNotFoundException {
		boolean exist = repository.exists(id);
		if (exist) {
			repository.delete(id);
		} else {
			throw new EntityNotFoundException(CmonDanToc.class, "id", id.toString());
		}
	}

	public Page<CmonDanToc> findAllWithPaging(CmonDanTocSearchCriteria search, Pageable pageable) {
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
