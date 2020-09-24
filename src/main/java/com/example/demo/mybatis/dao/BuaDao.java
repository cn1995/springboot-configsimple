package com.example.demo.mybatis.dao;

import com.example.demo.master.BankUserAccount;
import com.example.demo.mybatis.annotation.Master;
import com.example.demo.mybatis.annotation.Slave;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Slave
public interface BuaDao {


    @Select("select * from bank_user_account where user_name=#{userName}")
    public List<BankUserAccount> getAccountByUserName(String userName);


    @Select("select * from bank_user_account where user_id=#{userName} limit 1")
    //    @Select("select * from BankUserAccount where userId=#{userId}")
    public BankUserAccount getAccountByUserId(String userId);

    @Select("<script>" +
            "select * from bank_user_account where 1=1 " +
            " <if test='uuid != null'> " +
            "and  uuid=#{uuid} " +
            "</if>" + "</script>")
    Page<BankUserAccount> findAll(Map param);

//    @SelectKey(statement = "SELECT identity('users')", keyProperty = "uuid", before = true, resultType = int.class)

    @SelectKey(keyProperty = "uuid", resultType = String.class, before = true, statement = "select replace(uuid(), '-', '')")
//    @Options(keyProperty = "uuid", useGeneratedKeys = true)
    @Insert("INSERT INTO bank_user_account(uuid, money, user_account, user_id, user_name, user_password) " +
            "VALUES (#{uuid}, #{money}, #{userAccount}, #{userId}, #{userName}, #{userPassword})")
    boolean save(BankUserAccount bankUserAccount);
}
