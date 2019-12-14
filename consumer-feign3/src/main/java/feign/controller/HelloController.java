package feign.controller;

import feign.service.RemoteServiceMultiple;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * @author Frankie Yang on 2019-07-17.
 */
@RestController
//@RequestMapping("/xx")
public class HelloController {
    private final Logger logger = Logger.getLogger(getClass());

    /**
     * 下面 两个 注解 SuppressWarnings 和 Qualifier， 以及 注解 Primary 都是为了解决 Autowired 多个实现 的问题
     * */
//    @SuppressWarnings(value = "unchecked")
    @Qualifier(value = "remoteServiceMultiple")
    @Autowired
    private RemoteServiceMultiple remoteServiceMultiple;

    @RequestMapping("/info")
    public String info() {
        return "xx info";
    }

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
        logger.warn("WebService multiplz parallelling is called, and a = " + a + ", b = " + b + ", c = " + c);
        return remoteServiceMultiple.multiplz(a, b, c);
    }

    @RequestMapping("/multiplz/{i}/{j}/{a}/{b}/{c}")
    public Object multiplzForFeignClient(@PathVariable int i, @PathVariable int j, @PathVariable int a, @PathVariable int b, @PathVariable int c) {
        logger.warn("WebService multiplz w5 parallelling is called, and a = " + a + ", b = " + b + ", c = " + c);
        return remoteServiceMultiple.multiplz(i, j, a, b, c);
    }


/*
    TODO    继续研究 Feign 写法 的并发数量触发 fallback 逻辑？

    @GetMapping("/frank2/{a}/{b}")
    @HystrixCommand(fallbackMethod = "frankFallback",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE"),
                    @HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests", value = "9")
            })
    public Object frank(@PathVariable("a") int a, @PathVariable("b") int b) {
        try {
            return remoteServiceMultiple.frank(a, b);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String frankFallback(int x, int y, Throwable te) {
        logger.warn("[fallbackMethod] frankFallback [feign3] is called !");
        return null;
    }
*/


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
