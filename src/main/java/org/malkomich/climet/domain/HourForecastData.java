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

		JSONObject cityJSON = json.getJSONObject(OWM.CITY);
		city = new City(cityJSON);

		JSONArray list = json.getJSONArray(OWM.LIST);
		forecast = new ArrayList<>(list.length());
		
		for (int i = 0; i < list.length(); i++) {
			JSONObject jsonItem = (JSONObject) list.get(i);
			
			Calendar dateTime = GregorianCalendar.getInstance();
			dateTime.setTimeInMillis(jsonItem.getInt(OWM.DATETIME_SECS) * 1000L);

			JSONObject stateJSON = (JSONObject) jsonItem.getJSONArray(OWM.STATE).get(0);
			JSONObject tempJSON = jsonItem.getJSONObject(OWM.MAIN);
			JSONObject windJSON = jsonItem.getJSONObject(OWM.WIND);
			JSONObject cloudsJSON = jsonItem.getJSONObject(OWM.CLOUDS);
			JSONObject rainJSON = jsonItem.optJSONObject(OWM.RAIN);
			JSONObject snowJSON = jsonItem.optJSONObject(OWM.SNOW);
			
			Weather weather = new Weather(dateTime, stateJSON, tempJSON, windJSON, cloudsJSON, rainJSON, snowJSON);
			forecast.add(weather);
		}
	}

	public List<Weather> getForecast() {
		return forecast;
	}

}
