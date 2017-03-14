package com.moxing.ssm.service;

import com.moxing.ssm.model.Travel;

import java.util.Date;
import java.util.List;
/**
 * Created by lxx on 2017/1/7.
 */
public interface TravelService {
    void add(Travel travel) throws Exception;

    Travel findByUserId(Integer userId) throws Exception;

    List<Travel> getTraListOfSamePos(Integer userId, String destPosProv, String destPosCity) throws Exception;

    Travel findById(Integer id) throws Exception;

    void addLike(Integer userId, Integer travelId) throws Exception;

    int ifLike(Integer userId2, Integer travelId1) throws Exception;

    void addMessage(Integer userId1, Integer userId2, String message, Date now) throws Exception;


}
