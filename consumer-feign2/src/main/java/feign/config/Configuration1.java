package feign.config;

import feign.Contract;
import feign.Logger;
import feign.Request;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Frankie Yang on 2019-07-22.
 */
@Configuration
public class Configuration1 {

    public static final int TIMEOUT_SECONDS = 7000;

    /*@Bean
    public Contract feignContract() {
        return new feign.Contract.Default();
    }*/

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;  //定义feign日志显示级别
    }

    @Bean
    public Request.Options options() {
        return new Request.Options(TIMEOUT_SECONDS, TIMEOUT_SECONDS);
    }

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("root", "password"); //Eureka添加了安全验证，则需要配置上面的用户名、密码
    }

}

