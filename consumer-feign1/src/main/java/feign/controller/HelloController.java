package feign.controller;

import feign.service.RemoteServiceMulti1;
import feign.service.RestServiceMulti2;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Frankie Yang on 2019-07-17.
 */
@RestController
public class HelloController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private RestServiceMulti2 restServiceMulti2;

    @Autowired
    private RemoteServiceMulti1 remoteServiceMulti1;

    @RequestMapping("/x")
    public Object multiplyForRestTemplate(@RequestParam int a, @RequestParam int b) {
        logger.warn("WebService x is called, and a = " + a + ", b = " + b);
        return restServiceMulti2.x(a, b);
    }

    @RequestMapping("/multiply/{a}/{b}")
    public Object multiplyForFeignClient(@PathVariable int a, @PathVariable int b) {
        logger.warn("WebService multiply is called, and a = " + a + ", b = " + b);
        return remoteServiceMulti1.multiply(a, b);
    }

    @RequestMapping("/multiply/{a}/{b}/{c}")
    public Object multiplyForFeignClient(@PathVariable int a, @PathVariable int b, @PathVariable int c) {
        logger.warn("WebService multiply is called, and a = " + a + ", b = " + b + ", c = " + c);
        return remoteServiceMulti1.multiply(a, b, c);
    }

    @RequestMapping("/x2/{a}/{b}")
    public Object multiplyForFeignClientx(@PathVariable int a, @PathVariable int b) {
        logger.warn("WebService x2 is called, and a = " + a + ", b = " + b);
        return remoteServiceMulti1.x2(a, b);
    }

}
