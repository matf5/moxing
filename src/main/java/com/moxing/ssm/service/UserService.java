package com.moxing.ssm.service;

import com.moxing.ssm.model.User;

/**
 * Created by mtf on 2016/11/30.
 */
public interface UserService {
    void add(User user) throws Exception;
    User findUser(User user) throws Exception;
    User findById(Integer id) throws Exception;

    void resetPwd(User user) throws Exception;
}
