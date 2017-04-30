package com.moxing.ssm.service.serviceImpl;

import com.moxing.ssm.dao.UserInfoDao;
import com.moxing.ssm.exception.OtherThingsException;
import com.moxing.ssm.model.Label;
import com.moxing.ssm.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lxx on 2016/12/27.
 */
@Service("userInfoService")
public class UserInfoServiceImpl {
    @Autowired
    private UserInfoDao userInfoDao;
    public void add(UserInfo userInfo) throws Exception {
        int result = 0; //受影响的行数默认为0
        try {
            result = userInfoDao.insertSelective(userInfo);
        } catch (Exception e) {
            System.out.println("修改资料失败");
            //其他用户添加失败异常
            throw new OtherThingsException(e);
        }
        if (result > 0)
            System.out.println("添加资料成功");
    }


    public void updateUserInfo(UserInfo userInfo) throws Exception {
        int result = 0; //受影响的行数默认为0
        try {
            result = userInfoDao.updateByPrimaryKeySelective(userInfo);
        } catch (Exception e) {
            System.out.println("更新资料失败");

        }
        if (result > 0)
            System.out.println("更新资料成功");
    }

    public void insertLabel(Label label) throws Exception {
        int result = 0; //受影响的行数默认为0
        try {
           // result = userInfoDao.insertLabel(label);
        } catch (Exception e) {
            System.out.println("更新标签失败");

        }
        if (result > 0)
            System.out.println("更新标签成功");
    }

    public UserInfo getUserInfo(Integer userId) throws Exception {

        return userInfoDao.getUserInfo(userId);
    }
}
