package com.moxing.ssm.model;

import java.io.Serializable;

/**
 * Created by mtf on 2016/11/30.
 */
public class User implements Serializable {
    private String phonenum;
    private String password;
    private int id;

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
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
