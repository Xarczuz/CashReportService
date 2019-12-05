package com.CashReportSystem.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "game_table")
public class GameTable {

    @Id
    private Long id;
    private String tableName;
    private String location;

    public GameTable() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "GameTable{" +
                "id=" + id +
                ", tableName='" + tableName + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
