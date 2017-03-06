package com.moxing.ssm.dao;

import com.moxing.ssm.model.Travel;

import java.util.Date;
import java.util.List;

/**
 * Created by lxx on 2017/1/9.
 */
public interface TravelDao {

    int add(Travel travel);

    Travel findByUserId(Integer userId);

    List<Travel> getTraListOfSamePos(Integer userId, String destPosProv, String destPosCity);

    int addBySelective(Travel travel);

    int del(Travel travel);

    int update(Travel travel);

    List<Travel> findAll();

    Travel findById(Integer id);

    int addLike(Integer userId, Integer travelId);

    int ifLike(Integer userId2, Integer travelId1);

    int addMessage(Integer userId1, Integer userId2, String message, Date now);

}
