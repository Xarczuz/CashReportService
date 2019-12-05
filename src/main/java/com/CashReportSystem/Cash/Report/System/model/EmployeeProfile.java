package com.CashReportSystem.Cash.Report.System.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee_profile_table")
public class EmployeeProfile {
    @Id
    private Long id;
    private String employeeNr;
    private String role;
    private String firstName;
    private String lastName;
    private String phonenr;
    private String Email;

    public EmployeeProfile() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeNr() {
        return employeeNr;
    }

    public void setEmployeeNr(String employeeNr) {
        this.employeeNr = employeeNr;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
        return "EmployeeProfile{" +
                "id=" + id +
                ", employeeNr='" + employeeNr + '\'' +
                ", role='" + role + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phonenr='" + phonenr + '\'' +
                ", Email='" + Email + '\'' +
                '}';
    }
}
