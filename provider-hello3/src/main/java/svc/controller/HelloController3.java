package svc.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import svc.property.HomeProperties;
import svc.property.UserProperties;

import java.util.List;

/**
 * @author Frankie Yang on 2019-07-09.
 */
@RestController
public class HelloController3 {
    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private DiscoveryClient client;

    @Autowired
    private HomeProperties homeProperties;

    @Autowired
    private UserProperties userProperties;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index() {
        List<ServiceInstance> instances = client.getInstances("springcloud-provider-hello");
        for (int i = 0; i < instances.size(); i++) {
            logger.info("/hello,host:" + instances.get(i).getHost() + ",service_id:" + instances.get(i).getServiceId());
        }
        return "Hello World";
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String info() {
        List<ServiceInstance> instances = client.getInstances("springcloud-provider-hello");

        StringBuilder sb = new StringBuilder();
        sb.append("default [provider-hello3] content!");

        for (int i = 0; i < instances.size(); i++) {
            sb.append("</br>INFO host:")
                    .append(instances.get(i).getHost())
                    .append(",service_id:")
                    .append(instances.get(i).getServiceId());
            logger.info(sb.toString());
        }

        return sb.toString();
    }


    @RequestMapping("/")
    public String defaultPage() {
        return "Default Page";
    }

    @RequestMapping("/home")
    public String homePage() {
        return "frankie - home " + homeProperties.toString();
    }

    @RequestMapping("/user")
    public String userPage() {
        return "frankie - user " + userProperties.toString();
    }

}
