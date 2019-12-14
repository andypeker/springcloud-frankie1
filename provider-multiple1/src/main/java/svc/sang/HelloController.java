package svc.sang;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import svc.component.Result;

/**
 * Created by sang on 2017/9/2.
 */
@RestController
public class HelloController {
    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private DiscoveryClient client;

    @Value("${spring.application.name}")
    private String serviceName;

    @Value("${spring.cloud.client.ipAddress}")
    private String address;

    @Value("${server.port}")
    private String port;

    @Autowired
    RestTemplate restTemplate;

    @Value("${springappname.consumer.ribbon2}")
    private String consumerRibbon2;

    @RequestMapping(value = "/frank")
    public Result frank(@RequestParam int a, @RequestParam int b) {
        Result result = new Result();
        result.setServiceName(serviceName);
        result.setHost(String.format("%s:%s", address, port));
        result.setMessage("frank(double)");
        result.setContent(a * b);
        return result;
    }

    @RequestMapping(value = "/x")
    public Result x(@RequestParam int a, @RequestParam int b) {
        Result result = new Result();
        result.setServiceName(serviceName);
        result.setHost(String.format("%s:%s", address, port));
        result.setMessage("x");
        result.setContent(a * b);
        return result;
    }

    @RequestMapping(value = "/x2/{a}/{b}")
    public Result x2(@PathVariable("a") int a, @PathVariable("b") int b) {
        Result result = new Result();
        result.setServiceName(serviceName);
        result.setHost(String.format("%s:%s", address, port));
        result.setMessage("x2");
        result.setContent(String.valueOf((long) a * (long) b));
        return result;
    }

    @RequestMapping(value = "/multiply/{a}/{b}")
    public Result multiply(@PathVariable("a") int a, @PathVariable("b") int b) {
        Result result = new Result();
        result.setServiceName(serviceName);
        result.setHost(String.format("%s:%s", address, port));
        result.setMessage("multiply(double)");
        result.setContent(a * b);
        return result;
    }

    @RequestMapping(value = "/multiply/{a}/{b}/{c}")
    public Result multiply(@PathVariable("a") int a, @PathVariable("b") int b, @PathVariable("c") int c) {
        Result result = new Result();
        result.setServiceName(serviceName);
        result.setHost(String.format("%s:%s", address, port));
        result.setMessage("multiply(triple)");
        result.setContent(String.valueOf((long) a * (long) b * (long) c));
        return result;
    }

    @RequestMapping(value = "/multiplz/{a}/{b}/{c}")
    public Result multiplz(@PathVariable("a") int a, @PathVariable("b") int b, @PathVariable("c") int c) {
        Result result = new Result();
        result.setServiceName(serviceName);
        result.setHost(String.format("%s:%s", address, port));
        result.setMessage("multiplz(triple)");
        result.setContent(String.valueOf((long) a * (long) b * (long) c));
        return result;
    }

    @RequestMapping(value = "/multiplz/{i}/{j}/{a}/{b}/{c}")
    public Result multiplz(@PathVariable("i") int i, @PathVariable("j") int j, @PathVariable("a") int a, @PathVariable("b") int b, @PathVariable("c") int c) {
        try {
            if (i < 1000 || j < 1000){
                i = 3000;
                j = 7000;
            }
            long waiting = (long) (Math.random() * j + i);
            logger.warn("\nmultiplz-i-j\t\t" + waiting);
            Thread.sleep(waiting);
        } catch (InterruptedException ie) {}

        Result result = new Result();
        result.setServiceName(serviceName);
        result.setHost(String.format("%s:%s", address, port));
        result.setMessage("multiplz(triple)");
        result.setContent(String.valueOf((long) a * (long) b * (long) c));
        return result;
    }


    @RequestMapping(value = "/stepribbon2/consumer")
    public Result stepribbon2step1(){
        String url = "http://"+ consumerRibbon2 +"/consumer";
//        return restTemplate.getForEntity(url, String.class).getBody();
        ResponseEntity resp = restTemplate.getForEntity(url, String.class);

        Result result = new Result();
        result.setCode(resp.getStatusCodeValue());
//        result.setServiceName("stepribbon2-consumer");
//        result.setHost("localhost");
//        result.setMessage("stepribbon2-consumer");
        result.setContent(resp.getBody().toString());
        return result;
    }

    @RequestMapping(value = "/stepribbon2/hola-info")
    public Result stepribbon2step2(){
        String url = "http://"+ consumerRibbon2 +"/hola-info";
//        return restTemplate.getForEntity(url, String.class).getBody();
        ResponseEntity resp = restTemplate.getForEntity(url, String.class);

        Result result = new Result();
        result.setCode(resp.getStatusCodeValue());
        result.setContent(resp.getBody().toString());
        return result;
    }

    @RequestMapping(value = "/stepribbon2/hola-hello")
    public Result stepribbon2step3(){
        String url = "http://"+ consumerRibbon2 +"/hola-hello";
//        return restTemplate.getForEntity(url, String.class).getBody();
        ResponseEntity resp = restTemplate.getForEntity(url, String.class);

        Result result = new Result();
        result.setCode(resp.getStatusCodeValue());
        result.setContent(resp.getBody().toString());
        return result;
    }

    @RequestMapping(value = "/stepribbon2/frankie-dream")
    public Result stepribbon2step4(){
        String url = "http://"+ consumerRibbon2 +"/frankie/dream";
//        return restTemplate.getForEntity(url, String.class).getBody();
        ResponseEntity resp = restTemplate.getForEntity(url, String.class);

        Result result = new Result();
        result.setCode(resp.getStatusCodeValue());
        result.setContent(resp.getBody().toString());
        return result;
    }

    @RequestMapping(value = "/stepribbon2/frankie-money")
    public Result stepribbon2step5(){
        String url = "http://"+ consumerRibbon2 +"/frankie/money";
//        return restTemplate.getForEntity(url, String.class).getBody();
        ResponseEntity resp = restTemplate.getForEntity(url, String.class);

        Result result = new Result();
        result.setCode(resp.getStatusCodeValue());
        result.setContent(resp.getBody().toString());
        return result;
    }
}
