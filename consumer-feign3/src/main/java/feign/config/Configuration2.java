package feign.config;

import feign.Logger;
import feign.Request;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Frankie Yang on 2019-07-22.
 */
@Configuration
public class Configuration2 {
    public static final int TIMEOUT_SECONDS = 7000;

    @Bean
    public Logger.Level feignLogger() {
        return Logger.Level.FULL;
    }

    @Bean
    public Request.Options options() {
        return new Request.Options(TIMEOUT_SECONDS, TIMEOUT_SECONDS);
    }

    /*@Bean
    public Contract feignContract() {
        return new feign.Contract.Default();
    }*/

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("root", "password"); //Eureka添加了安全验证，则需要配置上面的用户名、密码
    }
}

