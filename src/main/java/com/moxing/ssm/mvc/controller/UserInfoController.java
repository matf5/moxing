package com.moxing.ssm.mvc.controller;

import com.moxing.ssm.model.Label;
import com.moxing.ssm.model.ResponseObj;
import com.moxing.ssm.model.User;
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
        byte[] butter = new BASE64Decoder().decodeBuffer(img);
       // InputStream inputStream = new ByteArrayInputStream(new BASE64Decoder().decodeBuffer(img));
            responseObj = new ResponseObj<User>();

            UploadFileRequest uploadFileRequest = new UploadFileRequest(MyCos.getBucketName(),"/sample_file.jpg",butter);
            String uploadFileRet = MyCos.getClient().uploadFile(uploadFileRequest);
            System.out.println(uploadFileRet);
            responseObj.setCode(ResponseObj.EMPTY);
            responseObj.setMsg(uploadFileRet);


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

            if (label.getWarmth()) {
                eScore += 1;
            }
            if (label.getGregariousness()) {
                eScore += 1;
            }
            if (label.getAssertiveness()) {
                eScore += 1;
            }
            if (label.getExcitementSeeking()) {
                eScore += 1;
            }
            if (label.getActivity()) {
                eScore += 1;
            }
            if (label.getPositiveEmotions()) {
                eScore += 1;
            }
            label.setExtraversionScore(eScore);
            if (label.getTrust()) {
                aScore += 1;
            }
            if (label.getStraightforwardness()) {
                aScore += 1;
            }
            if (label.getAltruism()) {
                aScore += 1;
            }
            if (label.getCompliance()) {
                aScore += 1;
            }
            if (label.getModesty()) {
                aScore += 1;
            }
            if (label.getTenderMindedness()) {
                aScore += 1;
            }
            label.setAgreeablenessScore(aScore);
            if (label.getCompetence()) {
                cScore += 1;
            }
            if (label.getOrder()) {
                cScore += 1;
            }
            if (label.getDutifulness()) {
                cScore += 1;
            }
            if (label.getAchievementStriving()) {
                cScore += 1;
            }
            if (label.getSelfDiscipline()) {
                cScore += 1;
            }
            if (label.getDeliberation()) {
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
        responseObj.setMsg("上传成功");
        return new GsonUtils().toJson(responseObj);
    }
}
