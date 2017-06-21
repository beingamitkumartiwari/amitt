package com.getfreerecharge.trainschedule.models.trainbetweenstation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by amit on 4/3/2017.
 */

public class Class {
    @SerializedName("available")
    @Expose
    private String available;
    @SerializedName("class-code")
    @Expose
    private String classCode;

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }
}
