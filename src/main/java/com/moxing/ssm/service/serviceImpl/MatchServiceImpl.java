package com.moxing.ssm.service.serviceImpl;

import com.moxing.ssm.dao.MatchDao;
import com.moxing.ssm.exception.OtherThingsException;
import com.moxing.ssm.model.UserInfo;
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

    public void addMatch(Integer userId1, Integer userId2) throws Exception {
        int result;
        try {
            result = matchDao.addMatch(userId1, userId2);
        } catch (Exception e) {
            System.out.println("添加match表失败");
            throw new OtherThingsException(e);
        }
        if (result > 0)
            System.out.println("添加match表成功");
    }


    public List<UserInfo> getMatchUserInfo1(Integer userId) throws Exception {

        return matchDao.getMatchUserInfo1(userId);
    }

    public List<UserInfo> getMatchUserInfo2(Integer userId) throws Exception {

        return matchDao.getMatchUserInfo2(userId);
    }
}
