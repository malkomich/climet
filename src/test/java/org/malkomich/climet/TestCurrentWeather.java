package org.malkomich.climet;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class TestCurrentWeather {

	private final String CITY_EXAMPLE = "valladolid";
	private final double LAT_EXAMPLE = 41.66;
	private final double LON_EXAMPLE = -4.72;

	/**
	 * Check the call to the REST Service from “OpenWeatherMap” by the name of a
	 * town.
	 */
	@Test
	public void getCurrentWeatherByCity() {
		CurrentWeather api = new CurrentWeather(CITY_EXAMPLE);
		Weather weather = api.getWeather();
		Town town = api.getTown();
		assertNotNull(weather);
		assertNotNull(town);
	}

	/**
	 * Check the call to the REST Service from “OpenWeatherMap” by the name of
	 * an invalid town.
	 */
	@Test(expected = CityNotFoundException.class)
	public void getCurrentWeatherByWrongCity() {
		new CurrentWeather("xlkahsjdg");
	}

	/**
	 * Check the call to the REST Service from “OpenWeatherMap” by the
	 * coordinates.
	 */
	@Test
	public void getCurrentWeatherByCoordinates() {
		CurrentWeather api = new CurrentWeather(LAT_EXAMPLE, LON_EXAMPLE);
		Weather weather = api.getWeather();
		Town town = api.getTown();
		assertNotNull(weather);
		assertNotNull(town);
	}

}
