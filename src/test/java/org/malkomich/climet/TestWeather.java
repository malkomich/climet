package org.malkomich.climet;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestWeather {

	private static Weather weather;

	@BeforeClass
	public static void setUp() {
		long datetime = 1443800258;
		String description = "Clear";
		double temp = 10.5;
		double minTemp = 9.5;
		double maxTemp = 11.5;
		double pressure = 1000;
		double humidity = 10;
		double windSpeed = 10;
		double windDeg = 100;
		double clouds = 0;
		double rain = 0;
		double snow = 0;
		weather = new Weather(datetime, description, temp, minTemp, maxTemp, pressure, 
				humidity, windSpeed, windDeg, clouds, rain, snow);
	}

	/**
	 * Get a valid temperature of a town in Celsius.
	 */
	@Test
	public void getCelsiusTemperature() {
		double temp = weather.getTemperature();
		assertEquals(10.5, temp, 0.0);
	}

	/**
	 * Get a valid temperature of a town in Farenheit.
	 */
	@Test
	public void getFarenheitTemperature() {
		double temp = weather.getTemperature(Weather.FARENHEIT);
		assertEquals(50.9, temp, 0.1);
	}

	/**
	 * Get a valid temperature of a town in Kelvin.
	 */
	@Test
	public void getKelvinTemperature() {
		double temp = weather.getTemperature(Weather.KELVIN);
		assertEquals(283.65, temp, 0.01);
	}

}
