package com.moxing.ssm.mvc.controller;


import com.moxing.ssm.model.ResponseObj;
import com.moxing.ssm.model.User;
import com.moxing.ssm.service.serviceImpl.UserServiceImpl;
import com.moxing.ssm.utils.GsonUtils;
import com.moxing.ssm.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 用户请求相关控制器
 * <br/>Created by acheng on 2016/9/26.
 */
@Controller
@RequestMapping("/userAction")
public class UserController {

    @Autowired
    private UserServiceImpl userService;    //自动载入Service对象
    private ResponseObj responseObj;

    /**
     * @Author:mtf
     * @description：注册接口
     * @Date：10:31 2016/12/6
     */
   //注册接口
    @RequestMapping(value = "/reg"
            , method = RequestMethod.POST
            , produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object reg(HttpServletRequest request, HttpServletResponse response, User user, HttpSession session) throws Exception {

        Object result;
        responseObj = new ResponseObj<User>();
        if (null == user) {
            responseObj.setCode(ResponseObj.FAILED);
            responseObj.setMsg("用户信息不能为空！");
            result = new GsonUtils().toJson(responseObj);
            return result;
        }
        if (StringUtils.isEmpty(user.getPhonenum()) || StringUtils.isEmpty(user.getPassword())) {
            responseObj.setCode(ResponseObj.FAILED);
            responseObj.setMsg("用户名或密码不能为空！");
            result = new GsonUtils().toJson(responseObj);
            return result;
        }
        if (null != userService.findUser(user)) {
            responseObj.setCode(ResponseObj.FAILED);
            responseObj.setMsg("用户已经存在！");
            result = new GsonUtils().toJson(responseObj);
            return result;
        }
        try {
            userService.add(user);
        } catch (Exception e) {
            e.printStackTrace();
            responseObj.setCode(ResponseObj.FAILED);
            responseObj.setMsg("其他错误！");
            result = new GsonUtils().toJson(responseObj);
            return result;
        }
        responseObj.setCode(ResponseObj.OK);
        responseObj.setMsg("注册成功");
        //user.setPassword(session.getId());   //单独设置密码为sessionId 误导黑客，前端访问服务器的时候必须有这个信息才能操作
        //user.setNextUrl(request.getContextPath() + "/mvc/home");    //单独控制地址
        //responseObj.setData(user);
        //session.setAttribute("userInfo", user); //将一些基本信息写入到session中
        result = new GsonUtils().toJson(responseObj);
        return result;
    }

    /**
     * @Author:mtf
     * @description：登录接口
     * @Date：10:31 2016/12/6
     */
    //登录接口
    @RequestMapping(value = "/login"
            , method = RequestMethod.POST
            , produces = "application/json; charset=utf-8")
    @ResponseBody

    public Object login(HttpServletRequest request, HttpServletResponse response, User user, HttpSession session) throws Exception {
        Object result;
        if (null == user) {
            responseObj = new ResponseObj<User>();
            responseObj.setCode(ResponseObj.EMPUTY);
            responseObj.setMsg("登录信息不能为空");
            result = new GsonUtils().toJson(responseObj);
            return result; //返回json
        }
        if (StringUtils.isEmpty(user.getPhonenum()) || StringUtils.isEmpty(user.getPassword())) {
            responseObj = new ResponseObj<User>();
            responseObj.setCode(ResponseObj.FAILED);
            responseObj.setMsg("用户名或密码不能为空");
            result = new GsonUtils().toJson(responseObj);
            return result;
        }
        //查找用户
        User user1 = userService.findUser(user);
        if (null == user1) {
            responseObj = new ResponseObj<User>();
            responseObj.setCode(ResponseObj.EMPUTY);
            responseObj.setMsg("未找到该用户");
            result = new GsonUtils().toJson(responseObj);
        } else {
            if (user.getPassword().equals(user1.getPassword())) {
                user1.setPassword(session.getId());
               // user1.setNextUrl(request.getContextPath() + "/mvc/home");
                responseObj = new ResponseObj<User>();
                responseObj.setCode(ResponseObj.OK);
                responseObj.setMsg(ResponseObj.OK_STR);
                //responseObj.setData(user1);
                //session.setAttribute("userInfo", user1);
                result = new GsonUtils().toJson(responseObj);
            } else {
                responseObj = new ResponseObj<User>();
                responseObj.setCode(ResponseObj.FAILED);
                responseObj.setMsg("用户密码错误");
                result = new GsonUtils().toJson(responseObj);
            }
        }
        return result;
    }

}
