package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.master.BankUserAccount;
import com.example.demo.master.User;
import com.example.demo.mybatis.dao.BuaDao;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private BuaDao buadao;

    @ResponseBody
    @RequestMapping("/getsome")
    public Map getsome(@RequestBody Map pageRequest) {
        try {
            // andy 给lucy转账50元
            List<BankUserAccount> abc = buadao.getAccountByUserName("abc");
            BankUserAccount abcd = buadao.getAccountByUserId("1234321421");

            PageHelper.startPage(pageRequest);
            Page<BankUserAccount> data = buadao.findAll(pageRequest);
            JSONObject result = new JSONObject();
            result.put("pageNum", data.getPageNum());
            result.put("pages", data.getPages());
            result.put("pagesSize", data.getPageSize());
            result.put("total", data.getTotal());
            result.put("result", data);

            return result;
            // PageHelper.startPage(pageNum, pageSize);
            //  PageImpl<BankUserAccount> all = buadao.findAll(pageRequest);


            // return all.getContent();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/insert")
    @ResponseBody
//    @Transactional
    public Boolean inset(@RequestBody List<BankUserAccount> users) {
        users.forEach(e -> buadao.save(e));
        if (users.size() > 0) {
//            throw new RuntimeException("transaction");
            return true;
        } else {
            return false;
        }
    }


}
