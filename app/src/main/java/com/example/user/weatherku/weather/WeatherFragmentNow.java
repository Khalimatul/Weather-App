package com.example.user.weatherku.weather;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.user.weatherku.Harian;
import com.example.user.weatherku.MainActivity;
import com.example.user.weatherku.R;
import com.example.user.weatherku.Utils;
import com.example.user.weatherku.model.CityWeather;
import com.example.user.weatherku.model.CurrentByCity;
import com.example.user.weatherku.model.Forecast;

import java.util.Calendar;
import java.util.Locale;


/**
 * Created by user on 12/12/17.
 */
public class WeatherFragmentNow extends Fragment {
    private static final String TAG = WeatherFragmentNow.class.toString();

    private ScrollView mScrollView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private TextView mUpdateText;
    private TextView mCityText;
    private ImageView mWeatherIcon;
    private TextView mDescriptionText;
    private TextView mHumidityText;
    private TextView mPressureText;
    private TextView mTemperatureText;
    private TextView mWeekdayText;
    private TextView mTemperatureMaxText;
    private TextView mTemperatureMinText;
    private ImageView mWeatherBg;
    private TextView mTemperatureDayText;
    private TextView mTemperatureMornText;
    private TextView mTemperatureNightText;
    private TextView mTemperatureEveText;

    private Forecast mForecast;

    private WeatherManager mWeatherManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWeatherManager = new WeatherManager((MainActivity)getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather_now, container, false);
        Button button = (Button) view.findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte = new Intent(getActivity(), Harian.class);
                startActivity(inte);

//                Fragment fragment = new WeatherFragmentHari();
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.container_weather, fragment);
//                    transaction.addToBackStack(null);
//                transaction.commit();
              //  FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
             //   fragmentManager.beginTransaction().replace(R.id.container_weather, fragment).commit();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mScrollView = (ScrollView) view.findViewById(R.id.scrollview_weather);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_weather);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.orange50);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mWeatherManager.fetchLocalWeatherData();
            }
        });


        //current
        mUpdateText = (TextView) view.findViewById(R.id.update_text_weather);
        mCityText = (TextView) view.findViewById(R.id.city_text_weather);
        mWeatherIcon = (ImageView) view.findViewById(R.id.icon_image_weather);
        mDescriptionText = (TextView) view.findViewById(R.id.description_text_weather);
        mHumidityText = (TextView) view.findViewById(R.id.humidity_text_weather);
        mPressureText = (TextView) view.findViewById(R.id.pressure_text_weather);
        mTemperatureText = (TextView) view.findViewById(R.id.temperature_text_weather);
        mWeekdayText = (TextView) view.findViewById(R.id.weekday_text_weather);
        mTemperatureMaxText = (TextView) view.findViewById(R.id.maxTemp_text_weather);
        mTemperatureMinText = (TextView) view.findViewById(R.id.minTemp_text_weather);
        mWeatherBg = (ImageView) view.findViewById(R.id.bg_weather);
//        mTemperatureMornText = (TextView) view.findViewById(R.id.morning_text_weather);
//        mTemperatureDayText = (TextView) view.findViewById(R.id.day_text_weather);
//        mTemperatureEveText = (TextView) view.findViewById(R.id.evening_text_weather);
//        mTemperatureNightText = (TextView) view.findViewById(R.id.night_text_weather);


    }

    private void setWeatherIcon(ImageView icon, String description) {
        if (description.contains("clear")) {
            icon.setImageResource(R.drawable.clear);
        } else if (description.contains("rain")) {
            icon.setImageResource(R.drawable.rain);
        } else if (description.contains("cloud")) {
            icon.setImageResource(R.drawable.cloud);
        } else if (description.contains("snow")) {
            icon.setImageResource(R.drawable.snow);
        } else if (description.contains("mist") || description.contains("haze")) {
            icon.setImageResource(R.drawable.mist);
        }
    }

    private void setWeatherBg(ImageView bg, String desc) {
        if (desc.contains("clear")) {
            bg.setImageResource(R.drawable.clouds);
        } else if (desc.contains("rain")) {
            bg.setImageResource(R.drawable.rainy);
        } else if (desc.contains("cloud")) {
            bg.setImageResource(R.drawable.clouds);
        } else if (desc.contains("snow")) {
            bg.setImageResource(R.drawable.snoww);
        } else if (desc.contains("mist") || desc.contains("haze")) {
            bg.setImageResource(R.drawable.mistt);
        }
    }

    private void updateWeather(CityWeather cityWeather) {
        Log.d(TAG, "updateWeather");
        Log.d(TAG, "updateWeather: " + cityWeather.main.temp);
        Log.d(TAG, "description: " + cityWeather.weathers[0].description);

        mUpdateText.setText(Utils.currentFormattedTime());
        //update image
        setWeatherBg(mWeatherBg, cityWeather.weathers[0].description);
        setWeatherIcon(mWeatherIcon, cityWeather.weathers[0].description);
        String stateString = cityWeather.name + ", " + mForecast.city.country;
        mCityText.setText(stateString);
        mDescriptionText.setText(cityWeather.weathers[0].description);
        stateString = "Humidity: " + cityWeather.main.humidity + " %";
        mHumidityText.setText(stateString);
        stateString = "Pressure: " + cityWeather.main.pressure + " hPa";
        mPressureText.setText(stateString);
        mWeekdayText.setText(Utils.weekDay());

        mTemperatureText.setText(Utils.temperatureFormat(cityWeather.main.temp));
        mTemperatureMaxText.setText(Utils.temperatureFormat(cityWeather.main.temp_max));
        mTemperatureMinText.setText(Utils.temperatureFormat(cityWeather.main.temp_min));
//        stateString = "Morning : " +Utils.temperatureFormat(cityWeather.main.temp_morn);
//        mTemperatureMornText.setText(stateString);
//        stateString = "Day : " +Utils.temperatureFormat(cityWeather.main.temp_day);
//        mTemperatureDayText.setText(stateString);
//        stateString = "Evening : " +Utils.temperatureFormat(cityWeather.main.temp_eve);
//        mTemperatureEveText.setText(stateString);
//        stateString = "Night : " +Utils.temperatureFormat(cityWeather.main.temp_night);
//        mTemperatureNightText.setText(stateString);
        scrollViewToTop();
    }

    private void updateWeatherByCity(CurrentByCity currentByCity) {
        Log.d(TAG, "updateWeatherByCity");
        Log.d(TAG, "updateWeatherByCity: " + currentByCity.main.temp);
        Log.d(TAG, "description: " + currentByCity.weathers[0].description);

        mUpdateText.setText(Utils.currentFormattedTime());
        //update image
        setWeatherBg(mWeatherBg, currentByCity.weathers[0].description);
        setWeatherIcon(mWeatherIcon, currentByCity.weathers[0].description);
        String stateString = currentByCity.name + ", " + currentByCity.system.country;
        mCityText.setText(stateString);
        mDescriptionText.setText(currentByCity.weathers[0].description);
        stateString = "Humidity: " + currentByCity.main.humidity + " %";
        mHumidityText.setText(stateString);
        stateString = "Pressure: " + currentByCity.main.pressure + " hPa";
        mPressureText.setText(stateString);
        mWeekdayText.setText(Utils.weekDay());

        mTemperatureText.setText(Utils.temperatureFormat(currentByCity.main.temp));

        mTemperatureMaxText.setText(Utils.temperatureFormat(currentByCity.main.temp_max));

        mTemperatureMinText.setText(Utils.temperatureFormat(currentByCity.main.temp_min));
//        stateString = "Morning : " +Utils.temperatureFormat(currentByCity.main.temp_morn);
//        mTemperatureMornText.setText(stateString);
//        stateString = "Day : " +Utils.temperatureFormat(currentByCity.main.temp_day);
//        mTemperatureDayText.setText(stateString);
//        stateString = "Evening : " +Utils.temperatureFormat(currentByCity.main.temp_eve);
//        mTemperatureEveText.setText(stateString);
//        stateString = "Night : " +Utils.temperatureFormat(currentByCity.main.temp_night);
//        mTemperatureNightText.setText(stateString);
        scrollViewToTop();
    }

    public void setCityWeather(CityWeather cityWeather) {
        Log.d(TAG, "setCityWeather");
        updateWeather(cityWeather);
    }

    public void setForecast(Forecast forecast) {
        Log.d(TAG, "setForecast");
        mForecast = forecast;
}

    public void setCurrentByCity(CurrentByCity currentByCity) {
        Log.d(TAG, "setCurrentByCity");
        updateWeatherByCity(currentByCity);
    }

    public void setRefreshing(boolean refreshing) {
        mSwipeRefreshLayout.setRefreshing(refreshing);
    }

    private void scrollViewToTop() {
        mScrollView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mScrollView.fullScroll(ScrollView.FOCUS_UP);
            }
        }, 20);
    }

    private String getWeekdayFromString(String date) {
        long time = Long.parseLong(date) * 1000;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.ENGLISH);
    }

}
