package com.getfreerecharge.trainschedule.models.seatavailabilites;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by amit on 4/3/2017.
 */

public class SeatAvailability {
    @SerializedName("failure_rate")
    @Expose
    private Double failureRate;
    @SerializedName("to")
    @Expose
    private To to;
    @SerializedName("last_updated")
    @Expose
    private LastUpdated lastUpdated;
    @SerializedName("train_number")
    @Expose
    private String trainNumber;
    @SerializedName("class")
    @Expose
    private Class _class;
    @SerializedName("quota")
    @Expose
    private Quota quota;
    @SerializedName("from")
    @Expose
    private From from;
    @SerializedName("availability")
    @Expose
    private List<Availability> availability = null;
    @SerializedName("response_code")
    @Expose
    private Integer responseCode;
    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("train_name")
    @Expose
    private String trainName;

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

    public LastUpdated getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LastUpdated lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public Class getClass_() {
        return _class;
    }

    public void setClass_(Class _class) {
        this._class = _class;
    }

    public Quota getQuota() {
        return quota;
    }

    public void setQuota(Quota quota) {
        this.quota = quota;
    }

    public From getFrom() {
        return from;
    }

    public void setFrom(From from) {
        this.from = from;
    }

    public List<Availability> getAvailability() {
        return availability;
    }

    public void setAvailability(List<Availability> availability) {
        this.availability = availability;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

}
