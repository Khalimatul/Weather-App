package com.example.user.weatherku.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 12/12/17.
 */
public class ForecastList {
    @SerializedName("dt")
    public String timestamp;
    public Temp temp;
    @SerializedName("weather")
    public Weather[] weathers;
}
