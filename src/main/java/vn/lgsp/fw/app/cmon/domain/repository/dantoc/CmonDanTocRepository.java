package vn.lgsp.fw.app.cmon.domain.repository.dantoc;


import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import vn.lgsp.fw.app.cmon.domain.entity.CmonDanToc;
import vn.lgsp.fw.app.cmon.domain.repository.BaseRepository;

@RepositoryRestResource
public interface CmonDanTocRepository extends BaseRepository<CmonDanToc, Long>, CmonDanTocCustomRepository{

}
