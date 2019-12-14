package feign.component;

import feign.service.RemoteServiceMultiple;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Frankie Yang on 2019-07-19.
 */
@Component
public class RemoteServiceMultipleFallbackImpl implements RemoteServiceMultiple {

    @Override
    public Object frank(@RequestParam int x, @RequestParam int y) {
        Map<String, Object> result = new HashMap<>();
        result.put("from", "frank Fallback method");
        result.put("a", x);
        result.put("b", y);

        return result;
    }

    @Override
    public Object x(@RequestParam int x, @RequestParam int y) {
        Map<String, Object> result = new HashMap<>();
        result.put("from", "x Fallback method");
        result.put("a", x);
        result.put("b", y);

        return result;
    }

    @Override
    public Object x2(@RequestParam int x, @RequestParam int y) {
        Map<String, Object> result = new HashMap<>();
        result.put("from", "x2 Fallback method");
        result.put("a", x);
        result.put("b", y);

        return result;
    }

    @Override
    public Object multiply(@PathVariable("a") int x, @PathVariable("b") int y) {
        Map<String, Object> result = new HashMap<>();
        result.put("from", "multiply p2 Fallback method");
        result.put("a", x);
        result.put("b", y);

        return result;
    }

    @Override
    public Object multiply(@PathVariable("a") int x, @PathVariable("b") int y, @PathVariable("c") int z) {
        Map<String, Object> result = new HashMap<>();
        result.put("from", "multiply p3 Fallback method");
        result.put("a", x);
        result.put("b", y);
        result.put("c", z);

        return result;
    }

    @Override
    public Object multiplz(@PathVariable("a") int x, @PathVariable("b") int y, @PathVariable("c") int z) {
        Map<String, Object> result = new HashMap<>();
        result.put("from", "multiplz Fallback method");
        result.put("a", x);
        result.put("b", y);
        result.put("c", z);

        return result;
    }


    @Override
    public Object stepribbon2step1() {
        Map<String, Object> result = new HashMap<>();
        result.put("from", "\n\n\n\nstepribbon2step1 Fallback method\n\n");
        return result;
    }

    @Override
    public Object stepribbon2step2() {
        Map<String, Object> result = new HashMap<>();
        result.put("from", "\n\n\n\nstepribbon2step2 Fallback method\n\n");
        return result;
    }

    @Override
    public Object stepribbon2step3() {
        Map<String, Object> result = new HashMap<>();
        result.put("from", "\n\n\n\nstepribbon2step3 Fallback method\n\n");
        return result;
    }

    @Override
    public Object stepribbon2step4() {
        Map<String, Object> result = new HashMap<>();
        result.put("from", "\n\n\n\nstepribbon2step4 Fallback method\n\n");
        return result;
    }

    @Override
    public Object stepribbon2step5() {
        Map<String, Object> result = new HashMap<>();
        result.put("from", "\n\n\n\nstepribbon2step5 Fallback method\n\n");
        return result;
    }

}
