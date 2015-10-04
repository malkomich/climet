package org.malkomich.climet.domain;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.json.JSONObject;
import org.malkomich.climet.domain.Weather.WeatherClouds;
import org.malkomich.climet.domain.Weather.WeatherRain;
import org.malkomich.climet.domain.Weather.WeatherSnow;
import org.malkomich.climet.domain.Weather.WeatherState;
import org.malkomich.climet.domain.Weather.WeatherTemp;
import org.malkomich.climet.domain.Weather.WeatherWind;

public class CurrentWeatherData extends AbstractData {

	private Weather weather;

	public CurrentWeatherData(JSONObject json) {

		Calendar dateTime = GregorianCalendar.getInstance();
		dateTime.setTimeInMillis(json.getInt(OWN.DATETIME_SECS) * 1000L);

		JSONObject coordJSON = json.getJSONObject(OWN.COORD);
		String country = (json.getJSONObject(OWN.SYS)).getString(OWN.COUNTRY);
		String name = json.getString(OWN.NAME);
		int id = json.getInt(OWN.ID);
		city = new City(id, name, country, coordJSON);

		JSONObject stateJSON = (JSONObject) (json.getJSONArray(OWN.STATE)).get(0);
		JSONObject tempJSON = json.getJSONObject(OWN.MAIN);
		JSONObject windJSON = json.getJSONObject(OWN.WIND);
		JSONObject cloudsJSON = json.getJSONObject(OWN.CLOUDS);
		JSONObject rainJSON = json.optJSONObject(OWN.RAIN);
		JSONObject snowJSON = json.optJSONObject(OWN.SNOW);
		
		weather = new Weather(dateTime, stateJSON, tempJSON, windJSON, cloudsJSON, rainJSON, snowJSON);
	}

	public Weather getWeather() {
		return weather;
	}
	
	public Calendar getDateTime() {
		return weather.getDateTime();
	}
	
	public WeatherState getState() {
		return weather.getState();
	}

	public WeatherTemp getTemp() {
		return weather.getTemp();
	}

	public WeatherWind getWind() {
		return weather.getWind();
	}

	public WeatherClouds getClouds() {
		return weather.getClouds();
	}

	public WeatherRain getRain() {
		return weather.getRain();
	}

	public WeatherSnow getSnow() {
		return weather.getSnow();
	}
}
