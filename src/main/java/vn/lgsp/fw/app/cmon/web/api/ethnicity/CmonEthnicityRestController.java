package vn.lgsp.fw.app.cmon.web.api.ethnicity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import vn.lgsp.fw.app.cmon.domain.entity.CmonEthnicity;
import vn.lgsp.fw.app.cmon.domain.service.ethnicity.CmonEthnicityService;

@RestController
//@RequestMapping(path="/cmon/ethnicities", produces=MediaType.APPLICATION_JSON_VALUE)
public class CmonEthnicityRestController {

	@Autowired
	CmonEthnicityService<CmonEthnicity> ethnicityService;

	@ResponseBody 
	@RequestMapping(method = RequestMethod.GET, value = "/ethnicities")
	public List<CmonEthnicity> loadCmonEthnicity() {
		System.out.println("sdfsfsf");
		return ethnicityService.load();
	}
	
}
