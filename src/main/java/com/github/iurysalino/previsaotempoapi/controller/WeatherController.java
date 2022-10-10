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
    WeatherServiceImpl weatherService;
    Main main = new Main();
    @Value("${api.key}")
    private String apiKey;
    @Value("${url.api}")
    private String urlApi;

    @GetMapping("{cityName}")
    public Root getWeatherByCityName(@PathVariable String cityName){
        RestTemplate restTemplate = new RestTemplate();
        StringBuilder stringBuilder = new StringBuilder();
        String urlFinal = stringBuilder
                .append(urlApi)
                .append(cityName)
                .append("&lang=pt_br")
                .append("&appid=")
                .append(apiKey)
                .append("&units=metric")
                .toString();
        ResponseEntity<Root> root = restTemplate.getForEntity(urlFinal, Root.class);
        System.out.println("Temperatura: "+ root.getBody().getMain().getTemp());
        System.out.println("Temperatura Máxima: "+ root.getBody().getMain().getTemp_max());
        System.out.println("Temperatura Mínima: "+ root.getBody().getMain().getTemp_min());
        System.out.println("Céu: "+ root.getBody().getWeather().get(0).getDescription());
        return root.getBody();
    }
}
