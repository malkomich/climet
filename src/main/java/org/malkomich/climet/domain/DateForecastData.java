package org.malkomich.climet.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class DateForecastData extends AbstractData {

	private List<Weather> forecast;

	public DateForecastData(JSONObject json) {

		JSONObject cityJSON = json.getJSONObject(OWN.CITY);
		town = new Town(cityJSON);

		JSONArray list = json.getJSONArray(OWN.LIST);
		forecast = new ArrayList<>(list.length());

		for (int i = 0; i < list.length(); i++) {
			JSONObject jsonItem = (JSONObject) list.get(i);

			Calendar dateTime = GregorianCalendar.getInstance();
			dateTime.setTimeInMillis(jsonItem.getInt(OWN.DATETIME_SECS) * 1000L);

			JSONObject stateJSON = (JSONObject) jsonItem.getJSONArray(OWN.STATE).get(0);
			JSONObject tempJSON = jsonItem.getJSONObject(OWN.TEMP);
			if (jsonItem.has(OWN.PRESSURE))
				tempJSON.put(OWN.PRESSURE, jsonItem.getDouble(OWN.PRESSURE));
			if (jsonItem.has(OWN.HUMIDITY))
				tempJSON.put(OWN.HUMIDITY, jsonItem.getDouble(OWN.HUMIDITY));
			JSONObject windJSON = null;
			if (jsonItem.has(OWN.SPEED) && jsonItem.has(OWN.DEG)) {
				windJSON = new JSONObject();
				windJSON.put(OWN.SPEED, jsonItem.getDouble(OWN.SPEED));
				windJSON.put(OWN.DEG, jsonItem.getInt(OWN.DEG));
			}
			JSONObject cloudsJSON = null;
			if (jsonItem.has(OWN.CLOUDS)) {
				cloudsJSON = new JSONObject();
				cloudsJSON.put(OWN.ALL, jsonItem.getInt(OWN.CLOUDS));
			}
			JSONObject rainJSON = null;
			if (jsonItem.has(OWN.RAIN)) {
				rainJSON = new JSONObject();
				rainJSON.put("1", jsonItem.getDouble(OWN.RAIN));
			}
			JSONObject snowJSON = null;
			if (jsonItem.has(OWN.SNOW)) {
				snowJSON = new JSONObject();
				snowJSON.put("1", jsonItem.getDouble(OWN.SNOW));
			}

			Weather weather = new Weather(dateTime, stateJSON, tempJSON, windJSON, cloudsJSON, rainJSON, snowJSON);
			forecast.add(weather);
		}
	}

	public List<Weather> getForecast() {
		return forecast;
	}
}
