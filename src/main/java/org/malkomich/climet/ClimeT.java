package org.malkomich.climet;

import org.malkomich.climet.domain.CurrentWeatherData;
import org.malkomich.climet.domain.DateForecastData;
import org.malkomich.climet.domain.HourForecastData;
import org.malkomich.climet.exceptions.CityNotFoundException;

public class ClimeT {

	public static CurrentWeatherData getCurrentWeather(String town) throws CityNotFoundException {
		CurrentWeatherClient data = new CurrentWeatherClient(town);
		if(data.apiSuccess())
			return data.getWeather();
		throw new CityNotFoundException("Error");
	}
	
	public static CurrentWeatherData getCurrentWeather(float lat, float lon) throws CityNotFoundException {
		CurrentWeatherClient data = new CurrentWeatherClient(lat, lon);
		if(data.apiSuccess())
			return data.getWeather();
		throw new CityNotFoundException("Error");
	}
	
	public static HourForecastData getHourForecast(String town) throws CityNotFoundException {
		HourForecastClient data = new HourForecastClient(town);
		if(data.apiSuccess())
			return data.getForecast();
		throw new CityNotFoundException("Error");
	}
	
	public static HourForecastData getHourForecast(float lat, float lon) throws CityNotFoundException {
		HourForecastClient data = new HourForecastClient(lat, lon);
		if(data.apiSuccess())
			return data.getForecast();
		throw new CityNotFoundException("Error");
	}
	
	public static DateForecastData getDateForecast(String town) throws CityNotFoundException {
		DateForecastClient data = new DateForecastClient(town);
		if(data.apiSuccess())
			return data.getForecast();
		throw new CityNotFoundException("Error");
	}
	
	public static DateForecastData getHourForgetDateForecastcast(float lat, float lon) throws CityNotFoundException {
		DateForecastClient data = new DateForecastClient(lat, lon);
		if(data.apiSuccess())
			return data.getForecast();
		throw new CityNotFoundException("Error");
	}
}
