package org.malkomich.climet.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class HourForecastData extends AbstractData {

	private List<Weather> forecast;

	public HourForecastData(JSONObject json) {

		JSONObject cityJSON = json.getJSONObject(OWN.CITY);
		city = new City(cityJSON);

		JSONArray list = json.getJSONArray(OWN.LIST);
		forecast = new ArrayList<>(list.length());
		
		for (int i = 0; i < list.length(); i++) {
			JSONObject jsonItem = (JSONObject) list.get(i);
			
			Calendar dateTime = GregorianCalendar.getInstance();
			dateTime.setTimeInMillis(jsonItem.getInt(OWN.DATETIME_SECS) * 1000L);

			JSONObject stateJSON = (JSONObject) jsonItem.getJSONArray(OWN.STATE).get(0);
			JSONObject tempJSON = jsonItem.getJSONObject(OWN.MAIN);
			JSONObject windJSON = jsonItem.getJSONObject(OWN.WIND);
			JSONObject cloudsJSON = jsonItem.getJSONObject(OWN.CLOUDS);
			JSONObject rainJSON = jsonItem.optJSONObject(OWN.RAIN);
			JSONObject snowJSON = jsonItem.optJSONObject(OWN.SNOW);
			
			Weather weather = new Weather(dateTime, stateJSON, tempJSON, windJSON, cloudsJSON, rainJSON, snowJSON);
			forecast.add(weather);
		}
	}

	public List<Weather> getForecast() {
		return forecast;
	}

}
