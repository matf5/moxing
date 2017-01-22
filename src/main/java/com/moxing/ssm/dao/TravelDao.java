package com.moxing.ssm.dao;

import com.moxing.ssm.model.Travel;

import java.util.List;

/**
 * Created by lxx on 2017/1/9.
 */
public interface TravelDao {

    int add(Travel travel);

    int addBySelective(Travel travel);

    int del(Travel travel);

    int update(Travel travel);

    List<Travel> findAll();
}
