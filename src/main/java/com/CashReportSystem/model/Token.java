package com.CashReportSystem.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "active_tokens_table")
public class Token {

    @Id
    private long id;
    private String token;

    @Override
    public String toString() {
        return "Tokens{" +
                "id=" + id +
                ", token='" + token + '\'' +
                '}';
    }

    public Token() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
