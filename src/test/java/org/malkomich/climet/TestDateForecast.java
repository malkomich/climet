package org.malkomich.climet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.malkomich.climet.domain.City;
import org.malkomich.climet.domain.DateForecastData;
import org.malkomich.climet.domain.Weather;

public class TestDateForecast {

	private final String CITY_EXAMPLE = "valladolid";
	private final float LAT_EXAMPLE = 41.66f;
	private final float LON_EXAMPLE = -4.72f;

	/**
	 * Check the call to the REST Service from “OpenWeatherMap” by the name of a
	 * town.
	 */
	@Test
	public void getDateForecastByCity() {
		DateForecastClient api = new DateForecastClient(CITY_EXAMPLE);
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
		DateForecastClient api = new DateForecastClient("zzzzzzzzzz");
		DateForecastData data = api.getForecast();
		assertNull(data);
	}

	/**
	 * Check the call to the REST Service from “OpenWeatherMap” by the
	 * coordinates.
	 */
	@Test
	public void getDateForecastByCoordinates() {
		DateForecastClient api = new DateForecastClient(LAT_EXAMPLE, LON_EXAMPLE);
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
		DateForecastClient api = new DateForecastClient(100, -80);
		DateForecastData data = api.getForecast();
		assertNull(data);
	}

}
