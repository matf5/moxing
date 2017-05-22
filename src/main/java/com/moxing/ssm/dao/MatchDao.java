package com.moxing.ssm.dao;

import com.moxing.ssm.model.Message;
import com.moxing.ssm.model.Travel;

import java.util.List;

/**
 * Created by lxx on 2017/3/13.
 */
public interface MatchDao {

    int selectMatch(Integer travelId1, Integer travelId2);

    int addMatch(Integer travelId1, Integer travelId2);

    List<Travel> getMatchUserInfo1(Integer travelId);

    List<Travel> getMatchUserInfo2(Integer travelId);

    List<Message> getMessage(Integer userId, Integer anotherUserId);

    List<Message> getMyMessage(Integer userId);

    int addMessage(Message message);

    int updateMessage(Integer userId, Integer anotherUserId);

    List<Message> getNewMessage(Integer userId);

}
