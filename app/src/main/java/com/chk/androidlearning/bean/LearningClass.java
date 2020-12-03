package com.chk.androidlearning.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by CHK on 20-12-2.
 */
public class LearningClass {

    @SerializedName("Name")
    String name;

    @SerializedName("ActivityName")
    String activityName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }
}
