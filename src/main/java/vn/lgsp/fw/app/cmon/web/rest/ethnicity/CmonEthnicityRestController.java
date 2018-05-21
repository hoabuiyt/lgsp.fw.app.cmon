package vn.lgsp.fw.app.cmon.web.rest.ethnicity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Order;

import vn.lgsp.fw.app.cmon.domain.entity.CmonEthnicity;
import vn.lgsp.fw.app.cmon.domain.repository.ethnicity.CmonEthnicityRepository;
import vn.lgsp.fw.app.cmon.domain.service.ethnicity.CmonEthnicityService;
import vn.lgsp.fw.app.cmon.web.rest.BaseRestController;

@RestController
@RequestMapping(path="/ethnicities", produces=MediaType.APPLICATION_JSON_VALUE)
public class CmonEthnicityRestController extends BaseRestController{

	@Autowired
	CmonEthnicityService<CmonEthnicity> ethnicityService;

	@Autowired
	CmonEthnicityRepository cmonEthnicityRepository;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public List<CmonEthnicity> loadCmonEthnicity() {
		return ethnicityService.load(Order.DESC);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public CmonEthnicity getCmonEthnicity(@PathVariable(name="id") Long id) {
		return ethnicityService.findOne(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public CmonEthnicity addCmonEthnicity(@RequestBody CmonEthnicity ethnicity) {
		return cmonEthnicityRepository.save(ethnicity);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public CmonEthnicity updateCmonEthnicity(@RequestBody CmonEthnicity ethnicity) {
		return cmonEthnicityRepository.save(ethnicity);
	}
}
