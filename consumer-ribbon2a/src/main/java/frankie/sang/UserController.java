package frankie.sang;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import frankie.components.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sang on 2017/9/4.
 */
//@Api("springcloud consumer user 控制器")
@RequestMapping("user")
@RestController
public class UserController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Value("${springappname.provider.multiple}")
    private String multipleServiceName;

    /**
     * @param id
     * @return
     */
//    @ApiOperation(value = "根据用户id查询用户信息", httpMethod = "GET", produces = "application/json")
//    @ApiResponse(code = 200, message = "success", response = User.class)
    @GetMapping("{id}")
    @HystrixCommand(fallbackMethod="userFallbackMethod")
    public User getUser(/*@ApiParam(name = "id", required = true, value = "用户Id") */@PathVariable String id) {
        return this.restTemplate.getForObject("http://springcloud-provider-config/user/" + id, User.class);
    }

    public User userFallbackMethod(String id){
        return null;
    }

    /**
     * 这块ribbon不支持复杂数据类型list，所以要用数组接受，然后转list
     * @return
     */
    @GetMapping("list")
    @HystrixCommand(fallbackMethod = "userList")
    public List<User> users(HttpServletRequest request) {
        try {
            User[] forObject = this.restTemplate.getForObject("http://springcloud-provider-config/user/list", User[].class);
            List<User> users = Arrays.asList(forObject);
            return users == null ? new ArrayList<User>() : users;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> userList(HttpServletRequest request) {
        return null;
    }

    /**
     * 通过服务id获取服务的地址
     * @return
     */
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

}

