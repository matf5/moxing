package com.moxing.ssm.mvc.controller;

import com.moxing.ssm.model.ResponseObj;
import com.moxing.ssm.model.User;
import com.moxing.ssm.utils.GsonUtils;
import com.moxing.ssm.utils.MyCos;
import com.qcloud.cos.request.UploadFileRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by mtf on 2016/12/29.
 */
@Controller
@RequestMapping("/baseAction")
public class UserInfoController {

    private ResponseObj responseObj;

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

}
