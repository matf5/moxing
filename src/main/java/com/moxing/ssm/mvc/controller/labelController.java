package com.moxing.ssm.mvc.controller;

import com.moxing.ssm.model.Label;
import com.moxing.ssm.model.ResponseObj;
import com.moxing.ssm.service.serviceImpl.LabelServiceImpl;
import com.moxing.ssm.service.serviceImpl.TravelServiceImpl;
import com.moxing.ssm.utils.CalUtils;
import com.moxing.ssm.utils.GsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by matingfeng on 2017/5/23.
 */
@Controller
@RequestMapping("/labelAction")
public class labelController {
    ResponseObj responseObj;
    @Autowired
    private LabelServiceImpl labelService;

    @RequestMapping(value = "/calLabel"
            , method = RequestMethod.POST
            , produces = "application/json; charset=utf-8")
    @ResponseBody
    public String calLabel(HttpServletRequest request, HttpServletResponse response) throws Exception {

        responseObj = new ResponseObj<String>();
        Integer userId = Integer.parseInt(request.getParameter("userId"));
        Integer anotherUserId = Integer.parseInt(request.getParameter("anotherUserId"));
        try {
            Label label1 = labelService.selectLabel(userId);
            Label label2 = labelService.selectLabel(anotherUserId);
            responseObj.setCode(ResponseObj.OK);
            responseObj.setData(CalUtils.getCal(label1,label2));

        } catch (Exception e) {
            e.printStackTrace();
            responseObj.setCode(ResponseObj.FAILED);
            responseObj.setMsg("错误：返回不了与自己有关的所有联系人信息！");
            return new GsonUtils().toJson(responseObj);
        }
        return new GsonUtils().toJson(responseObj);
    }
}
