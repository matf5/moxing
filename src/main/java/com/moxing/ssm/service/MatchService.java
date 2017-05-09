package com.moxing.ssm.service;

import com.moxing.ssm.model.Message;
import com.moxing.ssm.model.Travel;

import java.util.List;

/**
 * Created by lxx on 2017/3/13.
 */
public interface MatchService {

    void addMatch(Integer travelId1, Integer travelId2) throws Exception;

    List<Travel> getMatchUserInfo1(Integer travelId) throws Exception;

    List<Travel> getMatchUserInfo2(Integer travelId) throws Exception;

    List<Message> getMessage(Integer userId, Integer anotherUserId) throws Exception;

    List<Message> getMyMessage(Integer userId) throws Exception;

    void addMessage(Message message) throws Exception;
}
