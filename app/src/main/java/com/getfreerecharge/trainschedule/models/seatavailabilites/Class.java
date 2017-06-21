package com.getfreerecharge.trainschedule.models.seatavailabilites;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by amit on 4/3/2017.
 */

public class Class {
    @SerializedName("class_name")
    @Expose
    private String className;
    @SerializedName("class_code")
    @Expose
    private String classCode;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

}
