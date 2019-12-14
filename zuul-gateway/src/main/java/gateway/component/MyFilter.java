package gateway.component;

import com.netflix.zuul.ZuulFilter;
import org.springframework.stereotype.Component;

/**
 * @author Frankie Yang on 2019-07-25.
 */
@Component
public class MyFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre"; //定义filter的类型，有pre、route、post、error四种
    }

    @Override
    public int filterOrder() {
        return 10; //定义filter的顺序，数字越小表示顺序越高，越先执行
    }

    @Override
    public boolean shouldFilter() {
        return true; //表示是否需要执行该filter，true表示执行，false表示不执行
    }

    @Override
    public Object run() {
        return null; //filter需要执行的具体操作
    }
}

