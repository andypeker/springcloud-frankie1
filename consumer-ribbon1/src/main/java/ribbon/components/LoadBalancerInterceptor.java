package ribbon.components;

import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerRequestFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.net.URI;

/**
 * @author Frankie Yang on 2019-07-31.
 */
public class LoadBalancerInterceptor implements ClientHttpRequestInterceptor {

    private LoadBalancerClient loadBalancer;
    private LoadBalancerRequestFactory requestFactory;

    //省略构造器代码...

    @Override
    public ClientHttpResponse intercept(final HttpRequest request,
                                        final byte[] body,
                                        final ClientHttpRequestExecution execution) throws IOException {
        final URI originalUri = request.getURI();
        String serviceName = originalUri.getHost();
        /**
         *拦截请求，并调用loadBalancer.execute()方法
         *在该方法内部完成server的选取。向选取的server
         *发起请求，并获得返回结果。
         */
        return this.loadBalancer.execute(serviceName, requestFactory.createRequest(request, body, execution));
    }
}
