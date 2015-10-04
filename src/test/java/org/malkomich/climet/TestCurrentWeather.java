package org.malkomich.climet;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.malkomich.climet.domain.CurrentWeatherData;

public class TestCurrentWeather {

	private final String CITY_EXAMPLE = "valladolid";
	private final float LAT_EXAMPLE = 41.66f;
	private final float LON_EXAMPLE = -4.72f;

	/**
	 * Check the call to the REST Service from “OpenWeatherMap” by the name of a
	 * town.
	 */
	@Test
	public void getCurrentWeatherByCity() {
		CurrentWeatherClient api = new CurrentWeatherClient(CITY_EXAMPLE);
		CurrentWeatherData weather = api.getWeather();
		assertNotNull(weather);
	}

	/**
	 * Check the call to the REST Service from “OpenWeatherMap” by the name of
	 * an invalid town.
	 */
	@Test
	public void getCurrentWeatherByWrongCity() {
		CurrentWeatherClient api = new CurrentWeatherClient("zzzzzzzzzz");
		CurrentWeatherData weather = api.getWeather();
		assertNull(weather);
	}

	/**
	 * Check the call to the REST Service from “OpenWeatherMap” by the
	 * coordinates.
	 */
	@Test
	public void getCurrentWeatherByCoordinates() {
		CurrentWeatherClient api = new CurrentWeatherClient(LAT_EXAMPLE, LON_EXAMPLE);
		CurrentWeatherData weather = api.getWeather();
		assertNotNull(weather);
	}
	
	/**
	 * Check the call to the REST Service from “OpenWeatherMap” by wrong
	 * coordinates.
	 */
	@Test
	public void getCurrentWeatherByWrongCoordinates() {
		CurrentWeatherClient api = new CurrentWeatherClient(100, -80);
		CurrentWeatherData weather = api.getWeather();
		assertNull(weather);
	}

}
