package com.CashReportSystem.model;

import org.json.JSONObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CasinoTokens {
    @Column()
    private int startFives;
    @Column()
    private int startTens;
    @Column()
    private int startTwenties;
    @Column()
    private int startFifties;
    @Column()
    private int startHundreds;
    @Column()
    private int startChange;
    @Column()
    private int startSum;
    @Column()
    private int endFives;
    @Column()
    private int endTens;
    @Column()
    private int endTwenties;
    @Column()
    private int endFifties;
    @Column()
    private int endHundreds;
    @Column()
    private int endChange;
    @Column()
    private int endSum;

    public JSONObject toJsonObject() {
        JSONObject casinoTokenJSONObject = new JSONObject();
        casinoTokenJSONObject.put("startFives", startFives);
        casinoTokenJSONObject.put("startTens", startTens);
        casinoTokenJSONObject.put("startTwenties", startTwenties);
        casinoTokenJSONObject.put("startFifties", startFifties);
        casinoTokenJSONObject.put("startHundreds", startHundreds);
        casinoTokenJSONObject.put("startChange", startChange);
        casinoTokenJSONObject.put("startSum", startSum);

        casinoTokenJSONObject.put("endFives", endFives);
        casinoTokenJSONObject.put("endTens", endTens);
        casinoTokenJSONObject.put("endTwenties", endTwenties);
        casinoTokenJSONObject.put("endFifties", endFifties);
        casinoTokenJSONObject.put("endHundreds", endHundreds);
        casinoTokenJSONObject.put("endChange", endChange);
        casinoTokenJSONObject.put("endSum", endSum);
        return casinoTokenJSONObject;

    }

    public CasinoTokens() {
    }

    @Override
    public String toString() {
        return "CasinoTokens{" +
                "startFives=" + startFives +
                ", startTens=" + startTens +
                ", startTwenties=" + startTwenties +
                ", startFifties=" + startFifties +
                ", startHundreds=" + startHundreds +
                ", startChange=" + startChange +
                ", startSum=" + startSum +
                ", endFives=" + endFives +
                ", endTens=" + endTens +
                ", endTwenties=" + endTwenties +
                ", endFifties=" + endFifties +
                ", endHundreds=" + endHundreds +
                ", endChange=" + endChange +
                ", endSum=" + endSum +
                '}';
    }

    public int getStartFives() {
        return startFives;
    }

    public void setStartFives(int startFives) {
        this.startFives = startFives;
    }

    public int getStartTens() {
        return startTens;
    }

    public void setStartTens(int startTens) {
        this.startTens = startTens;
    }

    public int getStartTwenties() {
        return startTwenties;
    }

    public void setStartTwenties(int startTwenties) {
        this.startTwenties = startTwenties;
    }

    public int getStartFifties() {
        return startFifties;
    }

    public void setStartFifties(int startFifties) {
        this.startFifties = startFifties;
    }

    public int getStartHundreds() {
        return startHundreds;
    }

    public void setStartHundreds(int startHundreds) {
        this.startHundreds = startHundreds;
    }

    public int getStartChange() {
        return startChange;
    }

    public void setStartChange(int startChange) {
        this.startChange = startChange;
    }

    public int getStartSum() {
        return startSum;
    }

    public void setStartSum(int startSum) {
        this.startSum = startSum;
    }

    public int getEndFives() {
        return endFives;
    }

    public void setEndFives(int endFives) {
        this.endFives = endFives;
    }

    public int getEndTens() {
        return endTens;
    }

    public void setEndTens(int endTens) {
        this.endTens = endTens;
    }

    public int getEndTwenties() {
        return endTwenties;
    }

    public void setEndTwenties(int endTwenties) {
        this.endTwenties = endTwenties;
    }

    public int getEndFifties() {
        return endFifties;
    }

    public void setEndFifties(int endFifties) {
        this.endFifties = endFifties;
    }

    public int getEndHundreds() {
        return endHundreds;
    }

    public void setEndHundreds(int endHundreds) {
        this.endHundreds = endHundreds;
    }

    public int getEndChange() {
        return endChange;
    }

    public void setEndChange(int endChange) {
        this.endChange = endChange;
    }

    public int getEndSum() {
        return endSum;
    }

    public void setEndSum(int endSum) {
        this.endSum = endSum;
    }
}
