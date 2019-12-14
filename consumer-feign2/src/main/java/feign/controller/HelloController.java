package feign.controller;

import feign.service.RemoteServiceMultiple;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Frankie Yang on 2019-07-17.
 */
@RestController
public class HelloController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private RemoteServiceMultiple remoteServiceMultiple;

    @RequestMapping("/frank")
    public Object frankForRestTemplate(@RequestParam int a, @RequestParam int b) {
        logger.warn("WebService frank is called, and a = " + a + ", b = " + b);
        return remoteServiceMultiple.frank(a, b);
    }

    @RequestMapping("/x")
    public Object multiplyForRestTemplate(@RequestParam int a, @RequestParam int b) {
        logger.warn("WebService x is called, and a = " + a + ", b = " + b);
        return remoteServiceMultiple.x(a, b);
    }

    @RequestMapping("/x2/{a}/{b}")
    public Object xForRestTemplate(@PathVariable int a, @PathVariable int b) {
        logger.warn("WebService x2 is called, and a = " + a + ", b = " + b);
        return remoteServiceMultiple.x2(a, b);
    }

    @RequestMapping("/multiply/{a}/{b}")
    public Object multiplyForFeignClient(@PathVariable int a, @PathVariable int b) {
        logger.warn("WebService multiply is called, and a = " + a + ", b = " + b);
        return remoteServiceMultiple.multiply(a, b);
    }

    @RequestMapping("/multiply/{a}/{b}/{c}")
    public Object multiplyForFeignClient(@PathVariable int a, @PathVariable int b, @PathVariable int c) {
        logger.warn("WebService multiply is called, and a = " + a + ", b = " + b + ", c = " + c);
        return remoteServiceMultiple.multiply(a, b, c);
    }

    @RequestMapping("/multiplz/{a}/{b}/{c}")
    public Object multiplzForFeignClient(@PathVariable int a, @PathVariable int b, @PathVariable int c) {
        logger.warn("WebService multiply is called, and a = " + a + ", b = " + b + ", c = " + c);
        return remoteServiceMultiple.multiplz(a, b, c);
    }



    @RequestMapping("/stepribbon2/consumer")
    public Object ribbon2Stepstep1() {
        return remoteServiceMultiple.stepribbon2step1();
    }

    @RequestMapping("/stepribbon2/hola-info")
    public Object ribbon2Stepstep2() {
        return remoteServiceMultiple.stepribbon2step2();
    }

    @RequestMapping("/stepribbon2/hola-hello")
    public Object ribbon2Stepstep3() {
        return remoteServiceMultiple.stepribbon2step3();
    }

    @RequestMapping("/stepribbon2/frankie-dream")
    public Object ribbon2Stepstep4() {
        return remoteServiceMultiple.stepribbon2step4();
    }

    @RequestMapping("/stepribbon2/frankie-money")
    public Object ribbon2Stepstep5() {
        return remoteServiceMultiple.stepribbon2step5();
    }

}
