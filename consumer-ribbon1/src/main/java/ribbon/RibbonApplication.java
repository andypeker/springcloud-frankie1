package ribbon;

import com.netflix.loadbalancer.ILoadBalancer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerAutoConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.RibbonAutoConfiguration;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClientConfiguration;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import ribbon.config.RibbonLBAutoConfiguration;

/**
 * @author Frankie Yang on 2019-07-09.
 */
@EnableDiscoveryClient
@SpringBootApplication
@RibbonClient(name = "springcloud-provider-ribbon1", configuration = RibbonLBAutoConfiguration.class)
public class RibbonApplication {

    public static void main(String[] args) {
        SpringApplication.run(RibbonApplication.class, args);
    }

    RibbonAutoConfiguration rac;

    LoadBalancerAutoConfiguration lbautocfg;

    LoadBalancerClient lbc;
    RibbonLoadBalancerClient rlbc;

    RibbonClientConfiguration rccfg;

    /**
     * Ribbon 的负载均衡，主要通过 LoadBalancerClient 来实现的，而 LoadBalancerClient 具体交给了 ILoadBalancer 来处理，
     * ILoadBalancer 通过配置 IRule、IPing 等信息，并向 EurekaClient 获取注册列表的信息，并默认10秒一次向 EurekaClient
     * 发送“ping”,进而检查是否更新服务列表，最后，得到注册列表后，ILoadBalancer根据IRule的策略进行负载均衡。
     *
     * 而 RestTemplate 被 @LoadBalance 注解后，能过用负载均衡，主要是维护了一个被 @LoadBalance 注解的 RestTemplate 列表，
     * 并给列表中的 RestTemplate 添加拦截器，进而交给负载均衡器去处理。
     *
     * */

}