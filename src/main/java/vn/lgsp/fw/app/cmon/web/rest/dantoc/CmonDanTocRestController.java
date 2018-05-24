package vn.lgsp.fw.app.cmon.web.rest.dantoc;

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
import vn.lgsp.fw.app.cmon.domain.service.dantoc.CmonDanTocService;
import vn.lgsp.fw.app.cmon.web.rest.BaseRestController;
import vn.lgsp.fw.app.cmon.web.rest.exception.EntityNotFoundException;
import vn.lgsp.fw.app.cmon.web.rest.exception.UpdateEntityMismatchException;

@RestController
@ExposesResourceFor(CmonDanToc.class)
@RequestMapping(path = "/rest/cmonDanTocs", produces = MediaType.APPLICATION_JSON_VALUE)
public class CmonDanTocRestController extends BaseRestController {

	@Autowired
	CmonDanTocService service;

	@Autowired
	CmonDanTocResourceAssembler assembler;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CmonDanTocResource>> loadAll() {
		List<CmonDanToc> list = service.getAll();
		if(list.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(assembler.toResources(list));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<CmonDanTocResource> getOne(@PathVariable(name = "id", required=true) Long id) throws EntityNotFoundException {
		return ResponseEntity.ok(assembler.toResource(service.getOne(id)));
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<CmonDanTocResource> createOne(@RequestBody CmonDanToc data) {
		CmonDanToc entity = service.save(data);
		return new ResponseEntity<CmonDanTocResource>(assembler.toResource(entity), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<CmonDanTocResource> updateOne(@PathVariable(name = "id", required=true) Long id,
			@RequestBody CmonDanToc data) throws EntityNotFoundException, UpdateEntityMismatchException {
		CmonDanToc entity = service.update(id, data);
		return new ResponseEntity<CmonDanTocResource>(assembler.toResource(entity), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<Object> deleteOne(@PathVariable(name = "id", required=true) Long id) throws EntityNotFoundException {
		service.delete(id);
		return ResponseEntity.ok().build();
	}
}
