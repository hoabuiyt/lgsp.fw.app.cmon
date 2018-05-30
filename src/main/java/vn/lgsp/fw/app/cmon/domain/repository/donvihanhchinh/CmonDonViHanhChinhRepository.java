package vn.lgsp.fw.app.cmon.domain.repository.donvihanhchinh;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import vn.lgsp.fw.app.cmon.domain.entity.CmonDonViHanhChinh;
import vn.lgsp.fw.app.cmon.domain.repository.BaseRepository;

@RepositoryRestResource
public interface CmonDonViHanhChinhRepository extends BaseRepository<CmonDonViHanhChinh, Long>, CustomCmonDonViHanhChinhRepository<CmonDonViHanhChinh>{

	@Query("select d from CmonDonViHanhChinh d where d.cha.id=:id")
	List<CmonDonViHanhChinh> findAllDonViHanhChinhChildren(@Param("id") Long id);
}
