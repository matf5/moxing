package com.moxing.ssm.service;

import com.moxing.ssm.model.User;

/**
 * Created by mtf on 2016/11/30.
 */
public interface UserService extends BaseService<User> {
    void add(User user) throws Exception;
    User findUser(User user) throws Exception;
}
