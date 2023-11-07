package ru.sberbank.edu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TravelServiceTest {

    private TravelService travelService;

    @BeforeEach
    public void setUp() {
        travelService = new TravelService();
    }

    @Test
    public void testAddCity() {
        CityInfo cityInfo = new CityInfo("Moscow", new GeoPosition("55(45'07'')", "37(36'56'')"));
        travelService.add(cityInfo);

        List<String> cityNames = travelService.citiesNames();
        Assertions.assertEquals(1, cityNames.size());
        Assertions.assertEquals("Moscow", cityNames.get(0));
    }

    @Test
    public void testRemoveCity() {
        CityInfo cityInfo1 = new CityInfo("Moscow", new GeoPosition("55(45'07'')", "37(36'56'')"));
        CityInfo cityInfo2 = new CityInfo("Saint Petersburg", new GeoPosition("59(53'38'')", "30(15'50'')"));

        travelService.add(cityInfo1);
        travelService.add(cityInfo2);

        travelService.remove("Moscow");

        List<String> cityNames = travelService.citiesNames();
        Assertions.assertEquals(1, cityNames.size());
        Assertions.assertEquals("Saint Petersburg", cityNames.get(0));
    }

    @Test
    public void testGetDistanceBetweenCities() {
        CityInfo cityInfo1 = new CityInfo("Moscow", new GeoPosition("55(45'07'')", "37(36'56'')"));
        CityInfo cityInfo2 = new CityInfo("Saint Petersburg", new GeoPosition("59(53'38'')", "30(15'50'')"));

        travelService.add(cityInfo1);
        travelService.add(cityInfo2);

        int distance = travelService.getDistance("Moscow", "Saint Petersburg");
        Assertions.assertEquals(634, distance);
    }

    @Test
    public void testGetCitiesNear() {
        CityInfo cityInfo1 = new CityInfo("Moscow", new GeoPosition("55(45'07'')", "37(36'56'')"));
        CityInfo cityInfo2 = new CityInfo("Saint Petersburg", new GeoPosition("59(53'38'')", "30(15'50'')"));
        CityInfo cityInfo3 = new CityInfo("Tver", new GeoPosition("56(51'28'')", "35(55'18'')"));
        CityInfo cityInfo4 = new CityInfo("Ryazan", new GeoPosition("54(37'37'')", "39(41'31'')"));

        travelService.add(cityInfo1);
        travelService.add(cityInfo2);
        travelService.add(cityInfo3);
        travelService.add(cityInfo4);

        List<String> nearbyCities = travelService.getCitiesNear("Moscow", 200);
        Assertions.assertEquals(2, nearbyCities.size());
        Assertions.assertTrue(nearbyCities.contains("Tver"));
    }
}
