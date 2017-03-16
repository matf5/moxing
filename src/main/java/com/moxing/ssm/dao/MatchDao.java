package com.moxing.ssm.dao;

import com.moxing.ssm.model.Message;
import com.moxing.ssm.model.UserInfo;

import java.util.List;

/**
 * Created by lxx on 2017/3/13.
 */
public interface MatchDao {

    int addMatch(Integer userId1, Integer userId2);

    List<UserInfo> getMatchUserInfo1(Integer userId);

    List<UserInfo> getMatchUserInfo2(Integer userId);

    List<Message> getMessage(Integer userId, Integer anotherUserId);

}
