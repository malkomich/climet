package org.malkomich.climet;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestDateWeather {

	private static DateWeather weather;

	@BeforeClass
	public static void setUp() {
		long datetime = 1443800258;
		String description = "Clear";
		double tempDay = 10.5;
		double tempNight = 8.5;
		double tempEve = 11.5;
		double tempMorg = 9.5;
		double minTemp = 8.5;
		double maxTemp = 11.5;
		double pressure = 1000;
		double humidity = 10;
		double windSpeed = 10;
		double windDeg = 100;
		double clouds = 0;
		double rain = 0;
		double snow = 0;
		weather = new DateWeather(datetime, description, tempDay, tempNight, 
				tempEve, tempMorg, minTemp, maxTemp, pressure, 
				humidity, windSpeed, windDeg, clouds, rain, snow);
	}

	@Test
	public void getDayTemperature() {
		double temp = weather.getTemperature(DateWeather.DAY);
		assertEquals(10.5, temp, 0.0);
	}
	
	@Test
	public void getNightTemperature() {
		double temp = weather.getTemperature(DateWeather.NIGHT, Weather.FARENHEIT);
		assertEquals(47.3, temp, 0.0);
	}
	
	@Test
	public void getEveTemperature() {
		double temp = weather.getTemperature(DateWeather.EVE);
		assertEquals(11.5, temp, 0.0);
	}
	
	@Test
	public void getMorgTemperature() {
		double temp = weather.getTemperature(DateWeather.MORG, Weather.KELVIN);
		assertEquals(282.65, temp, 0.0);
	}

}
