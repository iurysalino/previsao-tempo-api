package com.github.iurysalino.previsaotempoapi.model;

import com.github.iurysalino.previsaotempoapi.exceptions.CityNameInvalidException;
import org.apache.logging.log4j.util.Strings;

// ter um classe especializada na criação da url, fica mais facil de testar :)
public class WeatherUrl {
    private final String urlApi;
    private final String cityName;
    private final String language;
    private final String apiKey;
    private final String units;

    public WeatherUrl(String urlApi, String cityName, String apiKey, String language, String units) throws CityNameInvalidException {
        if (Strings.isBlank(cityName)) {
            throw new CityNameInvalidException();
        }
        this.urlApi = urlApi;
        this.cityName = cityName;
        this.apiKey = apiKey;
        this.language = language;
        this.units = units;
    }

    @Override
    public String toString() {
        return String.format("%s?q=%s&lang=%s&appid=%s&units=%s", urlApi, cityName, language, apiKey, units);
    }
}