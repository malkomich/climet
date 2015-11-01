package climet.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class DateForecastData extends AbstractData {

	private List<Weather> forecast;

	public DateForecastData(JSONObject json) {

		JSONObject cityJSON = json.getJSONObject(OWM.CITY);
		city = new City(cityJSON);

		JSONArray list = json.getJSONArray(OWM.LIST);
		forecast = new ArrayList<>(list.length());

		for (int i = 0; i < list.length(); i++) {
			JSONObject jsonItem = (JSONObject) list.get(i);

			Calendar dateTime = GregorianCalendar.getInstance();
			dateTime.setTimeInMillis(jsonItem.getInt(OWM.DATETIME_SECS) * 1000L);

			JSONObject stateJSON = (JSONObject) jsonItem.getJSONArray(OWM.STATE).get(0);
			JSONObject tempJSON = jsonItem.getJSONObject(OWM.TEMP);
			if (jsonItem.has(OWM.PRESSURE))
				tempJSON.put(OWM.PRESSURE, jsonItem.getDouble(OWM.PRESSURE));
			if (jsonItem.has(OWM.HUMIDITY))
				tempJSON.put(OWM.HUMIDITY, jsonItem.getDouble(OWM.HUMIDITY));
			JSONObject windJSON = null;
			if (jsonItem.has(OWM.SPEED) && jsonItem.has(OWM.DEG)) {
				windJSON = new JSONObject();
				windJSON.put(OWM.SPEED, jsonItem.getDouble(OWM.SPEED));
				windJSON.put(OWM.DEG, jsonItem.getInt(OWM.DEG));
			}
			JSONObject cloudsJSON = null;
			if (jsonItem.has(OWM.CLOUDS)) {
				cloudsJSON = new JSONObject();
				cloudsJSON.put(OWM.ALL, jsonItem.getInt(OWM.CLOUDS));
			}
			JSONObject rainJSON = null;
			if (jsonItem.has(OWM.RAIN)) {
				rainJSON = new JSONObject();
				rainJSON.put("1", jsonItem.getDouble(OWM.RAIN));
			}
			JSONObject snowJSON = null;
			if (jsonItem.has(OWM.SNOW)) {
				snowJSON = new JSONObject();
				snowJSON.put("1", jsonItem.getDouble(OWM.SNOW));
			}

			Weather weather = new Weather(dateTime, stateJSON, tempJSON, windJSON, cloudsJSON, rainJSON, snowJSON);
			forecast.add(weather);
		}
	}

	public List<Weather> getForecast() {
		return forecast;
	}
}
