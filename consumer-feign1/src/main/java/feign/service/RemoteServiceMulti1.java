package feign.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Frankie Yang on 2019-07-17.
 */
@FeignClient(name = "${springappname.provider.multiple}",
        fallback = HelloRemoteServiceFallbackImpl.class)
public interface RemoteServiceMulti1 {

    @RequestMapping("/x")
    Object x(@RequestParam("a") int a, @RequestParam("b") int b);

    @RequestMapping("/x2/{a}/{b}")
    Object x2(@PathVariable("a") int a, @PathVariable("b") int b);

    @RequestMapping("/multiply/{a}/{b}")
    Object multiply(@PathVariable("a") int a, @PathVariable("b") int b);

    @RequestMapping("/multiply/{a}/{b}/{c}")
    Object multiply(@PathVariable("a") int a, @PathVariable("b") int b, @PathVariable("c") int c);

}

/**
 * 这个接口 的结构，具体是 方法列表；
 * 应该与 FeignClient 的参数 表示的[远端机器上的]服务的 "Controller" 保持一致!
 * <p>
 * 这个例子中，HelloService 与 HelloRemoteService 形成对照，一个发起 restTemplate，一个"Feign简洁"实现
 */