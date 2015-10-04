package org.malkomich.climet.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;
import org.malkomich.climet.domain.Weather.WeatherClouds;
import org.malkomich.climet.domain.Weather.WeatherRain;
import org.malkomich.climet.domain.Weather.WeatherSnow;
import org.malkomich.climet.domain.Weather.WeatherState;
import org.malkomich.climet.domain.Weather.WeatherTemp;
import org.malkomich.climet.domain.Weather.WeatherWind;
import org.malkomich.climet.domain.Town.Coordinates;

public class TestCurrentWeatherData {

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

		Town town = weather.getTown();
		assertNotNull(town);
		assertEquals(3174137, town.getId());
		assertEquals("Malonno", town.getName());
		assertEquals("IT", town.getCountryCode());
		Coordinates coord = town.getCoordinates();
		assertNotNull(coord);
		assertEquals(10.32f, coord.getLon(), 0.01f);
		assertEquals(46.12, coord.getLat(), 0.01f);

		WeatherState state = weather.getState();
		assertNotNull(state);
		assertEquals("Mist", state.getMain());
		assertEquals("mist", state.getDescription());
		assertEquals("50d", state.getIcon());

		WeatherTemp main = weather.getTemp();
		assertNotNull(main);
		assertEquals(286.24, main.getTemperature(), 0.01);
		assertEquals(1022, main.getPressure());
		assertEquals(81, main.getHumidity());
		assertEquals(282.15, main.getMinTemp(), 0.01);
		assertEquals(289.82, main.getMaxTemp(), 0.01);

		WeatherWind wind = weather.getWind();
		assertNotNull(wind);
		assertEquals(0.86, wind.getSpeed(), 0.01);
		assertEquals(163, wind.getDeg());

		WeatherClouds clouds = weather.getClouds();
		assertNotNull(clouds);
		assertEquals(40, clouds.getAll());

		WeatherRain rain = weather.getRain();
		assertNotNull(rain);
		assertEquals("3h", rain.getUnits());
		assertEquals(0.2275, rain.getValue(), 0.0001);

		WeatherSnow snow = weather.getSnow();
		assertNull(snow);
	}

	/**
	 * Get a valid temperature of a town in Celsius.
	 */
	 @Test
	 public void getCelsiusTemperature() {
	 double temp = weather.getTemp().getTemperature(WeatherTemp.CELSIUS);
	 assertEquals(13.09, temp, 0.01);
	 }
	
	 /**
	 * Get a valid temperature of a town in Farenheit.
	 */
	 @Test
	 public void getFarenheitTemperature() {
		 double temp = weather.getTemp().getTemperature(WeatherTemp.FARENHEIT);
	 assertEquals(55.562, temp, 0.001);
	 }
	
	 /**
	 * Get a valid temperature of a town in Kelvin.
	 */
	 @Test
	 public void getKelvinTemperature() {
		 double temp = weather.getTemp().getTemperature(WeatherTemp.KELVIN);
	 assertEquals(286.24, temp, 0.01);
	 }

}
