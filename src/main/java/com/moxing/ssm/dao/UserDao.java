package com.moxing.ssm.dao;

import com.moxing.ssm.model.User;

import java.util.List;

/**
 * Created by mtf on 2016/11/30.
 */
public interface UserDao extends Dao<User> {
    int add(User user);

    int del(User user);

    int update(User user);

    User findOneByPhoneNum(String phoneNum);

    List<User> findAll();

    User findById(Integer id);

    int resetPwd(User user);
}
