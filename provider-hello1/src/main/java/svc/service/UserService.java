package svc.service;

import org.springframework.stereotype.Service;
import svc.components.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Frankie Yang on 2019-07-11.
 */
@Service
public class UserService {

    public User find(String id){
        return new User();
    }


    public List<User> findAll(){
        return new ArrayList();
    }
}
