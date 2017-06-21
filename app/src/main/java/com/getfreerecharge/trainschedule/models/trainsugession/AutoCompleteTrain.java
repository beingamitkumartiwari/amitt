package com.getfreerecharge.trainschedule.models.trainsugession;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by amit on 4/17/2017.
 */

public class AutoCompleteTrain {

    @SerializedName("train")
    @Expose
    private List<String> train = null;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("trains")
    @Expose
    private List<SuggestedTrain> trains = null;
    @SerializedName("response_code")
    @Expose
    private Integer responseCode;

    public List<String> getTrain() {
        return train;
    }

    public void setTrain(List<String> train) {
        this.train = train;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<SuggestedTrain> getTrains() {
        return trains;
    }

    public void setTrains(List<SuggestedTrain> trains) {
        this.trains = trains;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }


}
