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

    @Value("${zuowenjun.skills}")
    public String zwjSkills;

    @RequestMapping("/consumer")
    public String hello(){
        return "hello --- Ribbon-Consumer2b";
    }

    @RequestMapping(value = "/hola-hello",method = RequestMethod.GET)
    public String helloController1() {
        return restTemplate.getForEntity("http://springcloud-provider-hola/hello", String.class).getBody();
    }

    @RequestMapping(value = "/hola-info",method = RequestMethod.GET)
    public String helloController2() {
        return restTemplate.getForEntity("http://springcloud-provider-hola/info", String.class).getBody();
    }

    @RequestMapping("/frankie/money")
    public String money(){
        return "hello --- frankie 's money: " + money;
    }

    @RequestMapping("/frankie/dream")
    public String dream(){
        return "hello --- frankie 's dream: " + dream;
    }

    @RequestMapping("/zuowenjun1")
    public String zuowenjun1(){
        return "hello --- zuowenjun1: " + zwjSkills;
    }

}
