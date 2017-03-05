package com.moxing.ssm.service.serviceImpl;

import com.moxing.ssm.dao.TravelDao;
import com.moxing.ssm.exception.OtherThingsException;
import com.moxing.ssm.model.Travel;
import com.moxing.ssm.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lxx on 2017/1/7.
 */
@Service("travelService")
public class TravelServiceImpl implements TravelService {
    @Autowired
    private TravelDao travelDao;

    public void add(Travel travel) throws Exception {
        int result = 0; //受影响的行数默认为0
        try {
            result = travelDao.add(travel);
        } catch (Exception e) {
            System.out.println("添加行程失败");
            throw new OtherThingsException(e);
        }
        if (result > 0)
            System.out.println("添加行程成功");
    }

    public Travel findByUserId(Integer userId) throws Exception {
        return travelDao.findByUserId(userId);
    }

    public List<Travel> getTraListOfSamePos(String destPosProv, String destPosCity) throws Exception {
        return travelDao.getTraListOfSamePos(destPosProv, destPosCity);
    }
}
