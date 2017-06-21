package com.getfreerecharge.trainschedule.models.trainroutes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by amit on 3/21/2017.
 */

public class Route {

    @SerializedName("distance")
    @Expose
    private Integer distance;
    @SerializedName("scharr")
    @Expose
    private String scharr;
    @SerializedName("lng")
    @Expose
    private Double lng;
    @SerializedName("halt")
    @Expose
    private Integer halt;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("day")
    @Expose
    private Integer day;
    @SerializedName("fullname")
    @Expose
    private String fullname;
    @SerializedName("schdep")
    @Expose
    private String schdep;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("no")
    @Expose
    private Integer no;
    @SerializedName("route")
    @Expose
    private Integer route;

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public String getScharr() {
        return scharr;
    }

    public void setScharr(String scharr) {
        this.scharr = scharr;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Integer getHalt() {
        return halt;
    }

    public void setHalt(Integer halt) {
        this.halt = halt;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getSchdep() {
        return schdep;
    }

    public void setSchdep(String schdep) {
        this.schdep = schdep;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public Integer getRoute() {
        return route;
    }

    public void setRoute(Integer route) {
        this.route = route;
    }
}
