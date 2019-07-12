package ribbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Frankie Yang on 2019-07-10.
 */

@RestController
public class RibbonController1 {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/hello1-info",method = RequestMethod.GET)
    public String helloController1() {
        return restTemplate.getForEntity("http://springcloud-provider-hello1/info", String.class).getBody();
    }

    @RequestMapping(value = "/hello2-info",method = RequestMethod.GET)
    public String helloController2() {
        return restTemplate.getForEntity("http://springcloud-provider-hello2/info", String.class).getBody();
    }

    @Deprecated
    @RequestMapping(value = "/consumer3",method = RequestMethod.GET)
    public String helloController3() {
        return restTemplate.getForEntity("http://HELLO-SVC3/info", String.class).getBody();
    }

    @RequestMapping(value = "/hola-info",method = RequestMethod.GET)
    public String helloController4() {
        return restTemplate.getForEntity("http://springcloud-provider-hola/info", String.class).getBody();
    }


}
