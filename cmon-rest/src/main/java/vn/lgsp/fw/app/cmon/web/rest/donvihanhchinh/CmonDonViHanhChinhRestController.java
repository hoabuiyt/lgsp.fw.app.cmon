package vn.lgsp.fw.app.cmon.web.rest.donvihanhchinh;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import vn.lgsp.fw.app.cmon.domain.entity.CmonDonViHanhChinh;
import vn.lgsp.fw.app.cmon.domain.exception.EntityNotFoundException;
import vn.lgsp.fw.app.cmon.domain.exception.UpdateEntityMismatchException;
import vn.lgsp.fw.app.cmon.domain.service.donvihanhchinh.CmonDonViHanhChinhService;
import vn.lgsp.fw.app.cmon.web.rest.BaseRestController;
import vn.lgsp.fw.core.BaseRepository;

@RestController
@RequestMapping(path = "/rest/cmonDonViHanhChinhs", produces = MediaType.APPLICATION_JSON_VALUE)
public class CmonDonViHanhChinhRestController extends BaseRestController<CmonDonViHanhChinh> {

	@Autowired
	CmonDonViHanhChinhService service;

	@Autowired
	CmonDonViHanhChinhResourceAssembler assembler;
	
	@Autowired
	public CmonDonViHanhChinhRestController(BaseRepository<CmonDonViHanhChinh, Long> repo) {
		super(repo);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<PagedResources<CmonDonViHanhChinhResource>> loadAll(Pageable pageable, HttpServletRequest request){
		System.out.println("request:"+request.getRemoteHost());
		Page<CmonDonViHanhChinh> list = service.findPage(pageable);
		return ResponseEntity.ok(pageAssembler.toResource(list, assembler));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<CmonDonViHanhChinhResource> getOne(@PathVariable(name = "id", required = true) Long id) throws EntityNotFoundException {
		CmonDonViHanhChinhResource resource = assembler.toResource(service.findOneById(id));
		return ResponseEntity.ok(resource);
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
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/children")
	public ResponseEntity<List<CmonDonViHanhChinhResource>> loadChildren(@PathVariable(name = "id", required = true) Long id){
		List<CmonDonViHanhChinh> children = service.findAllDonViHanhChinhChildren(id);
		return ResponseEntity.ok(assembler.toResources(children));
	}
}
