package feign.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Frankie Yang on 2019-07-10.
 */
@RestController
public class FeignController {
    private final Logger logger = Logger.getLogger(getClass());

    @RequestMapping("/consumer")
    public String hello() {
        return "hello --- Consumer-Feign3";
    }

}
