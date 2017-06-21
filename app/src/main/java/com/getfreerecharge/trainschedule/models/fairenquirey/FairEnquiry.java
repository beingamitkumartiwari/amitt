package com.getfreerecharge.trainschedule.models.fairenquirey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by amit on 4/3/2017.
 */

public class FairEnquiry {
    @SerializedName("fare")
    @Expose
    private List<Fare> fare = null;
    @SerializedName("failure_rate")
    @Expose
    private Double failureRate;
    @SerializedName("to")
    @Expose
    private To to;
    @SerializedName("quota")
    @Expose
    private Quota quota;
    @SerializedName("response_code")
    @Expose
    private Integer responseCode;
    @SerializedName("train")
    @Expose
    private Train train;
    @SerializedName("from")
    @Expose
    private From from;

    public List<Fare> getFare() {
        return fare;
    }

    public void setFare(List<Fare> fare) {
        this.fare = fare;
    }

    public Double getFailureRate() {
        return failureRate;
    }

    public void setFailureRate(Double failureRate) {
        this.failureRate = failureRate;
    }

    public To getTo() {
        return to;
    }

    public void setTo(To to) {
        this.to = to;
    }

    public Quota getQuota() {
        return quota;
    }

    public void setQuota(Quota quota) {
        this.quota = quota;
    }

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

    public From getFrom() {
        return from;
    }

    public void setFrom(From from) {
        this.from = from;
    }

}
