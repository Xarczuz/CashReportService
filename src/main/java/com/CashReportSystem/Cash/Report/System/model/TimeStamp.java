package com.CashReportSystem.Cash.Report.System.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "time_stamp_table")
public class TimeStamp {
    private String checkIn;  //Date
    private String date;
    private String timeTotal;

    public TimeStamp() {
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

    @Override
    public String toString() {
        return "TimeStamp{" +
                "checkIn='" + checkIn + '\'' +
                ", date='" + date + '\'' +
                ", timeTotal='" + timeTotal + '\'' +
                '}';
    }
}
