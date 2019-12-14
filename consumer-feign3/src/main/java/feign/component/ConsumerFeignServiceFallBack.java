package feign.component;

import feign.hystrix.FallbackFactory;
import feign.service.ConsumerFeignService;
import org.springframework.stereotype.Component;

/**
 * @author Frankie Yang on 2019-07-19.
 */
@Component
public class ConsumerFeignServiceFallBack implements FallbackFactory<ConsumerFeignService> {

    /**
     * 使用 Fallback 功能后出问题的 2个解决方法：
     *
     * 1. 修改 FallBack 实现类的 Mapping 路径；
     * 2. 使用 FallbackFactory；
     */

    @Override
    public ConsumerFeignService create(Throwable cause) {
        return new ConsumerFeignService() {
            @Override
            public User getUserById(long id) {
                return new User();
            }

            @Override
            public String echo(String name) {
                return "echo error: " + name;
            }
        };
    }
}
