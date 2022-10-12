package com.github.iurysalino.previsaotempoapi.service.impl;

import com.github.iurysalino.previsaotempoapi.exceptions.CityNameInvalidException;
import com.github.iurysalino.previsaotempoapi.exceptions.CityNotFoundException;
import com.github.iurysalino.previsaotempoapi.exceptions.ClientWeatherException;
import com.github.iurysalino.previsaotempoapi.model.Root;
import com.github.iurysalino.previsaotempoapi.model.WeatherUrl;
import com.github.iurysalino.previsaotempoapi.service.WeatherService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherServiceImpl implements WeatherService {

    private final String apiKey;
    private final String urlApi;

    // fazer injeção pelo construtor fica mais facil de testar, precisa usar reflection
    public WeatherServiceImpl(@Value("${api.key}") String apiKey, @Value("${url.api}") String urlApi) {
        this.apiKey = apiKey;
        this.urlApi = urlApi;
    }

    @Override
    public Root findByCityName(String cityName) throws CityNotFoundException, ClientWeatherException, CityNameInvalidException {
        RestTemplate restTemplate = new RestTemplate();
        var url = new WeatherUrl(urlApi, cityName, apiKey, "pt_br", "metric");

        try {
            ResponseEntity<Root> response = restTemplate.getForEntity(url.toString(), Root.class);
            return response.getBody();
        } catch (HttpClientErrorException.NotFound e) {
            throw new CityNotFoundException();
        } catch (Exception e) {
            // esse cara é muito generico, seria legal explorar os erros da api
            throw new ClientWeatherException();
        }
    }
}
