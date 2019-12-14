package ribbon.config;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.cloud.client.loadbalancer.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Frankie Yang on 2019-07-31.
 */
@Configuration
public class RibbonLBAutoConfiguration {

    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * 获取所有带有@LoadBalanced注解的restTemplate
     */
    @LoadBalanced
    @Autowired(required = false)
    private List<RestTemplate> restTemplates = Collections.emptyList();

    /**
     * 创建SmartInitializingSingleton接口的实现类。Spring会在所有
     * 单例Bean初始化完成后回调该实现类的afterSingletonsInstantiated()
     * 方法。在这个方法中会为所有被@LoadBalanced注解标识的
     * RestTemplate添加ribbon的自定义拦截器LoadBalancerInterceptor。
     */
    @Bean
    public SmartInitializingSingleton loadBalancedRestTemplateInitializer(
            final List<RestTemplateCustomizer> customizers) {
        return new SmartInitializingSingleton() {
            @Override
            public void afterSingletonsInstantiated() {
                for (RestTemplate restTemplate : RibbonLBAutoConfiguration.this.restTemplates) {
                    for (RestTemplateCustomizer customizer : customizers) {
                        customizer.customize(restTemplate);
                    }
                }
            }
        };
    }

    /**
     * 创建Ribbon自定义拦截器LoadBalancerInterceptor
     * 创建前提是当前classpath下不存在spring-retry。
     * 所以LoadBalancerInterceptor是默认的Ribbon拦截
     * 请求的拦截器。
     */
    @Configuration
    @ConditionalOnMissingClass("org.springframework.retry.support.RetryTemplate")
    static class LoadBalancerInterceptorConfig {
        @Bean
        public LoadBalancerInterceptor ribbonInterceptor(
                LoadBalancerClient loadBalancerClient,
                LoadBalancerRequestFactory requestFactory) {
            return new LoadBalancerInterceptor(loadBalancerClient, requestFactory);
        }

        /**
         * 添加拦截器具体方法。首先获取当前拦截器集合(List)
         * 然后将loadBalancerInterceptor添加到当前集合中
         * 最后将新的集合放回到restTemplate中。
         */
        @Bean
        @ConditionalOnMissingBean
        public RestTemplateCustomizer restTemplateCustomizer(
                final LoadBalancerInterceptor loadBalancerInterceptor) {
            return new RestTemplateCustomizer() {
                @Override
                public void customize(RestTemplate restTemplate) {
                    List<ClientHttpRequestInterceptor> list = new ArrayList<>(
                            restTemplate.getInterceptors());
                    list.add(loadBalancerInterceptor);
                    restTemplate.setInterceptors(list);
                }
            };
        }
    }
}
