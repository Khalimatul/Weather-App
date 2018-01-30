package com.example.user.weatherku.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 12/12/17.
 */
public class CityWeather {
    public int id;
    public String name;
    public Main main;
    @SerializedName("dt")
    public String timestamp;
    @SerializedName("sys")
    public System system;
    @SerializedName("weather")
    public Weather[] weathers;
}
