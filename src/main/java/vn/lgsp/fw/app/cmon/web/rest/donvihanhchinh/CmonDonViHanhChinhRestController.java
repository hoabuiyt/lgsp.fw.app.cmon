package vn.lgsp.fw.app.cmon.web.rest.donvihanhchinh;

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

import vn.lgsp.fw.app.cmon.domain.entity.CmonDanToc;
import vn.lgsp.fw.app.cmon.domain.entity.CmonDonViHanhChinh;
import vn.lgsp.fw.app.cmon.domain.service.donvihanhchinh.CmonDonViHanhChinhService;
import vn.lgsp.fw.app.cmon.web.rest.BaseRestController;
import vn.lgsp.fw.app.cmon.web.rest.exception.EntityNotFoundException;
import vn.lgsp.fw.app.cmon.web.rest.exception.UpdateEntityMismatchException;

@RestController
@ExposesResourceFor(CmonDonViHanhChinh.class)
@RequestMapping(path = "/rest/cmonDonViHanhChinhs", produces = MediaType.APPLICATION_JSON_VALUE)
public class CmonDonViHanhChinhRestController extends BaseRestController {

	@Autowired
	CmonDonViHanhChinhService service;

	@Autowired
	CmonDonViHanhChinhResourceAssembler assembler;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CmonDonViHanhChinhResource>> loadAll(){
		List<CmonDonViHanhChinh> list = service.getAll();
		return ResponseEntity.ok(assembler.toResources(list));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<CmonDonViHanhChinhResource> getOne(
			@PathVariable(name = "id", required = true) Long id) throws EntityNotFoundException {
		return ResponseEntity.ok(assembler.toResource(service.getOne(id)));
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<CmonDonViHanhChinhResource> createOne(@RequestBody CmonDonViHanhChinh data) {
		CmonDonViHanhChinh entity = service.save(data);
		return new ResponseEntity<CmonDonViHanhChinhResource>(assembler.toResource(entity), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<CmonDonViHanhChinhResource> updateOne(
			@PathVariable(name = "id", required = true) Long id, @RequestBody CmonDonViHanhChinh data)
			throws EntityNotFoundException, UpdateEntityMismatchException {
		CmonDonViHanhChinh entity = service.update(id, data);
		return new ResponseEntity<CmonDonViHanhChinhResource>(assembler.toResource(entity), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<Object> deleteOne(@PathVariable(name = "id", required = true) Long id)
			throws EntityNotFoundException {
		service.delete(id);
		return ResponseEntity.ok().build();
	}
}
