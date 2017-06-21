package com.getfreerecharge.trainschedule.models.stationsuggest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by amit on 4/24/2017.
 */

public class StationSuggestion {

    @SerializedName("response_code")
    @Expose
    private Integer responseCode;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("station")
    @Expose
    private List<StationSuggList> station = null;

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<StationSuggList> getStation() {
        return station;
    }

    public void setStation(List<StationSuggList> station) {
        this.station = station;
    }

}
