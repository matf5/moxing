package com.moxing.ssm.model;

import java.util.Date;

/**
 * Created by matingfeng on 2017/5/16.
 */
public class Match {
    private int id;
   private int firstTravelId;
   private int secondTravelId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFirstTravelId() {
        return firstTravelId;
    }

    public void setFirstTravelId(int firstTravelId) {
        this.firstTravelId = firstTravelId;
    }

    public int getSecondTravelId() {
        return secondTravelId;
    }

    public void setSecondTravelId(int secondTravelId) {
        this.secondTravelId = secondTravelId;
    }
}
