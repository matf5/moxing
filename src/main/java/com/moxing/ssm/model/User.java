package com.moxing.ssm.model;

import java.io.Serializable;

/**
 * Created by mtf on 2016/11/30.
 */
public class User implements Serializable {
    private String phoneNum;
    private String password;
    private int id;

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phonenum) {
        this.phoneNum = phonenum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
