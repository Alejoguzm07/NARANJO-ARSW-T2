
package edu.eci.arsw.weather.controllers;

import edu.eci.arsw.weather.services.*;
import edu.eci.arsw.weather.model.*;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Jose Alejandro Naranjo Guzman
 */

@RestController
@RequestMapping(value = "/weather")
public class WeatherController {

    @Autowired
    WeatherServices weatherServices;

    @GetMapping("/{city}")
    public ResponseEntity<?> getWeatherByCity(@PathVariable("city") String city) throws Exception{

        try {
            Weather clima = weatherServices.getWeatherByCity(city);
            return new ResponseEntity<>(clima,HttpStatus.ACCEPTED);
        } catch (Exception e) {
                return new ResponseEntity<>(e.getLocalizedMessage(),HttpStatus.NOT_FOUND);
        }
    }
}



