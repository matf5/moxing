package com.moxing.ssm.mvc.controller;

import com.moxing.ssm.model.Message;
import com.moxing.ssm.model.ResponseObj;
import com.moxing.ssm.model.Travel;
import com.moxing.ssm.service.serviceImpl.MatchServiceImpl;
import com.moxing.ssm.service.serviceImpl.TravelServiceImpl;
import com.moxing.ssm.utils.GsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lxx on 2017/3/13.
 */
@Controller
@RequestMapping("/matchAction")
public class matchController {
    ResponseObj responseObj = new ResponseObj();
    @Autowired
    private MatchServiceImpl matchService;
    @Autowired
    private TravelServiceImpl travelService;    //自动载入Service对象

    //查询联系人
    //post : user_id
    // 从match中查找与自己有关的所有联系人对应的行程
    //返回userinfo的集合-->返回 travel{userinfo，label}
    @RequestMapping(value = "/queryMatch"
            , method = RequestMethod.POST
            , produces = "application/json; charset=utf-8")
    @ResponseBody
    public String queryMatch(HttpServletRequest request, HttpServletResponse response) throws Exception {

        responseObj = new ResponseObj<Travel>();
        Integer userId = Integer.parseInt(request.getParameter("userId"));
        if (userId == null) {
            responseObj.setCode(ResponseObj.EMPTY);
            responseObj.setMsg("用户id不能为空！");
            return new GsonUtils().toJson(responseObj);
        }else {
            try {

                List<Travel> list = new ArrayList<Travel>();
                Integer travelId = travelService.findByUserId(userId).getId();
                list = matchService.getMatchUserInfo1(travelId);
                list.addAll(matchService.getMatchUserInfo2(travelId));
                System.out.println(list);
                responseObj.setCode(ResponseObj.OK);
                responseObj.setMsg("成功返回与自己有关的所有联系人信息！");
                responseObj.setData(list);
                return new GsonUtils().toJson(responseObj);
            } catch (Exception e) {
                e.printStackTrace();
                responseObj.setCode(ResponseObj.FAILED);
                responseObj.setMsg("错误：返回不了与自己有关的所有联系人信息！");
                return new GsonUtils().toJson(responseObj);
            }
        }
    }

    //查询消息
    //post : userId, anotherUserId
    // 从message中查找两人之间的所有消息
    //返回相关消息和时间的集合
    @RequestMapping(value = "/queryMessage"
            , method = RequestMethod.POST
            , produces = "application/json; charset=utf-8")
    @ResponseBody
    public String queryMessage(HttpServletRequest request, HttpServletResponse response) throws Exception {

        responseObj = new ResponseObj<Message>();
        Integer userId = Integer.parseInt(request.getParameter("userId"));
        Integer anotherUserId = Integer.parseInt(request.getParameter("anotherUserId"));
        if (userId == null || anotherUserId == null) {
            responseObj.setCode(ResponseObj.EMPTY);
            responseObj.setMsg("用户id不能为空！");
            return new GsonUtils().toJson(responseObj);
        }else {

            try {

                List<Message> list = new ArrayList<Message>();
                list = matchService.getMessage(userId, anotherUserId);
                responseObj.setCode(ResponseObj.OK);
                responseObj.setMsg("成功返回与自己有关的所有联系人信息！");
                responseObj.setData(list);
                return new GsonUtils().toJson(responseObj);
            } catch (Exception e) {
                e.printStackTrace();
                responseObj.setCode(ResponseObj.FAILED);
                responseObj.setMsg("错误：返回不了与自己有关的所有联系人信息！");
                return new GsonUtils().toJson(responseObj);
            }
        }
    }

}
