package com.CashReportSystem.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer_profile_table")
public class CustomerProfile {
    @Id
    private Long id;
    private String companyName;
    private String orgNr;
    private String firstName;
    private String lastName;
    private String adress;
    private String phonenr;
    private String Email;

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

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhonenr() {
        return phonenr;
    }

    public void setPhonenr(String phonenr) {
        this.phonenr = phonenr;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    @Override
    public String toString() {
        return "CustomerProfile{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", orgNr='" + orgNr + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", adress='" + adress + '\'' +
                ", phonenr='" + phonenr + '\'' +
                ", Email='" + Email + '\'' +
                '}';
    }
}