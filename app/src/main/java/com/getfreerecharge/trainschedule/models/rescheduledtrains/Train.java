package com.getfreerecharge.trainschedule.models.rescheduledtrains;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by amit on 3/29/2017.
 */

public class Train {
    @SerializedName("rescheduled_time")
    @Expose
    private String rescheduledTime;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("time_diff")
    @Expose
    private String timeDiff;
    @SerializedName("rescheduled_date")
    @Expose
    private String rescheduledDate;
    @SerializedName("to")
    @Expose
    private To to;
    @SerializedName("from")
    @Expose
    private From from;
    @SerializedName("number")
    @Expose
    private String number;

    public String getRescheduledTime() {
        return rescheduledTime;
    }

    public void setRescheduledTime(String rescheduledTime) {
        this.rescheduledTime = rescheduledTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimeDiff() {
        return timeDiff;
    }

    public void setTimeDiff(String timeDiff) {
        this.timeDiff = timeDiff;
    }

    public String getRescheduledDate() {
        return rescheduledDate;
    }

    public void setRescheduledDate(String rescheduledDate) {
        this.rescheduledDate = rescheduledDate;
    }

    public To getTo() {
        return to;
    }

    public void setTo(To to) {
        this.to = to;
    }

    public From getFrom() {
        return from;
    }

    public void setFrom(From from) {
        this.from = from;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
