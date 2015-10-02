package org.malkomich.climet;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class TestDateForecast {

	private final String CITY_EXAMPLE = "valladolid";
	private final double LAT_EXAMPLE = 41.66;
	private final double LON_EXAMPLE = -4.72;

	/**
	 * Check the call to the REST Service from “OpenWeatherMap” by the name of a
	 * town.
	 */
	@Test
	public void getCurrentWeatherByCity() {
		DateForecast api = new DateForecast(CITY_EXAMPLE);
		List<TestDateWeather> weathers = api.getForecast();
		Town town = api.getTown();
		assertFalse(weathers.isEmpty());
		assertNotNull(town);
	}

	/**
	 * Check the call to the REST Service from “OpenWeatherMap” by the name of
	 * an invalid town.
	 */
	@Test(expected = CityNotFoundException.class)
	public void getCurrentWeatherByWrongCity() {
		new DateForecast("xlkahsjdg");
	}

	/**
	 * Check the call to the REST Service from “OpenWeatherMap” by the
	 * coordinates.
	 */
	@Test
	public void getCurrentWeatherByCoordinates() {
		DateForecast api = new DateForecast(LAT_EXAMPLE, LON_EXAMPLE);
		List<TestDateWeather> weathers = api.getForecast();
		Town town = api.getTown();
		assertFalse(weathers.isEmpty());
		assertNotNull(town);
	}

}
