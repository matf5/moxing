package com.moxing.ssm.mvc.controller;

import com.moxing.ssm.model.ResponseObj;
import com.moxing.ssm.model.UserInfo;
import com.moxing.ssm.service.serviceImpl.MatchServiceImpl;
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

    //查询联系人
    //post : user_id
    // 从match中查找与自己有关的所有联系人
    //返回userinfo的集合
    @RequestMapping(value = "/queryMatch"
            , method = RequestMethod.POST
            , produces = "application/json; charset=utf-8")
    @ResponseBody
    public String query(HttpServletRequest request, HttpServletResponse response) throws Exception {

        responseObj = new ResponseObj<UserInfo>();
        Integer userId = Integer.parseInt(request.getParameter("userId"));
        if (userId == null) {
            responseObj.setCode(ResponseObj.EMPTY);
            responseObj.setMsg("用户id不能为空！");
            return new GsonUtils().toJson(responseObj);
        }else {
            try {

                List<UserInfo> list = new ArrayList<UserInfo>();
                list = matchService.getMatchUserInfo1(userId);
                list.addAll(matchService.getMatchUserInfo2(userId));
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
