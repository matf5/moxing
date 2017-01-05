package com.moxing.ssm.service;

import com.moxing.ssm.model.UserInfo;

/**
 * Created by lxx on 2016/12/27.
 */
public interface UserInfoService {

    void add(UserInfo userInfo) throws Exception;

    void update(UserInfo userInfo) throws Exception;
}
