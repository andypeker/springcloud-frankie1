package cfgsvr.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Frankie Yang on 2019-07-10.
 */
@RestController
public class HelloService {
    private final Logger logger = Logger.getLogger(getClass());

//    @Autowired
//    private DiscoveryClient client;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index() {
        return "Hello World - config-server";
    }

}
