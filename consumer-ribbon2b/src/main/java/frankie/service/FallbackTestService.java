package frankie.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Frankie Yang on 2019-07-19.
 */
@Service
public class FallbackTestService {
    private final Logger logger = Logger.getLogger(getClass());

    @Value("${springappname.provider.multiple}")
    private String multipleServiceName;

    @Autowired
    private RestTemplate restTemplate;


    /**
     * TODO 使用 siegn 并发请求，观察降级现象
     * $ siege -c 4 -t 10s http://10.19.139.102:38073/user/fallbackmethodtest/2/3
     * $ siege -c 4 -t 10s http://10.19.139.102:38073/user/fallbackmethodtest/2/3/5
     * */


    @HystrixCommand(fallbackMethod = "multiplyFallback2",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE"),
                    @HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests", value = "3")
            })
    public Object multiply(int a, int b) {
        String url = "http://" + multipleServiceName + "/multiply/" + a + "/" + b;
        return restTemplate.getForObject(url, String.class);
//        throw new RuntimeException("consumer exception");
    }

    private Object multiplyFallback2(int x, int y, Throwable e) {
        logger.warn("[fallbackMethod] multiplyFallback2 is called !");

        //TODO:额外增加的Throwable e，以便可以根据throwable的不同执行不同的逻辑
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("from", "multiply Fallback method");
        result.put("a", x);
        result.put("b", y);
        result.put("ex", e.getMessage());
        return result;
    }


    @HystrixCommand(fallbackMethod = "multiplyFallback3",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE"),
                    @HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests", value = "4")
            })
    public Object multiply(int a, int b, int c) {
        String url = "http://" + multipleServiceName + "/multiply/" + a + "/" + b + "/" + c;
        return restTemplate.getForObject(url, String.class);
//        throw new RuntimeException("consumer exception");
    }

    private Object multiplyFallback3(int x, int y, int z, Throwable e) {
        logger.warn("[fallbackMethod] multiplyFallback3 is called !");

        //TODO:额外增加的Throwable e，以便可以根据throwable的不同执行不同的逻辑
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("from", "multiply Fallback method");
        result.put("a", x);
        result.put("b", y);
        result.put("c", z);
        result.put("ex", e.getMessage());
        return result;
    }


    @HystrixCommand(fallbackMethod = "multiplzFallback",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE"),
                    @HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests", value = "5"),
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000")
            })
    public Object multiplz(int a, int b, int c) {
        String url = "http://" + multipleServiceName + "/multiplz/" + a + "/" + b + "/" + c;
        return restTemplate.getForObject(url, String.class);
//        throw new RuntimeException("consumer exception");
    }

    private Object multiplzFallback(int x, int y, int z, Throwable e) {
        logger.warn("[fallbackMethod] multiplzFallback is called !");

        //TODO:额外增加的Throwable e，以便可以根据throwable的不同执行不同的逻辑
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("from", "multiplz Fallback method");
        result.put("a", x);
        result.put("b", y);
        result.put("c", z);
        result.put("ex", e.getMessage());
        return result;
    }


    @HystrixCommand(fallbackMethod = "multiplzFallback",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE"),
                    @HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests", value = "5"),
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "8000")
            })
    public Object multiplz(int i, int j, int a, int b, int c) {
        String url = "http://" + multipleServiceName + "/multiplz/" + i + "/" + j + "/" + a + "/" + b + "/" + c;
        return restTemplate.getForObject(url, String.class);
//        throw new RuntimeException("consumer exception");
    }

    private Object multiplzFallback(int i, int j, int x, int y, int z, Throwable e) {
        logger.warn("[fallbackMethod] multiplzFallback w5 is called !");

        //TODO:额外增加的Throwable e，以便可以根据throwable的不同执行不同的逻辑
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("from", "multiplz w5 Fallback method");
        result.put("i", i);
        result.put("j", j);
        result.put("a", x);
        result.put("b", y);
        result.put("c", z);
        result.put("ex", e.getMessage());
        return result;
    }
}
