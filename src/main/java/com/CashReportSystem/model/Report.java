package com.CashReportSystem.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "report_table")
public class Report {
    @Id
    private Long id;
    private String reportNr;
    private String employeeList;
    private String emplyoeeSign;
    private String customerSign;
    private Double digatlCashFlow;
    private Double cashFlow;
    private Double revenue;
    private Double payment;
    private String infoField;
    private String staus;
    //UserId

    public Report() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReportNr() {
        return reportNr;
    }

    public void setReportNr(String reportNr) {
        this.reportNr = reportNr;
    }

    public String getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(String employeeList) {
        this.employeeList = employeeList;
    }

    public String getEmplyoeeSign() {
        return emplyoeeSign;
    }

    public void setEmplyoeeSign(String emplyoeeSign) {
        this.emplyoeeSign = emplyoeeSign;
    }

    public String getCustomerSign() {
        return customerSign;
    }

    public void setCustomerSign(String customerSign) {
        this.customerSign = customerSign;
    }

    public Double getDigatlCashFlow() {
        return digatlCashFlow;
    }

    public void setDigatlCashFlow(Double digatlCashFlow) {
        this.digatlCashFlow = digatlCashFlow;
    }

    public Double getCashFlow() {
        return cashFlow;
    }

    public void setCashFlow(Double cashFlow) {
        this.cashFlow = cashFlow;
    }

    public Double getRevenue() {
        return revenue;
    }

    public void setRevenue(Double revenue) {
        this.revenue = revenue;
    }

    public Double getPayment() {
        return payment;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }

    public String getInfoField() {
        return infoField;
    }

    public void setInfoField(String infoField) {
        this.infoField = infoField;
    }

    public String getStaus() {
        return staus;
    }

    public void setStaus(String staus) {
        this.staus = staus;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", reportNr='" + reportNr + '\'' +
                ", employeeList='" + employeeList + '\'' +
                ", emplyoeeSign='" + emplyoeeSign + '\'' +
                ", customerSign='" + customerSign + '\'' +
                ", digatlCashFlow=" + digatlCashFlow +
                ", cashFlow=" + cashFlow +
                ", revenue=" + revenue +
                ", payment=" + payment +
                ", infoField='" + infoField + '\'' +
                ", staus='" + staus + '\'' +
                '}';
    }
}
