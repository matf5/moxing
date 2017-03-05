package com.moxing.ssm.mvc.controller;

import com.moxing.ssm.model.ResponseObj;
import com.moxing.ssm.model.Travel;
import com.moxing.ssm.model.User;
import com.moxing.ssm.service.serviceImpl.TravelServiceImpl;
import com.moxing.ssm.utils.GsonUtils;
import com.moxing.ssm.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lxx on 2017/1/6.
 */
@Controller
@RequestMapping("/travelAction")
public class TravelController {

    ResponseObj responseObj = new ResponseObj();
    @Autowired
    private TravelServiceImpl travelService;    //自动载入Service对象

    @RequestMapping(value = "/publish"
            , method = RequestMethod.POST
            , produces = "application/json; charset=utf-8")
    @ResponseBody
    public String reg(HttpServletRequest request, HttpServletResponse response, Travel travel) throws Exception {

        responseObj = new ResponseObj<User>();
        if (travel == null) {
            responseObj.setCode(ResponseObj.EMPTY);
            responseObj.setMsg("发布行程不能为空！");
            return new GsonUtils().toJson(responseObj);
        }
        if(StringUtils.isEmpty(travel.getTitle())) {
            responseObj.setCode(ResponseObj.EMPTY);
            responseObj.setMsg("行程标题不能为空！");
            return new GsonUtils().toJson(responseObj);
        } else if(travel.getBeginDate() == null) {
            responseObj.setCode(ResponseObj.EMPTY);
            responseObj.setMsg("开始时间不能为空！");
            return new GsonUtils().toJson(responseObj);
        } else if(travel.getEndDate() == null) {
            responseObj.setCode(ResponseObj.EMPTY);
            responseObj.setMsg("结束时间不能为空！");
            return new GsonUtils().toJson(responseObj);
        } else if(StringUtils.isEmpty(travel.getBeginPosCity())) {
            responseObj.setCode(ResponseObj.EMPTY);
            responseObj.setMsg("出发城市不能为空！");
            return new GsonUtils().toJson(responseObj);
        } else if(StringUtils.isEmpty(travel.getBeginPosProv())) {
            responseObj.setCode(ResponseObj.EMPTY);
            responseObj.setMsg("出发省份不能为空！");
            return new GsonUtils().toJson(responseObj);
        } else if(StringUtils.isEmpty(travel.getDestPosCity())) {
            responseObj.setCode(ResponseObj.EMPTY);
            responseObj.setMsg("目的城市不能为空！");
            return new GsonUtils().toJson(responseObj);
        } else if(StringUtils.isEmpty(travel.getDestPosProv())) {
            responseObj.setCode(ResponseObj.EMPTY);
            responseObj.setMsg("目的省份不能为空！");
            return new GsonUtils().toJson(responseObj);
        } else if(StringUtils.isEmpty(travel.getImgUrl())) {
            responseObj.setCode(ResponseObj.EMPTY);
            responseObj.setMsg("配图不能为空！");
            return new GsonUtils().toJson(responseObj);
        } else if(StringUtils.isEmpty(travel.getDescription())) {
            responseObj.setCode(ResponseObj.EMPTY);
            responseObj.setMsg("描述不能为空！");
            return new GsonUtils().toJson(responseObj);
        } else if(StringUtils.isEmpty(travel.getLatitude()) || StringUtils.isEmpty(travel.getLongitude())) {
            responseObj.setCode(ResponseObj.EMPTY);
            responseObj.setMsg("定位不能为空！");
            return new GsonUtils().toJson(responseObj);
        } else {
            try {
                travelService.add(travel);
            } catch (Exception e) {
                e.printStackTrace();
                responseObj.setCode(ResponseObj.FAILED);
                responseObj.setMsg("其他错误！");
                return new GsonUtils().toJson(responseObj);

            }
            responseObj.setCode(ResponseObj.OK);
            responseObj.setMsg("添加行程成功  ！");
            return new GsonUtils().toJson(responseObj);
        }

    }
}
