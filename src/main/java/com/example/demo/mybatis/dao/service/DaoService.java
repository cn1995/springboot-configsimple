package com.example.demo.mybatis.dao.service;

import com.example.demo.mybatis.dao.BuaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DaoService {

    @Autowired
    private BuaDao BUADAO;

    public void transfer(String name) {

        BUADAO.getAccountByUserName(name); //转出
        //BUADAO.moveIn(inner, money); //转入

    }
}
