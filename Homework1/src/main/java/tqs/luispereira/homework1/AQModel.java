package tqs.luispereira.homework1;

import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class AQModel {
    private Date time;
    private String cityname;
    private BigDecimal latitude;
    private BigDecimal longitude;

    @Override
    public String toString() {
        return "{" +
                "time:" + time +
                ", cityname:'" + cityname + '\'' +
                ", latitude:" + latitude +
                ", longitude:" + longitude +
                ", dominantpol:'" + dominantpol + '\'' +
                ", pollutants:" + polutents +
                ", aqi:" + aqi +
                '}';
    }

    private String dominantpol;

    public AQModel() {
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }


    private Map<String,Double> polutents;
    private double aqi;

    public double getAqi() {
        return aqi;
    }

    public void setAqi(double aqi) {
        this.aqi = aqi;
    }

    public Map<String, Double> getPolutents() {
        return polutents;
    }

    public void setPolutents(Map<String, Double> polutents) {
        this.polutents = polutents;
    }

    public String getDominantpol() {
        return dominantpol;
    }

    public void setDominantpol(String dominantpol) {
        this.dominantpol = dominantpol;
    }
}
