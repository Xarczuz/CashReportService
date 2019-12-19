package com.CashReportSystem.model;

import org.json.JSONObject;

import javax.persistence.*;

@Entity
@Table(name = "employee_profile_table")
public class EmployeeProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String employeeNr;
    private String role;
    private String firstName;
    private String lastName;
    private String phoneNr;
    private String email;
    private long userId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public JSONObject toJsonObject() {
        JSONObject employeeJSONObject = new JSONObject();
        employeeJSONObject.put("id", id);
        employeeJSONObject.put("employeenr", employeeNr);
        employeeJSONObject.put("role", role);
        employeeJSONObject.put("firstname", firstName);
        employeeJSONObject.put("lastname", lastName);
        employeeJSONObject.put("phonenr", phoneNr);
        employeeJSONObject.put("email", email);
        employeeJSONObject.put("userid", userId);
        return employeeJSONObject;
    }

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

    public String getPhoneNr() {
        return phoneNr;
    }

    public void setPhoneNr(String phoneNr) {
        this.phoneNr = phoneNr;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "EmployeeProfile{" +
                "id=" + id +
                ", employeeNr='" + employeeNr + '\'' +
                ", role='" + role + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNr='" + phoneNr + '\'' +
                ", email='" + email + '\'' +
                ", userId=" + userId +
                '}';
    }
}
