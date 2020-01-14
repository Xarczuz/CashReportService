package com.CashReportSystem.model;

import org.json.JSONObject;

import javax.persistence.*;

@Entity
@Table(name = "report_table")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String gameTableName;
    private String location;
    private String employeeSign;
    private String customerSign;
    private Double digitalCashFlow;
    private Double cashFlow;
    private Double revenue;
    private Double payment;
    private String infoField;
    private String status;

    public JSONObject toJsonObject() {
        JSONObject reportJSONObject = new JSONObject();
        reportJSONObject.put("id", id);
        reportJSONObject.put("tablename", gameTableName);
        reportJSONObject.put("location", location);
        reportJSONObject.put("employeesign", employeeSign);
        reportJSONObject.put("customersign", customerSign);
        reportJSONObject.put("digitalcashflow", digitalCashFlow);
        reportJSONObject.put("cashflow", cashFlow);
        reportJSONObject.put("revenue", revenue);
        reportJSONObject.put("payment", payment);
        reportJSONObject.put("infofield", infoField);
        reportJSONObject.put("status", status);
        return reportJSONObject;
    }

    public Report() {
    }

    public String getGameTableName() {
        return gameTableName;
    }

    public void setGameTableName(String gameTableName) {
        this.gameTableName = gameTableName;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", tableName='" + gameTableName + '\'' +
                ", location='" + location + '\'' +
                ", employeeSign='" + employeeSign + '\'' +
                ", customerSign='" + customerSign + '\'' +
                ", digitalCashFlow=" + digitalCashFlow +
                ", cashFlow=" + cashFlow +
                ", revenue=" + revenue +
                ", payment=" + payment +
                ", infoField='" + infoField + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public String getLocation() {
        return location;
    }
    //UserId

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeSign() {
        return employeeSign;
    }

    public void setEmployeeSign(String employeeSign) {
        this.employeeSign = employeeSign;
    }

    public String getCustomerSign() {
        return customerSign;
    }

    public void setCustomerSign(String customerSign) {
        this.customerSign = customerSign;
    }

    public Double getDigitalCashFlow() {
        return digitalCashFlow;
    }

    public void setDigitalCashFlow(Double digitalCashFlow) {
        this.digitalCashFlow = digitalCashFlow;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
