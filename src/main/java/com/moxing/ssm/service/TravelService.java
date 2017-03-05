package com.moxing.ssm.service;

import com.moxing.ssm.model.Travel;
import java.util.List;
/**
 * Created by lxx on 2017/1/7.
 */
public interface TravelService {
    void add(Travel travel) throws Exception;

    Travel findByUserId(Integer userId) throws Exception;

    List<Travel> getTraListOfSamePos(String destPosProv, String destPosCity) throws Exception;

}
