package com.getfreerecharge.trainschedule.models.seatavailabilites;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by amit on 4/3/2017.
 */

public class Quota {
    @SerializedName("quota_code")
    @Expose
    private String quotaCode;
    @SerializedName("quota_name")
    @Expose
    private String quotaName;

    public String getQuotaCode() {
        return quotaCode;
    }

    public void setQuotaCode(String quotaCode) {
        this.quotaCode = quotaCode;
    }

    public String getQuotaName() {
        return quotaName;
    }

    public void setQuotaName(String quotaName) {
        this.quotaName = quotaName;
    }
}
