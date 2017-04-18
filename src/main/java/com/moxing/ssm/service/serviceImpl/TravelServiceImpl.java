package com.moxing.ssm.service.serviceImpl;

import com.moxing.ssm.dao.TravelDao;
import com.moxing.ssm.exception.OtherThingsException;
import com.moxing.ssm.model.Travel;
import com.moxing.ssm.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by lxx on 2017/1/7.
 */
@Service("travelService")
public class TravelServiceImpl implements TravelService {
    @Autowired
    private TravelDao travelDao;

    public void add(Travel travel) throws Exception {
        int result; //受影响的行数默认为0
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

    public List<Travel> getTraListOfSamePos(Integer userId, String destPosProv, String destPosCity) throws Exception {
        return travelDao.getTraListOfSamePos(userId, destPosProv, destPosCity);
    }

    public Travel findById(Integer id) throws Exception {
        return travelDao.findById(id);
    }

    public void addLike(Integer userId, Integer travelId) throws Exception {
        int result;
        try {
            result = travelDao.addLike(userId, travelId);
        } catch (Exception e) {
            System.out.println("添加进like表失败");
            throw new OtherThingsException(e);
        }
        if (result > 0)
            System.out.println("添加进like表成功");
    }

    public int ifLike(Integer userId2, Integer travelId1) throws Exception {
        int result;
        try {
            result = travelDao.ifLike(userId2, travelId1);

        } catch (Exception e) {
            System.out.println("匹配like表失败");
            throw new OtherThingsException(e);
        }
        if (result > 0)
            return 1;
        else
            return 0;
    }

    public void addMessage(Integer userId1, Integer userId2, String message, Date now) throws Exception {
        int result;
        try {
            result = travelDao.addMessage(userId1, userId2, message, now);
        } catch (Exception e) {
            System.out.println("添加message失败");
            throw new OtherThingsException(e);
        }
        if (result > 0)
            System.out.println("添加message成功");
    }

    public void updateLike(Integer travelId2) throws Exception {
        int result = 0; //受影响的行数默认为0
        try {
            result = travelDao.updateLike(travelId2);
        } catch (Exception e) {
            System.out.println("更新点赞数失败");

        }
        if (result > 0)
            System.out.println("更新点赞数成功");
    }


    public void updateFriendId(Integer travelId1, Integer userId2) throws Exception {
        int result;
        try {
            result = travelDao.updateFriendId(travelId1, userId2);
        } catch (Exception e) {
            System.out.println("修改friendId失败");
            throw new OtherThingsException(e);
        }
        if (result > 0)
            System.out.println("修改friendId成功");
    }
}
