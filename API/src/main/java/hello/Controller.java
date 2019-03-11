package hello;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@CrossOrigin("http://127.0.0.1:5500")
@RestController
public class Controller {

	private Database db = new Database();

	@RequestMapping("/")
	public String index() {
		return "Hello!";
	}

	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Map<String, Boolean> add(@RequestBody Computer pc){
		System.out.println(pc.getName());
		return Collections.singletonMap("return",db.set(pc.getName(),pc.getIp(),pc.getMac()));
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public List list() {
		return db.get();
	}

	@RequestMapping(value = "/clean", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Boolean> clean() {
		return new ResponseEntity<Boolean>(db.clean(), HttpStatus.OK);
	}
}