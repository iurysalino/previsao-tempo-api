package com.github.iurysalino.previsaotempoapi.service.impl;

import com.github.iurysalino.previsaotempoapi.model.Main;
import com.github.iurysalino.previsaotempoapi.model.Root;
import com.github.iurysalino.previsaotempoapi.service.WeatherService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherServiceImpl implements WeatherService {

    Main main = new Main();
    @Value("${api.key}")
    private String apiKey;
    @Value("${url.api}")
    private String urlApi;

    @Override
    public Root findConditionsByCityName(String cityName) {
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
        System.out.println("Temperatura: "+ Math.round(root.getBody().getMain().getTemp())+"º");
        System.out.println("Temperatura Máxima: "+ Math.round(root.getBody().getMain().getTemp_max())+ "º");
        System.out.println("Temperatura Mínima: "+ Math.round(root.getBody().getMain().getTemp_min())+ "º");
        System.out.println("Céu: "+ root.getBody().getWeather().get(0).getDescription());
        return root.getBody();
    }
}
