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
        int res = matchDao.selectMatch(travelId1,travelId2);
        if(res>0)
            System.out.println("添加match表失败，已存在");
        else {
            try {
                result = matchDao.addMatch(travelId1, travelId2);
            } catch (Exception e) {
                System.out.println("添加match表失败");
                throw new OtherThingsException(e);
            }
            if (result > 0)
                System.out.println("添加match表成功");
        }
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
    public List<Message> getNewMessage(Integer userId) throws  Exception{
        return matchDao.getNewMessage(userId);
    }


    public List<Message> getMyMessage(Integer userId) throws Exception {

        return matchDao.getMyMessage(userId);

    }
    public void addMessage(Message message) throws  Exception{
        int result = 0; //受影响的行数默认为0
        try {
            result = matchDao.addMessage(message);
        } catch (Exception e) {
            System.out.println("发送消息失败");
            //其他用户添加失败异常
            throw new OtherThingsException(e);
        }
        if (result > 0)
            System.out.println("发送消息成功");
    }

    public int updateMmessage(Integer userId, Integer anotherUserId) throws  Exception{
        int result = 0;
        try{
            result = matchDao.updateMessage(userId, anotherUserId);
        }catch (Exception e){
            System.out.println("查看消息失败");
            //其他用户添加失败异常
            throw new OtherThingsException(e);
        }
        if (result > 0)
            System.out.println("查看消息成功");
        return result;
    }

}
