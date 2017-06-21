package com.getfreerecharge.trainschedule.models.cancelledtrains;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by amit on 3/28/2017.
 */

public class CancelledTrainPojo {

    @SerializedName("last_updated")
    @Expose
    private LastUpdated lastUpdated;
    @SerializedName("response_code")
    @Expose
    private Integer responseCode;
    @SerializedName("trains")
    @Expose
    private List<Train> trains = null;

    public LastUpdated getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LastUpdated lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public List<Train> getTrains() {
        return trains;
    }

    public void setTrains(List<Train> trains) {
        this.trains = trains;
    }

}
