package vn.lgsp.fw.app.cmon.domain.service.donvihanhchinh;

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

import vn.lgsp.fw.app.cmon.domain.entity.CmonDonViHanhChinh;
import vn.lgsp.fw.app.cmon.domain.entity.QCmonDonViHanhChinh;
import vn.lgsp.fw.app.cmon.domain.repository.BaseSearchCriteria;
import vn.lgsp.fw.app.cmon.domain.repository.donvihanhchinh.CmonDonViHanhChinhRepository;
import vn.lgsp.fw.app.cmon.web.rest.exception.EntityNotFoundException;
import vn.lgsp.fw.app.cmon.web.rest.exception.UpdateEntityMismatchException;

@Service
@Transactional
public class CmonDonViHanhChinhServiceImpl implements CmonDonViHanhChinhService {

	private static final QCmonDonViHanhChinh CMON_DVHC = QCmonDonViHanhChinh.cmonDonViHanhChinh;
	
	BooleanExpression base = CMON_DVHC.deleted.isFalse();

	@Autowired
	CmonDonViHanhChinhRepository repository;
	
	@Override
	public List<CmonDonViHanhChinh> getAll(){
		List<CmonDonViHanhChinh> list = repository.findAllListResult(base, null, new OrderSpecifier<>(
				Order.DESC, Expressions.dateTimePath(LocalDateTime.class, CMON_DVHC, "ngaySua")));
		return list;
	}

	@Override
	public CmonDonViHanhChinh getOne(Long id) throws EntityNotFoundException {
		CmonDonViHanhChinh entity = repository.findById(id);
		if (entity == null) {
			throw new EntityNotFoundException(CmonDonViHanhChinh.class, "id", id.toString());
		}
		return entity;
	}

	@Override
	public CmonDonViHanhChinh save(CmonDonViHanhChinh entity) {
		return repository.save(entity);
	}

	@Override
	public CmonDonViHanhChinh update(Long id, CmonDonViHanhChinh entity)
			throws EntityNotFoundException, UpdateEntityMismatchException {
		if(id.equals(entity.getId())) {
			boolean exist = repository.exists(id);
			if (exist) {
				return repository.save(entity);
			} else {
				throw new EntityNotFoundException(CmonDonViHanhChinh.class, "id", id.toString());
			}
		}
		throw new UpdateEntityMismatchException(CmonDonViHanhChinh.class, id, entity.getId());
	}

	@Override
	public void delete(Long id) throws EntityNotFoundException {
		boolean exist = repository.exists(id);
		if (exist) {
			repository.delete(id);
		} else {
			throw new EntityNotFoundException(CmonDonViHanhChinh.class, "id", id.toString());
		}
	}
	
	public Page<CmonDonViHanhChinh> findAllWithPaging(BaseSearchCriteria search, Pageable pageable) {
		return repository.findAll(predicateFindAll(search), pageable);
	}
	
	private Predicate predicateFindAll(BaseSearchCriteria search) {
		BooleanExpression predicate = base;
		if (search != null) {
			if (search.getTen() != null && StringUtils.isNoneBlank(search.getTen().trim())) {
				predicate = predicate.and(CMON_DVHC.ten.contains(search.getTen()));
			}
			if (search.getMa() != null && StringUtils.isNoneBlank(search.getMa().trim())) {
				predicate = predicate.and(CMON_DVHC.ma.contains(search.getMa()));
			}

		}
		return predicate;
	}

	@Override
	public List<CmonDonViHanhChinh> findAllDonViHanhChinhChildren(Long id) {
		return repository.findAllDonViHanhChinhChildren(id);
	}

}
