package com.yashkanoria.coronavirustracker.DeathRecord;

public class DeathLocationWiseData {

    private String state;
    private String Country;

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

    public int getDeathlatestCount() {
        return DeathlatestCount;
    }

    public void setDeathlatestCount(int deathlatestCount) {
        DeathlatestCount = deathlatestCount;
    }

    public int getDeathrise() {
        return Deathrise;
    }

    public void setDeathrise(int deathrise) {
        Deathrise = deathrise;
    }

    private int DeathlatestCount;
    private int Deathrise;


}
