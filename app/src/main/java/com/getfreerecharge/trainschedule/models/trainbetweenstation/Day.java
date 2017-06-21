package com.getfreerecharge.trainschedule.models.trainbetweenstation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by amit on 4/3/2017.
 */

public class Day {
    @SerializedName("day-code")
    @Expose
    private String dayCode;
    @SerializedName("runs")
    @Expose
    private String runs;

    public String getDayCode() {
        return dayCode;
    }

    public void setDayCode(String dayCode) {
        this.dayCode = dayCode;
    }

    public String getRuns() {
        return runs;
    }

    public void setRuns(String runs) {
        this.runs = runs;
    }
}
