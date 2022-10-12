package com.github.iurysalino.previsaotempoapi.service;

import com.github.iurysalino.previsaotempoapi.exceptions.CityNameInvalidException;
import com.github.iurysalino.previsaotempoapi.exceptions.CityNotFoundException;
import com.github.iurysalino.previsaotempoapi.exceptions.ClientWeatherException;
import com.github.iurysalino.previsaotempoapi.model.Root;
import org.springframework.stereotype.Service;

@Service
public interface WeatherService {
    Root findByCityName(String cityName) throws CityNotFoundException, ClientWeatherException, CityNameInvalidException;
}
