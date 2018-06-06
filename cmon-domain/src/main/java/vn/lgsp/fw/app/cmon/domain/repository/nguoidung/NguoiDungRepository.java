package vn.lgsp.fw.app.cmon.domain.repository.nguoidung;


import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import vn.lgsp.fw.app.cmon.domain.entity.NguoiDung;
import vn.lgsp.fw.app.cmon.domain.repository.BaseRepository;

@RepositoryRestResource
public interface NguoiDungRepository extends BaseRepository<NguoiDung, Long> {

}
