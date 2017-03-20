package com.moxing.ssm.model;

import java.util.Date;

public class UserInfo {
    private Integer id;

    private String phoneNum;

    private String nickname;

    private String headimgUrl;

    private String posCity;

    private Boolean sex;

    private Date cdate;

    private Date mdate;

    private Integer userId;

    private String posProv;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum == null ? null : phoneNum.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getHeadimgUrl() {
        return headimgUrl;
    }

    public void setHeadimgUrl(String headimgUrl) {
        this.headimgUrl = headimgUrl == null ? null : headimgUrl.trim();
    }

    public String getPosCity() {
        return posCity;
    }

    public void setPosCity(String posCity) {
        this.posCity = posCity == null ? null : posCity.trim();
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    public Date getMdate() {
        return mdate;
    }

    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPosProv() {
        return posProv;
    }

    public void setPosProv(String posProv) {
        this.posProv = posProv == null ? null : posProv.trim();
    }
}