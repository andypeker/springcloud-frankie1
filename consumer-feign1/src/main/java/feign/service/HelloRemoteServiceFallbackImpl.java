package feign.service;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Frankie Yang on 2019-07-19.
 */
@Component
public class HelloRemoteServiceFallbackImpl implements RemoteServiceMulti1 {

    @Override
    public Object x(@PathVariable("a") int x, @PathVariable("b") int y) {
        Map<String, Object> result = new HashMap<>();
        result.put("from", "multiply Fallback method");
        result.put("a", x);
        result.put("b", y);

        return result;
    }

    @Override
    public Object x2(@PathVariable("a") int x, @PathVariable("b") int y) {
        Map<String, Object> result = new HashMap<>();
        result.put("from", "multiply Fallback method");
        result.put("a", x);
        result.put("b", y);

        return result;
    }

    @Override
    public Object multiply(@PathVariable("a") int x, @PathVariable("b") int y) {
        Map<String, Object> result = new HashMap<>();
        result.put("from", "multiply Fallback method");
        result.put("a", x);
        result.put("b", y);

        return result;
    }

    @Override
    public Object multiply(@PathVariable("a") int x, @PathVariable("b") int y, @PathVariable("c") int z) {
        Map<String, Object> result = new HashMap<>();
        result.put("from", "multiply Fallback method");
        result.put("a", x);
        result.put("b", y);
        result.put("c", z);

        return result;
    }

}
