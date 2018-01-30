package com.example.user.weatherku.model;

import java.util.List;

/**
 * Created by user on 12/12/17.
 */
public class Forecast extends WeatherBase {
    public City city;
    public int cnt;
    public List<ForecastList> list;
}
