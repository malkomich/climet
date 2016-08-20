package climet;

import climet.domain.HourForecastData;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class TestHourForecast {

	/**
	 * Check the call to the REST Service from “OpenWeatherMap” by the name of a
	 * town.
	 */
	@Test
	public void getHourForecastByCity() {
		HourForecastClient api = new HourForecastClient(TestConstants.CITY_EXAMPLE, TestConstants.DEFAULT_API_KEY);
		HourForecastData data = api.getForecast();
		assertNotNull(data);
	}

	/**
	 * Check the call to the REST Service from “OpenWeatherMap” by the name of
	 * an invalid town.
	 */
	@Test
	public void getHourForecastByWrongCity() {
		HourForecastClient api = new HourForecastClient(TestConstants.WRONG_CITY_EXAMPLE,
			TestConstants.DEFAULT_API_KEY);
		HourForecastData data = api.getForecast();
		assertNull(data);
	}

	/**
	 * Check the call to the REST Service from “OpenWeatherMap” by the
	 * coordinates.
	 */
	@Test
	public void getHourForecastByCoordinates() {
		HourForecastClient api = new HourForecastClient(TestConstants.LAT_EXAMPLE, TestConstants.LON_EXAMPLE,
			TestConstants.DEFAULT_API_KEY);
		HourForecastData data = api.getForecast();
		assertNotNull(data);
	}

	/**
	 * Check the call to the REST Service from “OpenWeatherMap” by wrong
	 * coordinates.
	 */
	@Test
	public void getHourForecastByWrongCoordinates() {
		HourForecastClient api = new HourForecastClient(100, -80, TestConstants.DEFAULT_API_KEY);
		HourForecastData data = api.getForecast();
		assertNull(data);
	}

}
