package vn.lgsp.fw.app.cmon.domain.repository.dantoc;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;

import vn.lgsp.fw.app.cmon.domain.entity.CmonDanToc;

public class CmonDanTocRepositoryImpl implements CustomCmonDanTocRepository<CmonDanToc> {

	@Override
	public void customMethod() {
		System.out.println("Call from custom repository");
	}

}
