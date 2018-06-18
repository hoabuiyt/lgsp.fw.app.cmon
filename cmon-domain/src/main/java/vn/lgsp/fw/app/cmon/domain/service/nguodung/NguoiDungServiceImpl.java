package vn.lgsp.fw.app.cmon.domain.service.nguodung;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import vn.lgsp.fw.app.cmon.domain.entity.NguoiDung;
import vn.lgsp.fw.app.cmon.domain.entity.QNguoiDung;
import vn.lgsp.fw.app.cmon.domain.repository.nguoidung.NguoiDungRepository;

@Service
@Transactional
public class NguoiDungServiceImpl /*implements NguoiDungService*/{

	/*private static final QNguoiDung QCLASS = QNguoiDung.nguoiDung;

	private BooleanExpression base = QCLASS.deleted.isFalse();
	
	@Autowired
	private NguoiDungRepository repository;
	
	@Override
	public Page<NguoiDung> findAll(Pageable pageable) {
		Page<NguoiDung> page = repository.findAll(base, pageable, new OrderSpecifier<>(
				Order.DESC, Expressions.dateTimePath(LocalDateTime.class, QCLASS, "ngaySua")));
		return page;
	}
	
	@Override
	public NguoiDung findOneById(Long id) {
		NguoiDung entity = repository.findOneById(id);
		return entity;
	}

	@Override
	public NguoiDung save(NguoiDung entity) {
		return repository.save(entity);
	}

	@Override
	public NguoiDung update(Long id, NguoiDung entity) {
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
	}*/

	

}
