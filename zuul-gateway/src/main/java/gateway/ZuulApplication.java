package gateway;

import gateway.component.AuthFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * @author Frankie Yang on 2019-07-10.
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
@RefreshScope
public class ZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }

    @Bean
    public AuthFilter tokenFilter() {
        return new AuthFilter();
    }

}

/**
 * https://www.cnblogs.com/zuowj/p/10645189.html
 * */