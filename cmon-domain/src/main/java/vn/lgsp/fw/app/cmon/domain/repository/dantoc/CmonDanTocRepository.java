package vn.lgsp.fw.app.cmon.domain.repository.dantoc;


import org.springframework.data.jpa.repository.JpaRepository;

import vn.lgsp.fw.app.cmon.domain.entity.CmonDanToc;
import vn.lgsp.fw.core.BaseRepository;

public interface CmonDanTocRepository extends BaseRepository<CmonDanToc, Long> , CustomCmonDanTocRepository<CmonDanToc>{

}
