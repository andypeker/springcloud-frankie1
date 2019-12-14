package frankie;

import frankie.config.RibbonConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@RibbonClient(name = "springcloud-provider-ribbon2", configuration = RibbonConfiguration.class)
//@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = ExtendRibbon.class)})
@EnableHystrix
public class RibbonConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RibbonConsumerApplication.class, args);
	}

	/*@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}*/


	/**
	 * 这样设置的超时，似乎并没有卵效果
	 * */
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate2() {
		SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
		simpleClientHttpRequestFactory.setConnectTimeout(6000);
		simpleClientHttpRequestFactory.setReadTimeout(6000);
		return new RestTemplate(simpleClientHttpRequestFactory);
	}

}
