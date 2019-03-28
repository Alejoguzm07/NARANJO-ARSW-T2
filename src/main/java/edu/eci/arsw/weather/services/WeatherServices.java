package edu.eci.arsw.weather.services;

import edu.eci.arsw.weather.model.Weather;
import edu.eci.arsw.weather.persistence.WeatherPersistence;

import java.util.Date;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jose Alejandro Naranjo Guzman
 */
@Service
public class WeatherServices {
    @Autowired
    WeatherPersistence wps;

	public Weather getWeatherByCity(String city) throws Exception {
        return wps.getWeather(city);
	}


}