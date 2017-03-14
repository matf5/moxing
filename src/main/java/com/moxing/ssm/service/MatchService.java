package com.moxing.ssm.service;

import com.moxing.ssm.model.UserInfo;

import java.util.List;

/**
 * Created by lxx on 2017/3/13.
 */
public interface MatchService {

    void addMatch(Integer userId1, Integer userId2) throws Exception;

    List<UserInfo> getMatchUserInfo1(Integer userId) throws Exception;

    List<UserInfo> getMatchUserInfo2(Integer userId) throws Exception;
}
