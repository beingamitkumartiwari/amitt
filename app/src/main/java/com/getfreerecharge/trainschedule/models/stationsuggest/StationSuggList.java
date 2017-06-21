package com.getfreerecharge.trainschedule.models.stationsuggest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by amit on 4/24/2017.
 */

public class StationSuggList {

    @SerializedName("fullname")
    @Expose
    private String fullname;
    @SerializedName("code")
    @Expose
    private String code;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
