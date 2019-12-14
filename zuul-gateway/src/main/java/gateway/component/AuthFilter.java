package gateway.component;

/**
 * @author Frankie Yang on 2019-07-18.
 */

import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import com.netflix.zuul.ZuulFilter;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义token验证过滤器，实现请求验证
 */
@Component
public class AuthFilter extends ZuulFilter {

//    private final Logger logger = Logger.getLogger(getClass());
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;//路由执行前
    }

    @Override
    public int filterOrder() {
        return 0;//过滤器优先顺序，数字越小越先执行
    }

    @Override
    public boolean shouldFilter() {
        if (RequestContext.getCurrentContext().getRequest().getRequestURL().toString().contains("/testgit/")) {
            return false;
        }
        return true;//是否需要过滤
    }

//    @Override
    public Object run1() /*throws ZuulException*/ {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        Object token = request.getHeader("token");
        if (token == null){
            token = request.getParameter("token");
        }
        //校验token
        Boolean isValid = false;

        if (StringUtils.equals(String.valueOf(token), "88888888")) { //模拟TOKEN验证,验证通过
            isValid = true;
        } else {
            logger.warn("token is :" + String.valueOf(token));
        }

        if (!isValid) {
            logger.error("token验证不通过，禁止访问！");
            ctx.setSendZuulResponse(false);//false表示不发送路由响应给消费端，即不会去路由请求后端服务
            ctx.getResponse().setContentType("text/html;charset=UTF-8");
            ctx.setResponseBody("token验证不通过，禁止访问！");
            ctx.setResponseStatusCode(401);
            return null;
        }

        logger.info(String.format("token is %s", token));

        return null;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        String tokenP = request.getParameter("token");// 获取请求的参数
        String tokenH = request.getHeader("token");

        String token = StringUtils.isNotBlank(tokenP) ? tokenP : StringUtils.isNotBlank(tokenH) ? tokenH : null;
        logger.info("--->>> TokenFilter {},{}", request.getMethod(), request.getRequestURL().toString());

        if (StringUtils.isNotBlank(token) && token.equals("666666")) {
            ctx.setSendZuulResponse(true); //对请求进行路由
            ctx.setResponseStatusCode(200);
            ctx.set("isSuccess", true);
            return null;
        } else {
            ctx.setSendZuulResponse(false); //不对其进行路由
            ctx.setResponseStatusCode(400);
            ctx.setResponseBody("token is empty or wrong");
            ctx.set("isSuccess", false);
            return null;
        }
    }

}
