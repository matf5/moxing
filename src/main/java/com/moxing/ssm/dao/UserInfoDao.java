package com.moxing.ssm.dao;

import com.moxing.ssm.model.Label;
import com.moxing.ssm.model.UserInfo;

/**
 * Created by lxx on 2016/12/27.
 */
public interface UserInfoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    int insertLabel(Label label);
}
