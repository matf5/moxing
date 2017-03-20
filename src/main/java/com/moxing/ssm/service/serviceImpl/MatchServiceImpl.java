package com.moxing.ssm.service.serviceImpl;

import com.moxing.ssm.dao.MatchDao;
import com.moxing.ssm.dao.TravelDao;
import com.moxing.ssm.exception.OtherThingsException;
import com.moxing.ssm.model.Message;
import com.moxing.ssm.model.Travel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lxx on 2017/3/13.
 */
@Service("matchService")
public class MatchServiceImpl {
    @Autowired
    private MatchDao matchDao;
    @Autowired
    private TravelDao travelDao;

    public void addMatch(Integer travelId1, Integer travelId2) throws Exception {
        int result;
        try {
            result = matchDao.addMatch(travelId1, travelId2);
        } catch (Exception e) {
            System.out.println("添加match表失败");
            throw new OtherThingsException(e);
        }
        if (result > 0)
            System.out.println("添加match表成功");
    }


    public List<Travel> getMatchUserInfo1(Integer travelId) throws Exception {

        return matchDao.getMatchUserInfo1(travelId);
    }

    public List<Travel> getMatchUserInfo2(Integer travelId) throws Exception {

        return matchDao.getMatchUserInfo2(travelId);
    }

    public List<Message> getMessage(Integer userId, Integer anotherUserId) throws Exception {

        return matchDao.getMessage(userId, anotherUserId);
    }


}
