package com.github.iurysalino.previsaotempoapi.service.impl;

import com.github.iurysalino.previsaotempoapi.model.Root;
import com.github.iurysalino.previsaotempoapi.service.WeatherService;
import org.springframework.stereotype.Service;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Override
    public Root findByCityName(String cityName) {
        return null;
    }
}
