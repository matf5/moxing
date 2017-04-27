package com.moxing.ssm.service.serviceImpl;

import com.moxing.ssm.dao.UserDao;
import com.moxing.ssm.model.User;
import com.moxing.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mtf on 2016/11/30.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    public void add(User user) throws Exception {
        int result = 0; //受影响的行数默认为0
        try {
            result = userDao.add(user);
        } catch (Exception e) {
            System.out.println("添加用户失败,用户已经存在");
            //其他用户添加失败异常
            //throw new OtherThingsException(e);
        }
        if (result > 0)
            System.out.println("添加用户成功");
    }

    public User findUser(User user) throws Exception {
        return userDao.findOneByPhoneNum(user.getPhoneNum());
    }

    public User findById(Integer id) throws Exception {
        return userDao.findById(id);
    }

    public void resetPwd(User user) throws Exception {
        int result = 0; //受影响的行数默认为0
        try {
            result = userDao.resetPwd(user);
        } catch (Exception e) {
            System.out.println("重设密码失败");
            //其他用户添加失败异常
            //throw new OtherThingsException(e);
        }
        if (result > 0)
            System.out.println("重设密码失败");
    }
}
