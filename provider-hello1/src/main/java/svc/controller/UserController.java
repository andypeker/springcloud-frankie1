package svc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import svc.components.User;
import svc.service.UserService;

import java.util.List;

/**
 * @author Frankie Yang on 2019-07-11.
 */
@RequestMapping("user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public User getuser(@PathVariable String id) {
        User user = null;
        try {
            System.out.println(id);
            user = userService.find(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * @return
     */
    @GetMapping("list")
    public List<User> users() {
        try {
            List<User> user = userService.findAll();
            if (user != null && user.size() != 0) {
                return user;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
