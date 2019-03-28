package edu.eci.arsw.weather.persistence;

import java.util.List;
import java.util.Set;

import edu.eci.arsw.weather.model.Weather;

/**
 * @author Jose Alejandro Naranjo Guzman
 */
public interface WeatherPersistence {
        
    /**
     * 
     * @param city name of the city
     * @return Weather of the given city
     * @throws  Exception if there is problem with the get request
     */
    public Weather getWeather(String city) throws Exception;

}