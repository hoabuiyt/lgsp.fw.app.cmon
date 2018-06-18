package vn.lgsp.fw.app.cmon.domain.repository.donvihanhchinh;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.lgsp.fw.app.cmon.domain.entity.CmonDonViHanhChinh;
import vn.lgsp.fw.core.BaseRepository;

public interface CmonDonViHanhChinhRepository extends BaseRepository<CmonDonViHanhChinh, Long>, CustomCmonDonViHanhChinhRepository<CmonDonViHanhChinh>{

	@Query("select d from CmonDonViHanhChinh d where d.cha.id=:id and d.deleted=0")
	List<CmonDonViHanhChinh> findAllDonViHanhChinhChildren(@Param("id") Long id);
}
