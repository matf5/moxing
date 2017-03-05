package com.moxing.ssm.dao;

import com.moxing.ssm.model.Travel;

import java.util.List;

/**
 * Created by lxx on 2017/1/9.
 */
public interface TravelDao {

    int add(Travel travel);

    Travel findByUserId(Integer userId);

    List<Travel> getTraListOfSamePos(String destPosProv, String destPosCity);

    int addBySelective(Travel travel);

    int del(Travel travel);

    int update(Travel travel);

    List<Travel> findAll();
}
