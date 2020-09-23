package com.example.demo;

import com.example.demo.master.User;
import com.example.demo.master.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService implements UserServiceImp {
    @Resource
    private UserRepository userRepository;

    public User loadUserByUsername(String username) {

        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new AuthenticationExcep("user not exist");
        }

        return user;
    }
    public  String saveUser(User user){

        User save = userRepository.save(user);


        return  save.toString();
    }

}
