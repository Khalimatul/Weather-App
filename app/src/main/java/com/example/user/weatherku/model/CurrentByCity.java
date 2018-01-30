package com.example.user.weatherku.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 12/12/17.
 */
public class CurrentByCity extends WeatherBase {
    @SerializedName("weather")
    public Weather[] weathers;
    public Main main;
    @SerializedName("dt")
    public String timestamp;
    @SerializedName("sys")
    public System system;
    public String id;
    public String name;
}
