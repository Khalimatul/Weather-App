package com.example.user.weatherku.weather;

import com.example.user.weatherku.model.Current;
import com.example.user.weatherku.model.CurrentByCity;
import com.example.user.weatherku.model.Forecast;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by user on 12/12/17.
 */
public interface WeatherApi {

    @GET("/data/2.5/find?appid=727aa6438ef7127bfc8650be4d1ecb2d&cnt=8")
    Observable<Current> getCurrentWeather(@Query("lat") String lat, @Query("lon") String lon);

    @GET("/data/2.5/forecast/daily?appid=727aa6438ef7127bfc8650be4d1ecb2d&mode=json&cnt=8")
    Observable<Forecast> getForecastWeather(@Query("q") String city);

    @GET("/data/2.5/weather?appid=727aa6438ef7127bfc8650be4d1ecb2d")
    Observable<CurrentByCity> getCurrentWeatherByCity(@Query("q") String city);
}
