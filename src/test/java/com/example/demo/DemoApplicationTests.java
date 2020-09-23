package com.example.demo;

import com.alibaba.druid.filter.config.ConfigFilter;
import com.alibaba.druid.filter.config.ConfigTools;
import com.alibaba.druid.filter.stat.StatFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import sun.nio.cs.AbstractCharsetProvider;

import javax.annotation.Resource;
import java.util.ArrayList;

@SpringBootTest
class DemoApplicationTests {
//    @Resource
//    compBean comBean1;
//
//    @Resource
//    propBean propBean;
//
//    @Test
//    void contextLoads() {
//        System.out.println(propBean.name);
//        System.out.println(comBean1.name);
//    }
//
//    @Test
//    public void druidEncrypt() throws Exception {
//        //密码明文
//        String password = "root";
//        System.out.println("明文密码: " + password);
//        String[] keyPair = ConfigTools.genKeyPair(512);
//        //私钥
//        String privateKey = keyPair[0];
//        //公钥
//        String publicKey = keyPair[1];
//
//        //用私钥加密后的密文
//        password = ConfigTools.encrypt(privateKey, password);
//
//        System.out.println("privateKey:" + privateKey);
//        System.out.println("publicKey:" + publicKey);
//
//        System.out.println("password:" + password);
//
//        String decryptPassword = ConfigTools.decrypt(publicKey, password);
//        System.out.println("解密后:" + decryptPassword);
//    }

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void test01(){
        ABean aBean = new ABean();
        aBean.a="a";
        aBean.bC="bC";
        ArrayList<Object> objects = new ArrayList<>();
        objects.add("1234");
        objects.add("5678");
        aBean.listString=objects;
        String s = Integer.toString(aBean.hashCode());
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set(aBean.bC,aBean);


        System.out.println(s);
        Boolean aBoolean = redisTemplate.hasKey(aBean.bC);

        Object o =  redisTemplate.opsForValue().get(aBean.bC);
        System.out.println(o);
      //  System.out.println(o.a+o.bC+o.listString);
    }
}
