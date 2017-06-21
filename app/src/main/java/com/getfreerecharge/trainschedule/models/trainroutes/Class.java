package com.getfreerecharge.trainschedule.models.trainroutes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by amit on 3/21/2017.
 */

public class Class {

    @SerializedName("class-code")
    @Expose
    private String classCode;
    @SerializedName("available")
    @Expose
    private String available;

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

}
