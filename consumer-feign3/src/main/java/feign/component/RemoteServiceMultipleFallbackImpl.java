package feign.component;

import feign.service.RemoteServiceMultiple;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Frankie Yang on 2019-07-19.
 */
@Component
@RequestMapping("/fallback/zzz")
public class RemoteServiceMultipleFallbackImpl implements RemoteServiceMultiple {

    private final Logger logger = Logger.getLogger(getClass());

    @Override
    public Object frank(@RequestParam("a") int x, @RequestParam("b") int y) {
        logger.warn("[RemoteServiceMultipleFallbackImpl] frank is called !");

        Map<String, Object> result = new HashMap<>();
        result.put("from", "frank Fallback method");
        result.put("a", x);
        result.put("b", y);

        return result;
    }

    @Override
    public Object x(@RequestParam int x, @RequestParam int y) {
        logger.warn("[RemoteServiceMultipleFallbackImpl] x is called !");

        Map<String, Object> result = new HashMap<>();
        result.put("from", "x Fallback method");
        result.put("a", x);
        result.put("b", y);

        return result;
    }

    @Override
    public Object x2(@RequestParam int x, @RequestParam int y) {
        logger.warn("[RemoteServiceMultipleFallbackImpl] x2 is called !");

        Map<String, Object> result = new HashMap<>();
        result.put("from", "x2 Fallback method");
        result.put("a", x);
        result.put("b", y);

        return result;
    }

    @Override
    public Object multiply(@PathVariable("a") int x, @PathVariable("b") int y) {
        logger.warn("[RemoteServiceMultipleFallbackImpl] multiply is called !");

        Map<String, Object> result = new HashMap<>();
        result.put("from", "multiply Fallback method");
        result.put("a", x);
        result.put("b", y);

        return result;
    }

    @Override
    public Object multiply(@PathVariable("a") int x, @PathVariable("b") int y, @PathVariable("c") int z) {
        logger.warn("[RemoteServiceMultipleFallbackImpl] multiply is called !");

        Map<String, Object> result = new HashMap<>();
        result.put("from", "multiply Fallback method");
        result.put("a", x);
        result.put("b", y);
        result.put("c", z);

        return result;
    }

    @Override
    public Object multiplz(@PathVariable("a") int x, @PathVariable("b") int y, @PathVariable("c") int z) {
        logger.warn("[RemoteServiceMultipleFallbackImpl] multiplz is called !");

        Map<String, Object> result = new HashMap<>();
        result.put("from", "\n\n\n\nmultiplz Fallback method\n\n");
        result.put("a", x);
        result.put("b", y);
        result.put("c", z);

        return result;
    }

    @Override
    public Object multiplz(@PathVariable("i") int i, @PathVariable("j") int j, @PathVariable("a") int x, @PathVariable("b") int y, @PathVariable("c") int z) {
        logger.warn("[RemoteServiceMultipleFallbackImpl] multiplz w5 with 5 parameters is called !");

        Map<String, Object> result = new HashMap<>();
        result.put("from", "\n\n\n\nmultiplz w5 Fallback method\n\n");
        result.put("i", i);
        result.put("j", j);
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

