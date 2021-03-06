package com.CashReportSystem.model;

import org.json.JSONObject;

import javax.persistence.*;

@Entity
@Table(name = "customer_profile_table")
public class CustomerProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    private String orgNr;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNr;
    private String Email;
    private long userId;

    public CustomerProfile() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getOrgNr() {
        return orgNr;
    }

    public void setOrgNr(String orgNr) {
        this.orgNr = orgNr;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNr() {
        return phoneNr;
    }

    public void setPhoneNr(String phoneNr) {
        this.phoneNr = phoneNr;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public JSONObject toJsonObject() {

        JSONObject customerJSONObject = new JSONObject();
        customerJSONObject.put("id", id);
        customerJSONObject.put("orgnr", orgNr);
        customerJSONObject.put("address", address);
        customerJSONObject.put("firstname", firstName);
        customerJSONObject.put("lastname", lastName);
        customerJSONObject.put("phonenr", phoneNr);
        customerJSONObject.put("email", Email);
        customerJSONObject.put("userid", userId);
        return customerJSONObject;

    }
}
