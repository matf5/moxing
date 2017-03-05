package com.moxing.ssm.mvc.controller;


import com.moxing.ssm.model.ResponseObj;
import com.moxing.ssm.model.User;
import com.moxing.ssm.model.UserInfo;
import com.moxing.ssm.service.serviceImpl.UserInfoServiceImpl;
import com.moxing.ssm.service.serviceImpl.UserServiceImpl;
import com.moxing.ssm.utils.GsonUtils;
import com.moxing.ssm.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户请求相关控制器
 */
@Controller
@RequestMapping("/userAction")
public class UserController {

    @Autowired
    private UserServiceImpl userService;    //自动载入Service对象
    private ResponseObj responseObj;
    @Autowired
    private UserInfoServiceImpl userInfoService;
    static final String imgUrl0 = "www.taobao.com";
    static final String posProv0 = "北京";
    static final String posCity0 = "北京";
    /**
     * @Author:lxx
     * @description：注册接口
     * @Date：10:31 2016/12/26
     */
   //注册接口
    @RequestMapping(value = "/reg"
            , method = RequestMethod.POST
            , produces = "application/json; charset=utf-8")
    @ResponseBody
    public String reg(HttpServletRequest request, HttpServletResponse response, User user) throws Exception {


        responseObj = new ResponseObj<User>();
        if (user == null) {
            responseObj.setCode(ResponseObj.EMPTY);
            responseObj.setMsg("用户信息不能为空！");
            return new GsonUtils().toJson(responseObj);
        }
        if (StringUtils.isEmpty(user.getPhoneNum()) || StringUtils.isEmpty(user.getPassword())) {
            responseObj.setCode(ResponseObj.EMPTY);
            responseObj.setMsg("用户名或密码不能为空！");
            return new GsonUtils().toJson(responseObj);
        }
        if (userService.findUser(user) != null) {
            responseObj.setCode(ResponseObj.FAILED);
            responseObj.setMsg("用户已经存在！");
            return new GsonUtils().toJson(responseObj);
        }
        try {
            userService.add(user);
        } catch (Exception e) {
            e.printStackTrace();
            responseObj.setCode(ResponseObj.FAILED);
            responseObj.setMsg("其他错误！");
            return new GsonUtils().toJson(responseObj);

        }
        responseObj.setCode(ResponseObj.OK);
        responseObj.setMsg("注册成功");
        User user1 = userService.findUser(user);
        UserInfo userinfo =new UserInfo();
        userinfo.setUserId(user1.getId());
        userinfo.setPhoneNum(user1.getPhoneNum());
        //userinfo.setNickname("扑母");
        userinfo.setHeadimgUrl(imgUrl0);
        userinfo.setPosCity(posCity0);
        userinfo.setPosProv(posProv0);
        userInfoService.add(userinfo);

        return new GsonUtils().toJson(responseObj);
    }

    /**
     * @Author:lxx
     * @description：登录接口
     * @Date：15:32 2016/12/26
     */
    //登录接口
    @RequestMapping(value = "/login"
            , method = RequestMethod.POST
            , produces = "application/json; charset=utf-8")
    @ResponseBody

    public String login(HttpServletRequest request, HttpServletResponse response, User user) throws Exception {
        if (user == null) {
            responseObj = new ResponseObj<User>();
            responseObj.setCode(ResponseObj.EMPTY);
            responseObj.setMsg("登录信息不能为空");
            return new GsonUtils().toJson(responseObj); //返回json
        }
        if (StringUtils.isEmpty(user.getPhoneNum()) || StringUtils.isEmpty(user.getPassword())) {
            responseObj = new ResponseObj<User>();
            responseObj.setCode(ResponseObj.FAILED);
            responseObj.setMsg("用户名或密码不能为空");
            return new GsonUtils().toJson(responseObj);
        }
        //查找用户
        User user1 = userService.findUser(user);
        if (user1 == null) {
            responseObj = new ResponseObj<User>();
            responseObj.setCode(ResponseObj.EMPTY);
            responseObj.setMsg("未找到该用户");

        } else {
            if (user.getPassword().equals(user1.getPassword())) {
                responseObj = new ResponseObj<User>();
                responseObj.setCode(ResponseObj.OK);
                responseObj.setMsg(ResponseObj.OK_STR);
                Map<String, Object> resultMap = new HashMap<String, Object>();
                resultMap.put("userId", user1.getId());
                responseObj.setData(resultMap);

            } else {
                responseObj = new ResponseObj<User>();
                responseObj.setCode(ResponseObj.FAILED);
                responseObj.setMsg("用户密码错误");
            }
        }
        return new GsonUtils().toJson(responseObj);
    }

}
