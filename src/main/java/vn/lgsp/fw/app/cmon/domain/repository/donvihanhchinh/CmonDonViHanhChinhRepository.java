package vn.lgsp.fw.app.cmon.domain.repository.donvihanhchinh;


import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import vn.lgsp.fw.app.cmon.domain.entity.CmonDonViHanhChinh;
import vn.lgsp.fw.app.cmon.domain.repository.BaseRepository;

@RepositoryRestResource
public interface CmonDonViHanhChinhRepository extends BaseRepository<CmonDonViHanhChinh, Long>{

}
