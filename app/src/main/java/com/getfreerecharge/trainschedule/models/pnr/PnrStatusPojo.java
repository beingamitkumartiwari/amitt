package com.getfreerecharge.trainschedule.models.pnr;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by amit on 3/21/2017.
 */

public class PnrStatusPojo {

    @SerializedName("response_code")
    @Expose
    private Integer responseCode;
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("from_station")
    @Expose
    private FromStation fromStation;
    @SerializedName("train_name")
    @Expose
    private String trainName;
    @SerializedName("boarding_point")
    @Expose
    private BoardingPoint boardingPoint;
    @SerializedName("reservation_upto")
    @Expose
    private ReservationUpto reservationUpto;
    @SerializedName("class")
    @Expose
    private String _class;
    @SerializedName("failure_rate")
    @Expose
    private Double failureRate;
    @SerializedName("doj")
    @Expose
    private String doj;
    @SerializedName("total_passengers")
    @Expose
    private Integer totalPassengers;
    @SerializedName("train_start_date")
    @Expose
    private TrainStartDate trainStartDate;
    @SerializedName("pnr")
    @Expose
    private String pnr;
    @SerializedName("train_num")
    @Expose
    private String trainNum;
    @SerializedName("passengers")
    @Expose
    private List<Passenger> passengers = null;
    @SerializedName("to_station")
    @Expose
    private ToStation toStation;
    @SerializedName("chart_prepared")
    @Expose
    private String chartPrepared;

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public FromStation getFromStation() {
        return fromStation;
    }

    public void setFromStation(FromStation fromStation) {
        this.fromStation = fromStation;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public BoardingPoint getBoardingPoint() {
        return boardingPoint;
    }

    public void setBoardingPoint(BoardingPoint boardingPoint) {
        this.boardingPoint = boardingPoint;
    }

    public ReservationUpto getReservationUpto() {
        return reservationUpto;
    }

    public void setReservationUpto(ReservationUpto reservationUpto) {
        this.reservationUpto = reservationUpto;
    }

    public String getClass_() {
        return _class;
    }

    public void setClass_(String _class) {
        this._class = _class;
    }

    public Double getFailureRate() {
        return failureRate;
    }

    public void setFailureRate(Double failureRate) {
        this.failureRate = failureRate;
    }

    public String getDoj() {
        return doj;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }

    public Integer getTotalPassengers() {
        return totalPassengers;
    }

    public void setTotalPassengers(Integer totalPassengers) {
        this.totalPassengers = totalPassengers;
    }

    public TrainStartDate getTrainStartDate() {
        return trainStartDate;
    }

    public void setTrainStartDate(TrainStartDate trainStartDate) {
        this.trainStartDate = trainStartDate;
    }

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public String getTrainNum() {
        return trainNum;
    }

    public void setTrainNum(String trainNum) {
        this.trainNum = trainNum;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public ToStation getToStation() {
        return toStation;
    }

    public void setToStation(ToStation toStation) {
        this.toStation = toStation;
    }

    public String getChartPrepared() {
        return chartPrepared;
    }

    public void setChartPrepared(String chartPrepared) {
        this.chartPrepared = chartPrepared;
    }

}
