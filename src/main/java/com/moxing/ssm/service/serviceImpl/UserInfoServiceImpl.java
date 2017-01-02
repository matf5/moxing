package com.moxing.ssm.service.serviceImpl;

import com.moxing.ssm.dao.UserInfoDao;
import com.moxing.ssm.exception.OtherThingsException;
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
}