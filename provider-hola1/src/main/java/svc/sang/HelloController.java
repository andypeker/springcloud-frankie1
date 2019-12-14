package svc.sang;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by sang on 2017/9/2.
 */
@RestController
@RefreshScope
public class HelloController {
    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private DiscoveryClient client;

    @Value("${yangweihua.money}")
    public String money;

    @Value("${yangweihua.dream}")
    public String dream;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index() {
        List<ServiceInstance> instances = client.getInstances("springcloud-provider-hola");
        for (int i = 0; i < instances.size(); i++) {
            logger.info("/hello,host:" + instances.get(i).getHost() + ",service_id:" + instances.get(i).getServiceId());
        }
        return "Hello World - 38091";
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String info() {
        List<ServiceInstance> instances = client.getInstances("springcloud-provider-hola");

        StringBuilder sb = new StringBuilder();
        sb.append("default [provider] content!");

        for (int i = 0; i < instances.size(); i++) {

            sb.append("</br>INFO host:")
                    .append(instances.get(i).getHost())
                    .append(",port:")
                    .append(instances.get(i).getPort())
                    .append(",service_id:")
                    .append(instances.get(i).getServiceId());
            logger.info(sb.toString());
        }

        return sb.toString();
    }


    @RequestMapping("/frankie2/money")
    public String money() {
        return "hello --- frankie2 's money: " + money;
    }

    @RequestMapping("/frankie2/dream")
    public String dream() {
        return "hello --- frankie2 's dream: " + dream;
    }

}
