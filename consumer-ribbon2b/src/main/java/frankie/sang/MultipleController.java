package frankie.sang;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import frankie.service.FallbackTestService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author Frankie Yang on 2019-07-22.
 */
@RequestMapping("/multi")
@RestController
public class MultipleController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    /**
     * TODO
     *     @Autowired
     *     private DiscoveryClient client;
     *     获取全部的 client 列表
     *
     *     @Autowired
     *     private LoadBalancerClient loadBalancerClient;
     *     获取负载均衡选中的 某个client
     * */

    @Autowired
    private FallbackTestService fallbackTestService;

    @Value("${springappname.provider.multiple}")
    private String multipleServiceName;


    @GetMapping("/ribbon")
    public String ribbon() {
        ServiceInstance serviceInstance = loadBalancerClient.choose(multipleServiceName);
        StringBuilder sb = new StringBuilder();
        sb.append("<br>URI:" + serviceInstance.getUri().toString());
        sb.append("<br>Port:" + serviceInstance.getPort());
        sb.append("<br>Host:" + serviceInstance.getHost());
        sb.append("<br>ServiceId:" + serviceInstance.getServiceId());
        sb.append("<br>Metadata:" + serviceInstance.getMetadata());
        logger.warn(sb);
//        return serviceInstance.getUri().toString();
        return sb.toString();
    }

    @GetMapping("/multiply/{a}/{b}")
    public String multiplytest(@PathVariable("a") int a, @PathVariable("b") int b){
        return fallbackTestService.multiply(a, b).toString();
    }

    @GetMapping("/multiply/{a}/{b}/{c}")
    public String multiplytest(@PathVariable("a") int a, @PathVariable("b") int b, @PathVariable("c") int c){
        return fallbackTestService.multiply(a, b, c).toString();
    }

    @GetMapping("/multiplz/{a}/{b}/{c}")
    public String multiplztest(@PathVariable("a") int a, @PathVariable("b") int b, @PathVariable("c") int c){
        return fallbackTestService.multiplz(a, b, c).toString();
    }

    @GetMapping("/multiplz/{i}/{j}/{a}/{b}/{c}")
    public String multiplztest(@PathVariable("i") int i, @PathVariable("j") int j, @PathVariable("a") int a, @PathVariable("b") int b, @PathVariable("c") int c){
        return fallbackTestService.multiplz(i, j, a, b, c).toString();
    }

    @GetMapping("/x2")
    @HystrixCommand(fallbackMethod = "fallbackx2")
    public String x2(@RequestParam int a, @RequestParam int b) {
        try {
            return restTemplate.getForEntity("http://" + multipleServiceName + "/x2/" + a + "/" + b, String.class).getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String fallbackx2(int x, int y, Throwable te) {
        logger.warn("[fallbackMethod] fallbackx2 is called !");
        return null;
    }

    @GetMapping("/frank/{a}/{b}")
    @HystrixCommand(fallbackMethod = "frankFallback",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE"),
                    @HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests", value = "9")
            })
    public String frank(@PathVariable("a") int a, @PathVariable("b") int b) {
        try {
            return restTemplate.getForEntity("http://" + multipleServiceName + "/frank?a=" + a + "&b=" + b, String.class).getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String frankFallback(int x, int y, Throwable te) {
        logger.warn("[fallbackMethod] frankFallback is called !");
        return null;
    }

}
