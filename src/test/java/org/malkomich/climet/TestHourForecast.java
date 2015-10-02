package org.malkomich.climet;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class TestHourForecast {

	private final String CITY_EXAMPLE = "valladolid";
	private final double LAT_EXAMPLE = 41.66;
	private final double LON_EXAMPLE = -4.72;

	/**
	 * Check the call to the REST Service from “OpenWeatherMap” by the name of a
	 * town.
	 */
	@Test
	public void getCurrentWeatherByCity() {
		HourForecast api = new HourForecast(CITY_EXAMPLE);
		List<Weather> weathers = api.getForecast();
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
		new HourForecast("xlkahsjdg");
	}

	/**
	 * Check the call to the REST Service from “OpenWeatherMap” by the
	 * coordinates.
	 */
	@Test
	public void getCurrentWeatherByCoordinates() {
		HourForecast api = new HourForecast(LAT_EXAMPLE, LON_EXAMPLE);
		List<Weather> weathers = api.getForecast();
		Town town = api.getTown();
		assertFalse(weathers.isEmpty());
		assertNotNull(town);
	}

}
