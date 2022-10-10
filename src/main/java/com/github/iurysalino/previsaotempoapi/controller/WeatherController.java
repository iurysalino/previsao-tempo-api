package com.github.iurysalino.previsaotempoapi.controller;

import com.github.iurysalino.previsaotempoapi.model.Main;
import com.github.iurysalino.previsaotempoapi.model.Root;
import com.github.iurysalino.previsaotempoapi.service.impl.WeatherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/weather-api")
public class WeatherController {

    @Autowired
    WeatherServiceImpl weatherServiceImpl;


    @GetMapping("{cityName}")
    public Root getWeatherByCityName(@PathVariable String cityName){
        return weatherServiceImpl.findConditionsByCityName(cityName);
    }
}
