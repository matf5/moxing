package com.moxing.ssm.model;

import java.util.Date;

/**
 * Created by lxx on 2017/3/16.
 */
public class Message {

    private int id;

    private int sendId;

    private int receId;

    private String content;

    private Date cdate;

    private boolean state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSendId() {
        return sendId;
    }

    public void setSendId(int sendId) {
        this.sendId = sendId;
    }

    public int getReceId() {
        return receId;
    }

    public void setReceId(int receId) {
        this.receId = receId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
