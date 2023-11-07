package ru.sberbank.edu;

import java.util.ArrayList;
import java.util.List;

/**
 * Travel Service.
 */
public class TravelService {

    // do not change type
    private final List<CityInfo> cities = new ArrayList<>();

    /**
     * Append city info.
     *
     * @param cityInfo - city info
     * @throws IllegalArgumentException if city already exists
     */
    public void add(CityInfo cityInfo) {
        if (!cities.contains(cityInfo)) {
            cities.add(cityInfo);
        } else {
            throw new IllegalArgumentException("City already exists");
        }
    }

    /**
     * remove city info.
     *
     * @param cityName - city name
     * @throws IllegalArgumentException if city doesn't exist
     */
    public void remove(String cityName) {
        cities.removeIf(cityInfo -> cityInfo.getName().equals(cityName));
    }

    /**
     * Get cities names.
     */
    public List<String> citiesNames() {
        List<String> cityNames = new ArrayList<>();
        for (CityInfo cityInfo : cities) {
            cityNames.add(cityInfo.getName());
        }
        return cityNames;
    }


    /**
     * Get distance in kilometers between two cities.
     * https://www.kobzarev.com/programming/calculation-of-distances-between-cities-on-their-coordinates/
     *
     * @param srcCityName  - source city
     * @param destCityName - destination city
     * @throws IllegalArgumentException if source or destination city doesn't exist.
     */
    public int getDistance(String srcCityName, String destCityName) {
        CityInfo srcCity = findCityByName(srcCityName);
        CityInfo destCity = findCityByName(destCityName);

        double distance = calculateDistance(srcCity.getPosition(), destCity.getPosition());
        return (int) Math.ceil(distance);
    }


    /**
     * Get all cities near current city in radius.
     *
     * @param cityName - city
     * @param radius   - radius in kilometers for search
     * @throws IllegalArgumentException if city with cityName city doesn't exist.
     */
    public List<String> getCitiesNear(String cityName, int radius) {
        CityInfo city = findCityByName(cityName);
        List<String> nearbyCities = new ArrayList<>();

        for (CityInfo otherCity : cities) {
            if (!otherCity.equals(city)) {
                double distance = calculateDistance(city.getPosition(), otherCity.getPosition());
                if (distance <= radius) {
                    nearbyCities.add(otherCity.getName());
                }
            }
        }

        return nearbyCities;
    }

    private CityInfo findCityByName(String cityName) {
        for (CityInfo city : cities) {
            if (city.getName().equals(cityName)) {
                return city;
            }
        }
        throw new IllegalArgumentException("Город не существует");
    }

    private double calculateDistance(GeoPosition srcPosition, GeoPosition destPosition) {
        double lat1 =srcPosition.getLatitude();
        double lon1 =srcPosition.getLongitude();
        double lat2 =destPosition.getLatitude();
        double lon2 =destPosition.getLongitude();
        double earthRadius = 6371.0;

        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;

        double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return earthRadius * c;
    }
}

