package vn.lgsp.fw.cmon.api.dantoc;

import org.springframework.data.rest.webmvc.RepositorySearchesResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/rest/test2", produces = MediaType.APPLICATION_JSON_VALUE)
public class DanTocController {

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> test() {
		return ResponseEntity.ok().body(new String("test2"));
	}
}
