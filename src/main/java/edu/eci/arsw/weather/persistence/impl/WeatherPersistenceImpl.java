package edu.eci.arsw.weather.persistence.impl;

import edu.eci.arsw.weather.model.Weather;
import edu.eci.arsw.weather.persistence.WeatherPersistence;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Jose Alejandro Naranjo Guzman
 */
@Service
public class WeatherPersistenceImpl implements WeatherPersistence {

    private ConcurrentHashMap<String,Weather> cache = new ConcurrentHashMap<String,Weather>();
    private static final String USER_AGENT = "Mozilla/5.0";
    private static String GET_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static String API_KEY = "&appid=c0198296887f1c0d81fd578f2c7e3761";

    @Override
    public Weather getWeather(String city) throws Exception {
        URL obj = new URL(GET_URL + city + API_KEY);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        // The following invocation perform the connection implicitly before getting the
        // code
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        Weather clima;
        if (responseCode == HttpURLConnection.HTTP_OK) {            
            if(cache.containsKey(city)){
                clima = cache.get(city);
                long time = System.currentTimeMillis();
                if(time - clima.getTime() <= 300000){
                    return clima;
                }                
            }
            clima = new Weather();
            cache.put(city, clima);
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            String response = "";

            while ((inputLine = in.readLine()) != null) {
                response += inputLine;
            }
            in.close();
            int posi = 16;
            int posf = posi + 1;
            while(response.charAt(posf)!=','){
                posf++;
            }
            clima.setLon(Float.parseFloat(response.substring(posi,posf)));
            posi = posf+1;
            while(response.charAt(posi)!=':'){
                posi++;
            }
            posf = posi+1;
            while(response.charAt(posf)!='}'){
                posf++;
            }
            clima.setLat(Float.parseFloat(response.substring(posi+1,posf)));
            posi = posf + 31;
            posf = posi+1;
            while(response.charAt(posf)!='"'){
                posf++;
            }
            clima.setWeather(response.substring(posi,posf));
            posi = posf + 1;
            while(response.charAt(posi)!=':'){
                posi++;
            }
            posf = posi+2;
            while(response.charAt(posf)!='"'){
                posf++;
            }
            clima.setDescription(response.substring(posi+2,posf));
            posi = posf + 1;
            while(response.charAt(posi)!='{'){
                posi++;
            }
            posi+=7;
            posf = posi+1;
            while(response.charAt(posf)!=','){
                posf++;
            }
            clima.setTemperature(Float.parseFloat(response.substring(posi+1,posf)));
        } else {
            throw new Exception();
        }
        return clima;
    }

    


}