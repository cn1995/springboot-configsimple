package com.example.demo;

import com.example.demo.master.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Controller
@SpringBootApplication
public class DemoApplication {
    static Logger logger = LoggerFactory.getLogger(DemoApplication.class);
    @Resource
    UserService userService;


    public static void main(String[] args) {

        logger.info("Init start");
        SpringApplication.run(DemoApplication.class, args);
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("layout")
    public String layout() {
        return "layout";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @Resource
    DataSource dataSource;

    @ResponseBody
    @RequestMapping("/t")
    public String test() {
        return dataSource.toString();
    }

    @ResponseBody
    @PostMapping("/sign_up")
    public String signUp(String username, String password) {
        String result = "";
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setActive(true);
        user.setUsername(username);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        String s = userService.saveUser(user);

        return s;
    }

}
