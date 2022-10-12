package com.github.iurysalino.previsaotempoapi.controller;

import com.github.iurysalino.previsaotempoapi.exceptions.CityNameInvalidException;
import com.github.iurysalino.previsaotempoapi.exceptions.CityNotFoundException;
import com.github.iurysalino.previsaotempoapi.model.MessageError;
import com.github.iurysalino.previsaotempoapi.service.impl.WeatherServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather-api")
public class WeatherController {

    private final WeatherServiceImpl weatherService;

    public WeatherController(WeatherServiceImpl weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("{cityName}")
    public ResponseEntity<?> getWeatherByCityName(@PathVariable String cityName) {
        try {
            return ResponseEntity.ok().body(weatherService.findByCityName(cityName));
        } catch (CityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (CityNameInvalidException e) {
            return ResponseEntity.badRequest().body(new MessageError("nome da cidade não é válido!"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new MessageError("algo de errado não está certo"));
        }
    }
}
