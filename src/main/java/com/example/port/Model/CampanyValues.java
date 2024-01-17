package com.example.port.Model;

import java.util.Objects;

public class CampanyValues {
    private String tickerSymbol;
    private String dayDate;
    private Float closeValue;
    private Integer volume;
    private Float openValue;
    private Float highValue;
    private Float lowValue;

    public CampanyValues(String tickerSymbol, String dayDate, Float closeValue, Integer volume, Float openValue, Float highValue, Float lowValue) {
        this.tickerSymbol = tickerSymbol;
        this.dayDate = dayDate;
        this.closeValue = closeValue;
        this.volume = volume;
        this.openValue = openValue;
        this.highValue = highValue;
        this.lowValue = lowValue;
    }

    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public String getDayDate() {
        return dayDate;
    }

    public Float getCloseValue() {
        return closeValue;
    }

    public Integer getVolume() {
        return volume;
    }

    public Float getOpenValue() {
        return openValue;
    }

    public Float getHighValue() {
        return highValue;
    }

    public Float getLowValue() {
        return lowValue;
    }

    @Override
    public String toString() {
        return "CompanyValues{" +
                "tickerSymbol='" + tickerSymbol + '\'' +
                ", dayDate='" + dayDate + '\'' +
                ", closeValue=" + closeValue +
                ", volume=" + volume +
                ", openValue=" + openValue +
                ", highValue=" + highValue +
                ", lowValue=" + lowValue +
                '}';
    }
}