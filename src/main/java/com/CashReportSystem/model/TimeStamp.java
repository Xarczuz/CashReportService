package com.CashReportSystem.model;

import javax.persistence.*;

@Entity
@Table(name = "time_stamp_table")
public class TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userID;
    private String checkIn;  //Date
    private String checkOut;  //Date
    private String date;
    private String timeTotal;
    private Long reportID;

    public TimeStamp() {
    }

    @Override
    public String toString() {
        return "TimeStamp{" +
                "id=" + id +
                ", userID=" + userID +
                ", checkIn='" + checkIn + '\'' +
                ", checkOut='" + checkOut + '\'' +
                ", date='" + date + '\'' +
                ", timeTotal='" + timeTotal + '\'' +
                ", reportID=" + reportID +
                '}';
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeTotal() {
        return timeTotal;
    }

    public void setTimeTotal(String timeTotal) {
        this.timeTotal = timeTotal;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public Long getReportID() {
        return reportID;
    }

    public void setReportID(Long reportID) {
        this.reportID = reportID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }
}
