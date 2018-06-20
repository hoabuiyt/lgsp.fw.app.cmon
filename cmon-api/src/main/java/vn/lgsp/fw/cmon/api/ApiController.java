package vn.lgsp.fw.cmon.api;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/rest/test", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiController {

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> test() {
		return ResponseEntity.ok().body(new String("test"));
	}
}
