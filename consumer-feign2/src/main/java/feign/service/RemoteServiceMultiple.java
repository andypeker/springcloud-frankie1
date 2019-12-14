package feign.service;

import feign.component.RemoteServiceMultipleFallbackImpl;
import feign.config.Configuration1;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Frankie Yang on 2019-07-17.
 */
@FeignClient(/*name = "springcloud-provider-feign-multiple",*/
        name = "${springappname.provider.multiple}",
        fallback = RemoteServiceMultipleFallbackImpl.class,
        configuration = Configuration1.class)
public interface RemoteServiceMultiple {

    @RequestMapping("/frank")
    Object frank(@RequestParam("a") int a, @RequestParam("b") int b);

    @RequestMapping("/x")
    Object x(@RequestParam("a") int a, @RequestParam("b") int b);

    @RequestMapping("/x2")
    Object x2(@RequestParam("a") int a, @RequestParam("b") int b);

    @RequestMapping("/multiply/{a}/{b}")
    Object multiply(@PathVariable("a") int a, @PathVariable("b") int b);

    @RequestMapping("/multiply/{a}/{b}/{c}")
    Object multiply(@PathVariable("a") int a, @PathVariable("b") int b, @PathVariable("c") int c);

    @RequestMapping("/multiplz/{a}/{b}/{c}")
    Object multiplz(@PathVariable("a") int a, @PathVariable("b") int b, @PathVariable("c") int c);


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
