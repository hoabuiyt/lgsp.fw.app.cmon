package vn.lgsp.fw.app.cmon.domain.repository.ethnicity;


import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import vn.lgsp.fw.app.cmon.domain.entity.CmonEthnicity;
import vn.lgsp.fw.app.cmon.domain.repository.BaseRepository;

@RepositoryRestResource
public interface CmonEthnicityRepository extends BaseRepository<CmonEthnicity, Long>{

}
