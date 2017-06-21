package com.getfreerecharge.trainschedule.models.pnr;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by amit on 3/23/2017.
 */

public class Passenger {

    @SerializedName("coach_position")
    @Expose
    private Integer coachPosition;
    @SerializedName("booking_status")
    @Expose
    private String bookingStatus;
    @SerializedName("no")
    @Expose
    private Integer no;
    @SerializedName("current_status")
    @Expose
    private String currentStatus;

    public Integer getCoachPosition() {
        return coachPosition;
    }

    public void setCoachPosition(Integer coachPosition) {
        this.coachPosition = coachPosition;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

}
