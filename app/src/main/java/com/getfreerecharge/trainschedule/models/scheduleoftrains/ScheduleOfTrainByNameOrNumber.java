package com.getfreerecharge.trainschedule.models.scheduleoftrains;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by amit on 3/27/2017.
 */

public class ScheduleOfTrainByNameOrNumber {
    @SerializedName("response_code")
    @Expose
    private Integer responseCode;
    @SerializedName("train")
    @Expose
    private Train train;

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

}
