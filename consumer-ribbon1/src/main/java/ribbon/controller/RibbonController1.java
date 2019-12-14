package ribbon.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Frankie Yang on 2019-07-10.
 */

@RestController
public class RibbonController1 {
    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    RestTemplate restTemplate;

    @Value("${springappname.provider.hello}")
    private String helloServiceName;

    @RequestMapping("/consumer")
    public String hello() {
        return "hello --- Ribbon-Consumer1";
    }

    @RequestMapping(value = "/hello-hello", method = RequestMethod.GET)
    public String helloController1() {
        return restTemplate.getForEntity("http://springcloud-provider-hello/hello", String.class).getBody();
    }

    @RequestMapping(value = "/hello-info", method = RequestMethod.GET)
    public String infoController1() {
        return restTemplate.getForEntity("http://springcloud-provider-hello/info", String.class).getBody();
    }

    @RequestMapping(value = "/hello-info2", method = RequestMethod.GET)
    public String infoController2() {
        logger.warn("\n\n\n" + "use @Value for getting service 's name" + "\n\n\n");
        String url = "http://" + helloServiceName + "/info";
        return restTemplate.getForEntity(url, String.class).getBody();
    }

    @Deprecated
    @RequestMapping(value = "/consumer3", method = RequestMethod.GET)
    public String helloController3() {
        return restTemplate.getForEntity("http://HELLO-SVC3/info", String.class).getBody();
    }

}
