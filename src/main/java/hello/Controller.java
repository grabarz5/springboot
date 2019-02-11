package hello;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@RestController
public class Controller {

    private Database db = new Database();

    @RequestMapping("/")
    public String index() {
        return "Hello!";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public List add(String name, String ip, String mac) {
        return Arrays.asList("result",db.set(name, ip, mac));
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List list() {
        return db.get();
    }
}