package com.moxing.ssm.mvc.controller;

import com.moxing.ssm.model.ResponseObj;
import com.moxing.ssm.model.Travel;
import com.moxing.ssm.service.serviceImpl.MatchServiceImpl;
import com.moxing.ssm.service.serviceImpl.TravelServiceImpl;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lxx on 2017/1/6.
 */
@Controller
@RequestMapping("/travelAction")
public class TravelController {

    ResponseObj responseObj = new ResponseObj();
    static final String message = "我们的旅行匹配到一起啦，来聊聊吧";
    @Autowired
    private TravelServiceImpl travelService;    //自动载入Service对象
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private MatchServiceImpl matchService;

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
            responseObj.setMsg("添加行程成功  ！");
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
    //请求数据：userId
    // 返回数据：不同userId、相同目的地的未匹配未过期的travel
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
                List<Travel> list = new ArrayList<Travel>();
                try {

                    list = travelService.getTraListOfSamePos(userId, destProv_temp, destCity_temp);
                } catch (Exception e) {
                    e.printStackTrace();
                    responseObj.setCode(ResponseObj.FAILED);
                    responseObj.setMsg("错误：查询不到相同目的地的未过期行程！");
                    return new GsonUtils().toJson(responseObj);
                }

                responseObj.setCode(ResponseObj.OK);
                responseObj.setMsg("成功返回相同目的地的未过期行程！");
                responseObj.setData(list);
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

        //System.out.println("!!!!!!!!!"+request.getParameter("userId"));
        //System.out.println("~~~~~~~~~"+request.getParameter("travelId"));
        Integer userId1 = Integer.parseInt(request.getParameter("userId"));
        Integer travelId2 = Integer.parseInt(request.getParameter("travelId"));
        if(userId1 == null || travelId2 == null) {
            responseObj.setCode(ResponseObj.EMPTY);
            responseObj.setMsg("用户id或点赞的行程id不能为空！");
            return new GsonUtils().toJson(responseObj);
        }

        if (userService.findById(userId1) == null || travelService.findById(travelId2) == null) {
            responseObj.setCode(ResponseObj.FAILED);
            responseObj.setMsg("用户id或点赞的行程id不存在！");
            return new GsonUtils().toJson(responseObj);
        }
        //将userId1和travelId2添加进like表
        travelService.addLike(userId1, travelId2);
        //查找userId1发布的未过期的travelId，判断是否被travelId2对应的userId2点赞过，
        //是，匹配成功；否，只返回点赞成功的json
        Integer travelId1 = travelService.findByUserId(userId1).getId();
        Integer userId2 = travelService.findById(travelId2).getUserId();
        if (travelService.ifLike(userId2, travelId1) == 0) {
            //匹配不成功
            responseObj.setCode(ResponseObj.OK);
            responseObj.setMsg("点赞成功！");
            return new GsonUtils().toJson(responseObj);
        } else {
            //匹配成功
            //添加1条message记录
            //添加用户1和2进match表
            //Date now = new Date();
            //SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            //String time_str = sdf.format(now);
            travelService.addMessage(userId1, userId2, message, new Date());
            matchService.addMatch(userId1, userId2);
            //travelService.addMessage(userId2, userId1, message, new Date());
            responseObj.setCode(ResponseObj.OK);
            responseObj.setMsg("匹配成功！");
            return new GsonUtils().toJson(responseObj);
        }
    }

}
