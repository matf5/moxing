package com.moxing.ssm.service;

import com.moxing.ssm.model.Label;

/**
 * Created by matingfeng on 2017/5/23.
 */
public interface LabelService {
    Label selectLabel(Integer userId)  throws Exception;
}
