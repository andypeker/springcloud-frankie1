package feign.service;

import feign.component.ConsumerFeignServiceFallBack;
import feign.component.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Frankie Yang on 2019-07-19.
 */
@RequestMapping("/api")
@FeignClient(name = "springcloud-provider-apiuser",
        fallback = ConsumerFeignServiceFallBack.class)
public interface ConsumerFeignService {
    @GetMapping("user/{id}")
    User getUserById(@PathVariable("id") long id);

    @GetMapping("hello")
    String echo(@RequestParam("name") String name);
}

