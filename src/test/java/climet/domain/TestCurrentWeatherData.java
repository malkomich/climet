package climet.domain;

import climet.domain.City.Coordinates;
import climet.domain.Weather.*;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestCurrentWeatherData {

    private final float ERROR_PRECISSION = 0.01f;

    private static CurrentWeatherData weather;

    @BeforeClass
    public static void setUp() {
        JSONObject json = new JSONObject(
                "{\"coord\":{\"lon\":10.32,\"lat\":46.12},\"weather\":[{\"id\":701,\"main\":\"Mist\",\"description\":\"mist\",\"icon\":\"50d\"}],"
                        + "\"base\":\"cmc stations\",\"main\":{\"temp\":286.24,\"pressure\":1022,\"humidity\":81,\"temp_min\":282.15,\"temp_max\":289.82},"
                        + "\"wind\":{\"speed\":0.86,\"deg\":163.504},\"rain\":{\"3h\":0.2275},\"clouds\":{\"all\":40},\"dt\":1443871736,"
                        + "\"sys\":{\"type\":3,\"id\":5870,\"message\":0.0039,\"country\":\"IT\",\"sunrise\":1443849584,\"sunset\":1443891294},"
                        + "\"id\":3174137,\"name\":\"Malonno\",\"cod\":200}");
        weather = new CurrentWeatherData(json);
    }

    /**
     * Check if all data have been well-assigned
     */
    @Test
    public void checkData() {
        assertNotNull(weather);
        assertEquals(1443871736000L, weather.getDateTime().getTimeInMillis());

        City town = weather.getCity();
        assertNotNull(town);
        assertEquals(3174137, town.getId());
        assertEquals("Malonno", town.getName());
        assertEquals("IT", town.getCountryCode());
        Coordinates coord = town.getCoordinates();
        assertNotNull(coord);
        assertEquals(10.32f, coord.getLongitude(), ERROR_PRECISSION);
        assertEquals(46.12f, coord.getLatitude(), ERROR_PRECISSION);

        WeatherState state = weather.getState();
        assertNotNull(state);
        assertEquals("Mist", state.getMain());
        assertEquals("mist", state.getCode().name().toLowerCase());
        assertEquals("50d", state.getIcon());

        WeatherTemp main = weather.getTemp();
        assertNotNull(main);
        assertEquals(286.24f, main.getCurrentTemp(), ERROR_PRECISSION);
        assertEquals(1022, main.getPressure());
        assertEquals(81, main.getHumidity());
        assertEquals(282.15f, main.getMinTemp(), ERROR_PRECISSION);
        assertEquals(289.82f, main.getMaxTemp(), ERROR_PRECISSION);

        WeatherWind wind = weather.getWind();
        assertNotNull(wind);
        assertEquals(0.86f, wind.getSpeed(), ERROR_PRECISSION);
        assertEquals(163, wind.getDegrees());

        WeatherClouds clouds = weather.getClouds();
        assertNotNull(clouds);
        assertEquals(40, clouds.getCloudiness());

        WeatherRain rain = weather.getRain();
        assertNotNull(rain);
        assertEquals(3, rain.getHours());
        assertEquals(0.2275f, rain.getValue(), 0.0001f);

        WeatherSnow snow = weather.getSnow();
        assertEquals(0, snow.getHours());
        assertEquals(0f, snow.getValue(), 0f);
    }

    /**
     * Get a valid temperature of a town in Celsius.
     */
    @Test
    public void getCelsiusTemperature() {
        float temp = weather.getTemp().getCurrentTemp(Weather.CELSIUS);
        assertEquals(13.09f, temp, ERROR_PRECISSION);
    }

    /**
     * Get a valid temperature of a town in Farenheit.
     */
    @Test
    public void getFarenheitTemperature() {
        float temp = weather.getTemp().getCurrentTemp(Weather.FARENHEIT);
        assertEquals(55.562f, temp, ERROR_PRECISSION);
    }

    /**
     * Get a valid temperature of a town in Kelvin.
     */
    @Test
    public void getKelvinTemperature() {
        float temp = weather.getTemp().getCurrentTemp(Weather.KELVIN);
        assertEquals(286.24f, temp, ERROR_PRECISSION);
    }

}
