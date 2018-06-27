package vn.lgsp.fw.cmon.domain.service.donvihanhchinh;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CmonDonViHanhChinhServiceImpl/* implements CmonDonViHanhChinhService*/ {

	/*private static final QCmonDonViHanhChinh CMON_DVHC = QCmonDonViHanhChinh.cmonDonViHanhChinh;
	
	BooleanExpression base = CMON_DVHC.deleted.isFalse();

	@Autowired
	CmonDonViHanhChinhRepository repository;
	
	@Override
	public Page<CmonDonViHanhChinh> findAll(Pageable pageable){
		Page<CmonDonViHanhChinh> list = repository.findAll(base, pageable, new OrderSpecifier<>(
				Order.DESC, Expressions.dateTimePath(LocalDateTime.class, CMON_DVHC, "ngaySua")));
		return list;
	}

	@Override
	public CmonDonViHanhChinh findOneById(Long id) {
		CmonDonViHanhChinh entity = repository.findOneById(id);
		return entity;
	}

	@Override
	public CmonDonViHanhChinh save(CmonDonViHanhChinh entity) {
		return repository.save(entity);
	}

	@Override
	public CmonDonViHanhChinh update(Long id, CmonDonViHanhChinh entity) {
		if(id.equals(entity.getId())) {
			boolean exist = repository.exists(id);
			if (exist) {
				return repository.save(entity);
			}
		}
		return null;
	}

	@Override
	public void delete(Long id) {
		boolean exist = repository.exists(id);
		if (exist) {
			repository.delete(id);
		} 
	}
	
	public Page<CmonDonViHanhChinh> findPageBySearchCriteria(BaseSearchCriteria search, Pageable pageable) {
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
	}*/

	

}
