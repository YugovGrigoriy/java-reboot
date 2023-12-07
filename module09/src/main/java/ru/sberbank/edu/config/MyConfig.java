package ru.sberbank.edu.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;
import ru.sberbank.edu.WeatherCache;
import ru.sberbank.edu.WeatherProvider;


@Configuration
@PropertySource("classpath:application.properties")
public class MyConfig {

    @Value("${appKey}")
    private String apiKey;


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public WeatherProvider weatherProvider(RestTemplate restTemplate) {
        return new WeatherProvider(restTemplate,apiKey);
    }

@Bean
    public WeatherCache weatherCache(WeatherProvider weatherProvider){
    return new WeatherCache(weatherProvider);
}
}
