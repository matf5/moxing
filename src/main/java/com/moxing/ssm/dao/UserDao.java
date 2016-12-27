package com.moxing.ssm.dao;

import com.moxing.ssm.model.User;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mtf on 2016/11/30.
 */
public interface UserDao extends Dao<User> {
    int add(User user);

    int del(User user);

    int update(User user);

    User findOneById(Serializable id);

    List<User> findAll();
}
