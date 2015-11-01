package climet.domain;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.json.JSONObject;

import climet.domain.Weather.WeatherClouds;
import climet.domain.Weather.WeatherRain;
import climet.domain.Weather.WeatherSnow;
import climet.domain.Weather.WeatherState;
import climet.domain.Weather.WeatherTemp;
import climet.domain.Weather.WeatherWind;

public class CurrentWeatherData extends AbstractData {

	private Weather weather;

	public CurrentWeatherData(JSONObject json) {

		Calendar dateTime = GregorianCalendar.getInstance();
		dateTime.setTimeInMillis(json.getInt(OWM.DATETIME_SECS) * 1000L);

		JSONObject coordJSON = json.getJSONObject(OWM.COORD);
		String country = (json.getJSONObject(OWM.SYS)).getString(OWM.COUNTRY);
		String name = json.getString(OWM.NAME);
		int id = json.getInt(OWM.ID);
		city = new City(id, name, country, coordJSON);

		JSONObject stateJSON = (JSONObject) (json.getJSONArray(OWM.STATE)).get(0);
		JSONObject tempJSON = json.getJSONObject(OWM.MAIN);
		JSONObject windJSON = json.getJSONObject(OWM.WIND);
		JSONObject cloudsJSON = json.getJSONObject(OWM.CLOUDS);
		JSONObject rainJSON = json.optJSONObject(OWM.RAIN);
		JSONObject snowJSON = json.optJSONObject(OWM.SNOW);
		
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
