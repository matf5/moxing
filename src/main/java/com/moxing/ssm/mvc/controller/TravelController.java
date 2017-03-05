package com.moxing.ssm.mvc.controller;

import com.moxing.ssm.model.ResponseObj;
import com.moxing.ssm.model.Travel;
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
import java.util.ArrayList;
import java.util.List;

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
    public String pub(HttpServletRequest request, HttpServletResponse response, Travel travel) throws Exception {

        responseObj = new ResponseObj<Travel>();
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
            responseObj.setMsg("添加行程成功！");
            return new GsonUtils().toJson(responseObj);
        }

    }

    //判断用户是否有发布未完成的行程
    //请求数据：userId
    //返回数据：0-没有未完成行程 1-有未完成行程
    @RequestMapping(value = "/isPubTraExpired"
            , method = RequestMethod.POST
            , produces = "application/json; charset=utf-8")
    @ResponseBody
    public Boolean isPubTraExpired(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Integer userId = Integer.parseInt(request.getParameter("userId"));
        if (userId == null) {
            return false;
        } else {
            if (travelService.findByUserId(userId) != null) {
                return true;
            } else {
                return false;
            }
        }
    }

    //根据用户id返回对应的未过期的行程id
    @RequestMapping(value = "/getTravelId"
            , method = RequestMethod.POST
            , produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getTravelId(HttpServletRequest request, HttpServletResponse response) throws Exception {

        responseObj = new ResponseObj<Travel>();
        Integer userId = Integer.parseInt(request.getParameter("userId"));
        if (userId == null) {
            responseObj.setCode(ResponseObj.EMPTY);
            responseObj.setMsg("用户id不能为空！");
            return new GsonUtils().toJson(responseObj);
        } else {
            try {
                responseObj.setData(travelService.findByUserId(userId).getId());
            } catch (Exception e) {
                e.printStackTrace();
                responseObj.setCode(ResponseObj.FAILED);
                responseObj.setMsg("错误：查询不到用户发布的行程！");
                return new GsonUtils().toJson(responseObj);

            }
            responseObj.setCode(ResponseObj.OK);
            responseObj.setMsg("成功获取用户发布的行程！");
            return new GsonUtils().toJson(responseObj);
        }
    }

    //根据用户发布的行程返回对应匹配相同目的地的行程
    //请求数据：userId 返回数据：相同目的地的travel
    //！！还没测试
    @RequestMapping(value = "/show"
            , method = RequestMethod.POST
            , produces = "application/json; charset=utf-8")
    @ResponseBody
    public String show(HttpServletRequest request, HttpServletResponse response) throws Exception {

        responseObj = new ResponseObj<Travel>();
        Integer userId = Integer.parseInt(request.getParameter("userId"));
        if (userId == null) {
            responseObj.setCode(ResponseObj.EMPTY);
            responseObj.setMsg("用户id不能为空！");
            return new GsonUtils().toJson(responseObj);
        } else {
            try {
                Travel travel1 = travelService.findByUserId(userId);
                String destProv_temp = travel1.getDestPosProv();
                String destCity_temp = travel1.getDestPosCity();
                try {
                    List<Travel> list = new ArrayList<Travel>();
                    list = travelService.getTraListOfSamePos(destProv_temp, destCity_temp);
                } catch (Exception e) {
                    e.printStackTrace();
                    responseObj.setCode(ResponseObj.FAILED);
                    responseObj.setMsg("错误：查询不到相同目的地的未过期行程！");
                    return new GsonUtils().toJson(responseObj);
                }

                responseObj.setCode(ResponseObj.OK);
                responseObj.setMsg("成功返回相同目的地的未过期行程！");
                return new GsonUtils().toJson(responseObj);
            } catch (Exception e) {
                e.printStackTrace();
                responseObj.setCode(ResponseObj.FAILED);
                responseObj.setMsg("错误：查询不到用户发布的行程！");
                return new GsonUtils().toJson(responseObj);
            }
        }

    }

    //点赞接口
    //请求数据：userId1 travelId2
    //点赞成功：将userId1和travelId2插入like表，返回点赞者userId1和被点赞者userId2
    @RequestMapping(value = "/like"
            , method = RequestMethod.POST
            , produces = "application/json; charset=utf-8")
    @ResponseBody
    public String like(HttpServletRequest request, HttpServletResponse response) throws Exception {

        if(request.getParameter("userId") == null || request.getParameter("travelId") == null) {
            responseObj.setCode(ResponseObj.EMPTY);
            responseObj.setMsg("用户id或点赞的行程id不能为空！");
            return new GsonUtils().toJson(responseObj);
        }
        Integer userId1 = Integer.parseInt(request.getParameter("userId"));
        Integer travelId2 = Integer.parseInt(request.getParameter("travelId"));

    }
}
