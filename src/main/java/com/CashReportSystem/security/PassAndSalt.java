package com.CashReportSystem.security;

public class PassAndSalt {
    private String PASSWORD;
    private String SALT;

    public PassAndSalt(String PASSWORD, String SALT) {
        this.PASSWORD = PASSWORD;
        this.SALT = SALT;
    }

    @Override
    public String toString() {
        return "PassAndSalt{" +
                "PASSWORD='" + PASSWORD + '\'' +
                ", SALT='" + SALT + '\'' +
                '}';
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public String getSALT() {
        return SALT;
    }

}
