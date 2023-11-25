package ru.sberbank.edu;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class WeatherCache {

    private final Map<String, WeatherInfo> cache = new HashMap<>();
    private final WeatherProvider weatherProvider;

    /**
     * Constructor.
     *
     * @param weatherProvider - weather provider
     */
    public WeatherCache(WeatherProvider weatherProvider) {
        this.weatherProvider = weatherProvider;
    }

    /**
     * Get ACTUAL weather info for current city or null if current city not found.
     * If cache doesn't contain weather info OR contains NOT ACTUAL info then we should download info
     * If you download weather info then you should set expiry time now() plus 5 minutes.
     * If you can't download weather info then remove weather info for current city from cache.
     *
     * @param city - city
     * @return actual weather info
     */
    public synchronized WeatherInfo getWeatherInfo(String city) {
        WeatherInfo cachedInfo = cache.get(city);
        if (cachedInfo == null || !isInfoActual(cachedInfo)) {
            WeatherInfo newInfo = weatherProvider.get(city);
            if (newInfo != null) {
                newInfo.setExpiryTime(LocalDateTime.now().plusMinutes(5));
                cache.put(city, newInfo);
                return newInfo;
            } else {
                cache.remove(city);
            }
        }
        return cachedInfo;
    }

    /**
     * Remove weather info from cache.
     **/
    public synchronized void removeWeatherInfo(String city) {
        cache.remove(city);
    }

    private boolean isInfoActual(WeatherInfo info) {
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime expiryTime = info.getExpiryTime();
        return currentTime.isBefore(expiryTime);
    }
}


