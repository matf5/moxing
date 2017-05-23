package com.moxing.ssm.mvc.controller;


import com.google.gson.Gson;
import com.moxing.ssm.model.CosJsonBean;
import com.moxing.ssm.model.Label;
import com.moxing.ssm.model.ResponseObj;
import com.moxing.ssm.model.UserInfo;
import com.moxing.ssm.service.serviceImpl.UserInfoServiceImpl;
import com.moxing.ssm.utils.GsonUtils;
import com.moxing.ssm.utils.MyCos;
import com.qcloud.cos.request.UploadFileRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by lxx on 2016/12/29.
 */
@Controller
@RequestMapping("/userInfoAction")
public class UserInfoController {

    private ResponseObj responseObj;
    @Autowired
    private UserInfoServiceImpl userInfoService;    //自动载入Service对象

    private Integer eScore = 0;
    private Integer aScore = 0;
    private Integer cScore = 0;

    @RequestMapping(value = "/uploadHeadImg"
            , method = RequestMethod.POST
            , produces = "application/json; charset=utf-8")
    @ResponseBody
    public String uploadHeadImg(HttpServletRequest request) throws IOException {
            String img = request.getParameter("img");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            String preName = simpleDateFormat.format(new Date());
            StringBuilder stringBuilder = new StringBuilder(preName);


            Random random = new Random();
            for(int i=0; i<10; i++){
               stringBuilder.append(random.nextInt(10));
            }
            preName = stringBuilder.toString();
            String imgFileName=preName+".jpg";

            byte[] butter = new BASE64Decoder().decodeBuffer(img);
       // InputStream inputStream = new ByteArrayInputStream(new BASE64Decoder().decodeBuffer(img));
            responseObj = new ResponseObj<String>();

            UploadFileRequest uploadFileRequest = new UploadFileRequest(MyCos.getBucketName(),"/headImg"+imgFileName,butter);
            String uploadFileRet = MyCos.getClient().uploadFile(uploadFileRequest);
            CosJsonBean cosJsonBean = new Gson().fromJson(uploadFileRet,CosJsonBean.class);
            System.out.println(uploadFileRet);
            responseObj.setCode(ResponseObj.OK);
            responseObj.setData(cosJsonBean.getData().getAccess_url());
            responseObj.setMsg("上传头像成功");


            return new GsonUtils().toJson(responseObj);
    }

    @RequestMapping(value = "/updateUserInfo"
            , method = RequestMethod.POST
            , produces = "application/json; charset=utf-8")
    @ResponseBody
    public String UserInfoUpdate(HttpServletRequest request, HttpServletResponse response, UserInfo userInfo) throws Exception {
        responseObj = new ResponseObj<UserInfo>();
        if (userInfo == null) {
            responseObj.setCode(ResponseObj.EMPTY);
            responseObj.setMsg("上传资料不能为空！");
            return new GsonUtils().toJson(responseObj);
        }
        try {
            userInfoService.updateUserInfo(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
            responseObj.setCode(ResponseObj.FAILED);
            responseObj.setMsg("其他错误！");
            return new GsonUtils().toJson(responseObj);
        }

        responseObj.setCode(ResponseObj.OK);
        responseObj.setMsg("上传成功");
        return new GsonUtils().toJson(responseObj);
    }

    //获取个人信息
    //post : userId
    // 从userinfo里获取个人信息
    //返回UserInfo
    @RequestMapping(value = "/getUserInfo"
            , method = RequestMethod.POST
            , produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getUserInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        responseObj = new ResponseObj<UserInfo>();
        Integer userId = Integer.parseInt(request.getParameter("userId"));
        if (userId == null) {
            responseObj.setCode(ResponseObj.EMPTY);
            responseObj.setMsg("用户id不能为空！");
            return new GsonUtils().toJson(responseObj);
        }
        try {
            UserInfo myUserInfo = new UserInfo();
            myUserInfo = userInfoService.getUserInfo(userId);
            responseObj.setCode(ResponseObj.OK);
            responseObj.setMsg("获取个人信息成功");
            responseObj.setData(myUserInfo);
        } catch (Exception e) {
            e.printStackTrace();
            responseObj.setCode(ResponseObj.FAILED);
            responseObj.setMsg("其他错误！");
            return new GsonUtils().toJson(responseObj);
        }
        return new GsonUtils().toJson(responseObj);
    }

    @RequestMapping(value = "/updateLabel"
            , method = RequestMethod.POST
            , produces = "application/json; charset=utf-8")
    @ResponseBody
    public String LabelUpdate(HttpServletRequest request, HttpServletResponse response, Label label) throws Exception {
        responseObj = new ResponseObj<Label>();
        if (label == null) {
            responseObj.setCode(ResponseObj.EMPTY);
            responseObj.setMsg("标签不能为空！");
            return new GsonUtils().toJson(responseObj);
        }
        try {

            if (label.getWarmth() == 1) {
                eScore += 1;
            }
            if (label.getGregariousness() == 1) {
                eScore += 1;
            }
            if (label.getAssertiveness() == 1) {
                eScore += 1;
            }
            if (label.getExcitementSeeking() == 1) {
                eScore += 1;
            }
            if (label.getActivity() == 1) {
                eScore += 1;
            }
            if (label.getPositiveEmotions() == 1) {
                eScore += 1;
            }
            label.setExtraversionScore(eScore);
            if (label.getTrust() == 1) {
                aScore += 1;
            }
            if (label.getStraightforwardness() == 1) {
                aScore += 1;
            }
            if (label.getAltruism() == 1) {
                aScore += 1;
            }
            if (label.getCompliance() == 1) {
                aScore += 1;
            }
            if (label.getModesty() == 1) {
                aScore += 1;
            }
            if (label.getTenderMindedness() == 1) {
                aScore += 1;
            }
            label.setAgreeablenessScore(aScore);
            if (label.getCompetence() == 1) {
                cScore += 1;
            }
            if (label.getOrder() == 1) {
                cScore += 1;
            }
            if (label.getDutifulness() == 1) {
                cScore += 1;
            }
            if (label.getAchievementStriving() == 1) {
                cScore += 1;
            }
            if (label.getSelfDiscipline() == 1) {
                cScore += 1;
            }
            if (label.getDeliberation() == 1) {
                cScore += 1;
            }
            label.setConscientiousnessScore(cScore);
            userInfoService.insertLabel(label);
        } catch (Exception e) {
            e.printStackTrace();
            responseObj.setCode(ResponseObj.FAILED);
            responseObj.setMsg("其他错误！");
            return new GsonUtils().toJson(responseObj);
        }

        responseObj.setCode(ResponseObj.OK);
        responseObj.setMsg("上传标签成功");
        return new GsonUtils().toJson(responseObj);
    }
}
