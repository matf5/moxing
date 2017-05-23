package com.moxing.ssm.dao;

import com.moxing.ssm.model.Label;

public interface LabelDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Label record);

    int insertSelective(Label record);

    Label selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(Label record);

    int updateByPrimaryKey(Label record);
}