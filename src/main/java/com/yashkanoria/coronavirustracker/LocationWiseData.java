package com.yashkanoria.coronavirustracker;

public class LocationWiseData {

    private String state;
    private String Country;
    private int latestCount;
    private int casesrise;

    public int getCasesrise() {
        return casesrise;
    }

    public void setCasesrise(int casesrise) {
        this.casesrise = casesrise;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public int getLatestCount() {
        return latestCount;
    }

    public void setLatestCount(int latestCount) {
        this.latestCount = latestCount;
    }


}
