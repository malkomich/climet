package org.malkomich.climet;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.malkomich.climet.domain.HourForecastData;

public class TestHourForecast {

	private final String CITY_EXAMPLE = "valladolid";
	private final String WRONG_CITY_EXAMPLE = "zzzzzzzzzzzzzzzzzzzzz";
	private final float LAT_EXAMPLE = 41.66f;
	private final float LON_EXAMPLE = -4.72f;

	/**
	 * Check the call to the REST Service from “OpenWeatherMap” by the name of a
	 * town.
	 */
	@Test
	public void getHourForecastByCity() {
		HourForecastClient api = new HourForecastClient(CITY_EXAMPLE);
		HourForecastData data = api.getForecast();
		assertNotNull(data);
	}

	/**
	 * Check the call to the REST Service from “OpenWeatherMap” by the name of
	 * an invalid town.
	 */
	@Test
	public void getHourForecastByWrongCity() {
		HourForecastClient api = new HourForecastClient(WRONG_CITY_EXAMPLE);
		HourForecastData data = api.getForecast();
		assertNull(data);
	}

	/**
	 * Check the call to the REST Service from “OpenWeatherMap” by the
	 * coordinates.
	 */
	@Test
	public void getHourForecastByCoordinates() {
		HourForecastClient api = new HourForecastClient(LAT_EXAMPLE, LON_EXAMPLE);
		HourForecastData data = api.getForecast();
		assertNotNull(data);
	}

	/**
	 * Check the call to the REST Service from “OpenWeatherMap” by wrong
	 * coordinates.
	 */
	@Test
	public void getHourForecastByWrongCoordinates() {
		HourForecastClient api = new HourForecastClient(100, -80);
		HourForecastData data = api.getForecast();
		assertNull(data);
	}

}
