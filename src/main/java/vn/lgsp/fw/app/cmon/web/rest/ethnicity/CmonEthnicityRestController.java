package vn.lgsp.fw.app.cmon.web.rest.ethnicity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import vn.lgsp.fw.app.cmon.domain.entity.CmonEthnicity;
import vn.lgsp.fw.app.cmon.domain.service.ethnicity.CmonEthnicityService;
import vn.lgsp.fw.app.cmon.web.rest.BaseRestController;
import vn.lgsp.fw.app.cmon.web.rest.exception.EntityNotFoundException;
import vn.lgsp.fw.app.cmon.web.rest.exception.UpdateEntityMismatchException;

@RestController
@ExposesResourceFor(CmonEthnicity.class)
@RequestMapping(path = "/rest/cmonEthnicities", produces = MediaType.APPLICATION_JSON_VALUE)
public class CmonEthnicityRestController extends BaseRestController {

	@Autowired
	CmonEthnicityService ethnicityService;

	@Autowired
	CmonEthnicityResourceAssembler ethnicityAssembler;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CmonEthnicityResource>> loadCmonEthnicity() throws EntityNotFoundException {
		return ResponseEntity.ok(ethnicityAssembler.toResources(ethnicityService.getAll()));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<CmonEthnicityResource> getCmonEthnicity(@PathVariable(name = "id", required=true) Long id) throws EntityNotFoundException {
		return ResponseEntity.ok(ethnicityAssembler.toResource(ethnicityService.getOne(id)));
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<CmonEthnicityResource> createCmonEthnicity(@RequestBody CmonEthnicity ethnicity) {
		CmonEthnicity entity = ethnicityService.save(ethnicity);
		return new ResponseEntity<CmonEthnicityResource>(ethnicityAssembler.toResource(entity), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<CmonEthnicityResource> updateCmonEthnicity(@PathVariable(name = "id", required=true) Long id,
			@RequestBody CmonEthnicity ethnicity) throws EntityNotFoundException, UpdateEntityMismatchException {
		CmonEthnicity entity = ethnicityService.update(id, ethnicity);
		return new ResponseEntity<CmonEthnicityResource>(ethnicityAssembler.toResource(entity), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<Object> deleteCmonEthnicity(@PathVariable(name = "id", required=true) Long id) throws EntityNotFoundException {
		ethnicityService.delete(id);
		return ResponseEntity.ok().build();
	}
}
