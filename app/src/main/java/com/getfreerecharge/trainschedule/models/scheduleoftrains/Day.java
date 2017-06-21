package com.getfreerecharge.trainschedule.models.scheduleoftrains;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by amit on 3/27/2017.
 */

public class Day {
    @SerializedName("runs")
    @Expose
    private String runs;
    @SerializedName("day-code")
    @Expose
    private String dayCode;

    public String getRuns() {
        return runs;
    }

    public void setRuns(String runs) {
        this.runs = runs;
    }

    public String getDayCode() {
        return dayCode;
    }

    public void setDayCode(String dayCode) {
        this.dayCode = dayCode;
    }
}
