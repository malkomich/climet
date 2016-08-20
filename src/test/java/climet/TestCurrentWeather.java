package climet;

import climet.domain.City;
import climet.domain.CurrentWeatherData;
import climet.domain.Weather;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestCurrentWeather {

    /**
     * Check the call to the REST Service from “OpenWeatherMap” by the name of a
     * town.
     */
    @Test
    public void getCurrentWeatherByCity() {
        CurrentWeatherClient api = new CurrentWeatherClient(TestConstants.CITY_EXAMPLE, TestConstants.DEFAULT_API_KEY);
        CurrentWeatherData data = api.getWeather();
        City city = data.getCity();
        Weather weather = data.getWeather();
        assertNotNull(city);
        assertNotNull(weather);
        assertEquals("ES", city.getCountryCode());
    }

    /**
     * Check the call to the REST Service from “OpenWeatherMap” by the name of
     * an invalid town.
     */
    @Test
    public void getCurrentWeatherByWrongCity() {
        CurrentWeatherClient api = new CurrentWeatherClient(TestConstants.WRONG_CITY_EXAMPLE,
            TestConstants.DEFAULT_API_KEY);
        CurrentWeatherData weather = api.getWeather();
        assertNull(weather);
    }

    /**
     * Check the call to the REST Service from “OpenWeatherMap” by the
     * coordinates.
     */
    @Test
    public void getCurrentWeatherByCoordinates() {
        CurrentWeatherClient api = new CurrentWeatherClient(TestConstants.LAT_EXAMPLE, TestConstants.LON_EXAMPLE,
            TestConstants.DEFAULT_API_KEY);
        CurrentWeatherData data = api.getWeather();
        City city = data.getCity();
        Weather weather = data.getWeather();
        assertNotNull(city);
        assertNotNull(weather);
        assertEquals("ES", city.getCountryCode());
    }

    /**
     * Check the call to the REST Service from “OpenWeatherMap” by wrong
     * coordinates.
     */
    @Test
    public void getCurrentWeatherByWrongCoordinates() {
        CurrentWeatherClient api = new CurrentWeatherClient(100, -80, TestConstants.DEFAULT_API_KEY);
        CurrentWeatherData weather = api.getWeather();
        assertNull(weather);
    }

}
