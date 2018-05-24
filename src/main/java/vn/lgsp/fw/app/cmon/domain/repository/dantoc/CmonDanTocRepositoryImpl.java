package vn.lgsp.fw.app.cmon.domain.repository.dantoc;

import vn.lgsp.fw.app.cmon.domain.entity.CmonDanToc;

public class CmonDanTocRepositoryImpl implements CustomCmonDanTocRepository<CmonDanToc> {

	@Override
	public void customMethod() {
		System.out.println("Call from custom repository");
	}


}
