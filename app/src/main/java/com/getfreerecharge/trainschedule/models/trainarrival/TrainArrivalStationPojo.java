package com.getfreerecharge.trainschedule.models.trainarrival;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by amit on 3/29/2017.
 */

public class TrainArrivalStationPojo {
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("station")
    @Expose
    private String station;
    @SerializedName("trains")
    @Expose
    private List<Train> trains = null;
    @SerializedName("response_code")
    @Expose
    private Integer responseCode;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public List<Train> getTrains() {
        return trains;
    }

    public void setTrains(List<Train> trains) {
        this.trains = trains;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

}
