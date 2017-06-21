package com.getfreerecharge.trainschedule.models.trainbetweenstation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by amit on 4/3/2017.
 */

public class Train {
    @SerializedName("dest_arrival_time")
    @Expose
    private String destArrivalTime;
    @SerializedName("no")
    @Expose
    private Integer no;
    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("to")
    @Expose
    private To to;
    @SerializedName("travel_time")
    @Expose
    private String travelTime;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("classes")
    @Expose
    private List<Class> classes = null;
    @SerializedName("src_departure_time")
    @Expose
    private String srcDepartureTime;
    @SerializedName("days")
    @Expose
    private List<Day> days = null;
    @SerializedName("from")
    @Expose
    private From from;

    public String getDestArrivalTime() {
        return destArrivalTime;
    }

    public void setDestArrivalTime(String destArrivalTime) {
        this.destArrivalTime = destArrivalTime;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public To getTo() {
        return to;
    }

    public void setTo(To to) {
        this.to = to;
    }

    public String getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(String travelTime) {
        this.travelTime = travelTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Class> getClasses() {
        return classes;
    }

    public void setClasses(List<Class> classes) {
        this.classes = classes;
    }

    public String getSrcDepartureTime() {
        return srcDepartureTime;
    }

    public void setSrcDepartureTime(String srcDepartureTime) {
        this.srcDepartureTime = srcDepartureTime;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }

    public From getFrom() {
        return from;
    }

    public void setFrom(From from) {
        this.from = from;
    }
}
