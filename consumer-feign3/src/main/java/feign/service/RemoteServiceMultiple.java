package feign.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import feign.component.RemoteServiceMultipleFallbackImpl;
import feign.config.Configuration2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.*;

/**
 * @author Frankie Yang on 2019-07-17.
 */
@FeignClient(name = "${springappname.provider.multiple}",
        fallback = RemoteServiceMultipleFallbackImpl.class,
        configuration = Configuration2.class/*,
        path = "/z"*/)
@Primary
@Qualifier(value = "remoteServiceMultiple")
public interface RemoteServiceMultiple {

    /**
     * TODO 估计，这个用法才是正确的 Feign 的用法，没有 Controller，这个 接口 直接处理 请求 Mapping。 --- 错误！
     * */

    @RequestMapping(method = RequestMethod.GET, value = "/frank")
    Object frank(@RequestParam(value = "a") int a, @RequestParam(value = "b") int b);

    /*@GetMapping(value = "/frank/{a}/{b}")
    Object frank(@PathVariable("a") int a, @PathVariable("b") int b);*/

    @RequestMapping(value = "x", method = RequestMethod.GET)
    Object x(@RequestParam("a") int a, @RequestParam("b") int b);

    @RequestMapping(value = "x2", method = RequestMethod.GET)
    Object x2(@RequestParam(value = "a") int a, @RequestParam(value = "b") int b);

    @RequestMapping(value = "multiply/{a}/{b}", method = RequestMethod.GET)
    Object multiply(@PathVariable("a") int a, @PathVariable("b") int b);

    @RequestMapping(value = "multiply/{a}/{b}/{c}", method = RequestMethod.GET)
    Object multiply(@PathVariable("a") int a, @PathVariable("b") int b, @PathVariable("c") int c);

    //  TODO 注解无效
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE"),
            @HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests", value = "5")
    })
    @RequestMapping(value = "multiplz/{a}/{b}/{c}", method = RequestMethod.GET)
    Object multiplz(@PathVariable("a") int a, @PathVariable("b") int b, @PathVariable("c") int c);

    @RequestMapping(value = "multiplz/{i}/{j}/{a}/{b}/{c}", method = RequestMethod.GET)
    Object multiplz(@PathVariable("i") int i, @PathVariable("j") int j, @PathVariable("a") int a, @PathVariable("b") int b, @PathVariable("c") int c);

    // TODO     https://www.cnblogs.com/zuowj/p/10418932.html


    @RequestMapping(value = "/stepribbon2/consumer", method = RequestMethod.GET)
    Object stepribbon2step1();

    @RequestMapping(value = "/stepribbon2/hola-info", method = RequestMethod.GET)
    Object stepribbon2step2();

    @RequestMapping(value = "/stepribbon2/hola-hello", method = RequestMethod.GET)
    Object stepribbon2step3();

    @RequestMapping(value = "/stepribbon2/frankie-dream", method = RequestMethod.GET)
    Object stepribbon2step4();

    @RequestMapping(value = "/stepribbon2/frankie-money", method = RequestMethod.GET)
    Object stepribbon2step5();


}
