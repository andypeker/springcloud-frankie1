package frankie.sang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by sang on 2017/9/4.
 */
@RestController
@RefreshScope
public class ConsumerController {
    @Autowired
    RestTemplate restTemplate;

    @Value("${yangweihua.money}")
    public String money;

    @Value("${yangweihua.dream}")
    public String dream;

    @RequestMapping("/consumer")
    public String hello(){
        return "hello --- Ribbon-Consumer2a";
    }

    @RequestMapping(value = "/hola-hello",method = RequestMethod.GET)
    public String helloController1() {
        return restTemplate.getForEntity("http://springcloud-provider-hola/hello", String.class).getBody();
    }

    @RequestMapping(value = "/hola-info",method = RequestMethod.GET)
    public String helloController2() {
        return restTemplate.getForEntity("http://springcloud-provider-hola/info", String.class).getBody();
    }

    @Deprecated
    @RequestMapping(value = "/svc1-info",method = RequestMethod.GET)
    public String helloControllerSVC1() {
        return restTemplate.getForEntity("http://springcloud-provider-hello1/info", String.class).getBody();
    }

    @Deprecated
    @RequestMapping(value = "/svc2-info",method = RequestMethod.GET)
    public String helloControllerSVC2() {
        return restTemplate.getForEntity("http://springcloud-provider-hello2/info", String.class).getBody();
    }

    @Deprecated
    @RequestMapping(value = "/svc3-info",method = RequestMethod.GET)
    public String helloControllerSVC3() {
        return restTemplate.getForEntity("http://HELLO-SVC3/info", String.class).getBody();
    }

    @RequestMapping("/frankie2/money")
    public String money(){
        return "hello --- frankie2 's money: " + money;
    }

    @RequestMapping("/frankie2/dream")
    public String dream(){
        return "hello --- frankie2 's dream: " + dream;
    }

}
