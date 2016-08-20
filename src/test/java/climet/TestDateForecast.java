package climet;

import climet.domain.City;
import climet.domain.DateForecastData;
import climet.domain.Weather;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TestDateForecast {

    /**
     * Check the call to the REST Service from “OpenWeatherMap” by the name of a
     * town.
     */
    @Test
    public void getDateForecastByCity() {
        DateForecastClient api = new DateForecastClient(TestConstants.CITY_EXAMPLE, TestConstants.DEFAULT_API_KEY);
        DateForecastData data = api.getForecast();
        City city = data.getCity();
        List<Weather> forecast = data.getForecast();
        assertNotNull(city);
        assertFalse(forecast.isEmpty());
        assertEquals("ES", city.getCountryCode());
    }

    /**
     * Check the call to the REST Service from “OpenWeatherMap” by the name of
     * an invalid town.
     */
    @Test
    public void getDateForecastByWrongCity() {
        DateForecastClient api = new DateForecastClient(TestConstants.WRONG_CITY_EXAMPLE,
            TestConstants.DEFAULT_API_KEY);
        DateForecastData data = api.getForecast();
        assertNull(data);
    }

    /**
     * Check the call to the REST Service from “OpenWeatherMap” by the
     * coordinates.
     */
    @Test
    public void getDateForecastByCoordinates() {
        DateForecastClient api = new DateForecastClient(TestConstants.LAT_EXAMPLE, TestConstants.LON_EXAMPLE,
            TestConstants.DEFAULT_API_KEY);
        DateForecastData data = api.getForecast();
        City city = data.getCity();
        List<Weather> forecast = data.getForecast();
        assertNotNull(city);
        assertFalse(forecast.isEmpty());
        assertEquals("ES", city.getCountryCode());
    }

    /**
     * Check the call to the REST Service from “OpenWeatherMap” by wrong
     * coordinates.
     */
    @Test
    public void getDateForecastByWrongCoordinates() {
        DateForecastClient api = new DateForecastClient(100, -80, TestConstants.DEFAULT_API_KEY);
        DateForecastData data = api.getForecast();
        assertNull(data);
    }

}
