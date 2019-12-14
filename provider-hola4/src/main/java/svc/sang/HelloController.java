package svc.sang;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by sang on 2017/9/2.
 */
@RestController
public class HelloController {
    private final Logger logger = Logger.getLogger(getClass());
    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index() {
        List<ServiceInstance> instances = client.getInstances("springcloud-provider-hola");
        for (int i = 0; i < instances.size(); i++) {
            logger.info("/hello,host:" + instances.get(i).getHost() + ",service_id:" + instances.get(i).getServiceId());
        }
        return "Hello World - 38094";
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String info() {
        List<ServiceInstance> instances = client.getInstances("springcloud-provider-hola");

        StringBuilder sb = new StringBuilder();
        sb.append("default [provider] content!");

        for (int i = 0; i < instances.size(); i++) {
            ServiceInstance si = instances.get(i);
            sb.append("</br>INFO host:")
                    .append(si.getHost())
                    .append(",port:")
                    .append(si.getPort())
                    .append(",service_id:")
                    .append(si.getServiceId());
            logger.info(sb.toString());
        }

        return sb.toString();
    }

}
