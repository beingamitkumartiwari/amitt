package com.getfreerecharge.trainschedule.models.trainroutes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by amit on 3/21/2017.
 */

public class TrainRoute {

    @SerializedName("response_code")
    @Expose
    private Integer responseCode;
    @SerializedName("train")
    @Expose
    private Train train;
    @SerializedName("route")
    @Expose
    private List<Route> route = null;

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

    public List<Route> getRoute() {
        return route;
    }

    public void setRoute(List<Route> route) {
        this.route = route;
    }

}
