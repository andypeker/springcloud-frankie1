package feign.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Frankie Yang on 2019-07-17.
 */
@Service
public class RestServiceMulti2 {
    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private RestTemplate restTemplate;

    @Value("${springappname.provider.multiple}")
    private String multipleServiceName;

    public Object x(int a, int b) {
        logger.warn("[RestTemplate] x");
        String url = "http://" + multipleServiceName + "/x/" + a + "/" + b;
        return restTemplate.getForObject(url, String.class);
    }

}
