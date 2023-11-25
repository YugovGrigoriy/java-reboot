package ru.sberbank.edu;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.time.LocalDateTime;


public class WeatherProvider {
    private final String OPEN_WEATHER_MAP_API_KEY = "79585b51cd9c90a1e75da767e1fb7994";
    private final String OPEN_WEATHER_MAP_API_URL = "http://api.openweathermap.org/data/2.5/weather?q={city}&appid={apiKey}";
    private RestTemplate restTemplate;

    public WeatherProvider(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Download ACTUAL weather info from internet.
     * You should call GET http://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}
     * You should use Spring Rest Template for calling requests
     *
     * @param city - city
     * @return weather info or null
     */
    ObjectMapper objectMapper = new ObjectMapper();

    public WeatherInfo get(String city) {
        String apiUrl = OPEN_WEATHER_MAP_API_URL.replace("{city}", city).replace("{apiKey}", OPEN_WEATHER_MAP_API_KEY);
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
            String json = response.getBody();
            System.out.println(json);
            return fromJson(json);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public WeatherInfo fromJson(String json) {
        try {
            JsonNode rootNode = objectMapper.readTree(json);

            WeatherInfo weatherInfo = new WeatherInfo();
            weatherInfo.setCity(rootNode.get("name").asText());

            JsonNode weatherNode = rootNode.get("weather");
            weatherInfo.setShortDescription(weatherNode.get(0).get("main").asText());
            weatherInfo.setDescription(weatherNode.get(0).get("description").asText());

            JsonNode mainNode = rootNode.get("main");
            weatherInfo.setTemperature(mainNode.get("temp").asDouble());
            weatherInfo.setFeelsLikeTemperature(mainNode.get("feels_like").asDouble());
            weatherInfo.setPressure(mainNode.get("pressure").asDouble());

            JsonNode windNode = rootNode.get("wind");
            weatherInfo.setWindSpeed(windNode.get("speed").asDouble());

            LocalDateTime currentTime = LocalDateTime.now();
            weatherInfo.setExpiryTime(currentTime);

            return weatherInfo;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
