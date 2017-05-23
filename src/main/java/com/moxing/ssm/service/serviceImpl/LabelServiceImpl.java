package com.moxing.ssm.service.serviceImpl;

import com.moxing.ssm.dao.LabelDao;
import com.moxing.ssm.model.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by matingfeng on 2017/5/23.
 */
@Service("labelService")
public class LabelServiceImpl {
    @Autowired
    private LabelDao labelDao;
    public Label selectLabel(Integer userId) throws Exception{

        return labelDao.selectByPrimaryKey(userId);
    }
}
