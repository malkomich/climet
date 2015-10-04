package org.malkomich.climet.domain;

import java.util.Calendar;
import java.util.Iterator;

import org.json.JSONObject;

public class Weather {

	private Calendar dateTime;
	private WeatherState state;
	private WeatherTemp temp;
	private WeatherWind wind;
	private WeatherClouds clouds;
	private WeatherRain rain;
	private WeatherSnow snow;

	public Weather(Calendar datetime, JSONObject state, JSONObject temp, JSONObject wind, JSONObject clouds,
			JSONObject rain, JSONObject snow) {
		this.dateTime = datetime;
		this.state = (state != null) ? new WeatherState(state) : null;
		this.temp = (temp != null) ? new WeatherTemp(temp) : null;
		this.wind = (wind != null) ? new WeatherWind(wind) : null;
		this.clouds = (clouds != null) ? new WeatherClouds(clouds) : null;
		this.rain = (rain != null) ? new WeatherRain(rain) : null;
		this.snow = (snow != null) ? new WeatherSnow(snow) : null;
	}

	public class WeatherState {

		private static final String OWM_MAIN = "main";
		private static final String OWN_DESCRIPTION = "description";
		private static final String OWN_ICON = "icon";

		private String main;
		private String description;
		private String icon;

		WeatherState(JSONObject json) {
			main = json.optString(OWM_MAIN);
			description = json.optString(OWN_DESCRIPTION);
			icon = json.optString(OWN_ICON);
		}

		public String getMain() {
			return main;
		}

		public String getDescription() {
			return description;
		}

		public String getIcon() {
			return icon;
		}
	}

	public class WeatherTemp {

		private static final String OWN_TEMPERATURE = "temp";
		private static final String OWN_MIN_TEMP = "temp_min";
		private static final String OWN_MAX_TEMP = "temp_max";
		private static final String OWN_HUMIDITY = "humidity";
		private static final String OWN_PRESSURE = "pressure";

		public static final int CELSIUS = 0;
		public static final int FARENHEIT = 1;
		public static final int KELVIN = 2;

		private static final double CELSIUS_TO_KELVIN = 273.15;

		private double temperature;
		private int pressure;
		private int humidity;
		private double minTemp;
		private double maxTemp;

		WeatherTemp(JSONObject json) {
			temperature = json.optDouble(OWN_TEMPERATURE);
			pressure = json.optInt(OWN_PRESSURE);
			humidity = json.optInt(OWN_HUMIDITY);
			minTemp = json.optDouble(OWN_MIN_TEMP);
			maxTemp = json.optDouble(OWN_MAX_TEMP);

		}

		public double getTemperature() {
			return getTemperature(WeatherTemp.KELVIN);
		}

		public int getPressure() {
			return pressure;
		}

		public int getHumidity() {
			return humidity;
		}

		public double getMinTemp() {
			return minTemp;
		}

		public double getMaxTemp() {
			return maxTemp;
		}

		public double getTemperature(int type) {

			switch (type) {
			case WeatherTemp.CELSIUS:
				return temperature - CELSIUS_TO_KELVIN;
			case WeatherTemp.KELVIN:
				return temperature;
			case WeatherTemp.FARENHEIT:
				return (temperature - CELSIUS_TO_KELVIN) * 1.8 + 32;

			default:
				throw new IllegalArgumentException("Invalid measurement");
			}
		}
	}

	public class WeatherWind {

		private double speed;
		private int deg;

		WeatherWind(JSONObject json) {
			speed = json.optDouble(OWN.SPEED);
			deg = json.optInt(OWN.DEG);
		}

		public double getSpeed() {
			return speed;
		}

		public int getDeg() {
			return deg;
		}
	}

	public class WeatherClouds {

		private int all;

		WeatherClouds(JSONObject json) {
				all = json.optInt(OWN.ALL);
		}

		public int getAll() {
			return all;
		}
	}

	public class WeatherRain {

		private String units;
		private double value;

		@SuppressWarnings("unchecked")
		WeatherRain(JSONObject json) {
			Iterator<String> it = json.keys();
			if (it.hasNext()) {
				units = it.next();
				value = json.getDouble(units);
			}
		}

		public String getUnits() {
			return units;
		}

		public double getValue() {
			return value;
		}
	}

	public class WeatherSnow {

		private String units;
		private double value;

		@SuppressWarnings("unchecked")
		WeatherSnow(JSONObject json) {
			Iterator<String> it = json.keys();
			if (it.hasNext()) {
				units = it.next();
				value = json.getDouble(units);
			}
		}

		public String getUnits() {
			return units;
		}

		public double getValue() {
			return value;
		}
	}

	public Calendar getDateTime() {
		return dateTime;
	}

	public WeatherState getState() {
		return state;
	}

	public WeatherTemp getTemp() {
		return temp;
	}

	public WeatherWind getWind() {
		return wind;
	}

	public WeatherClouds getClouds() {
		return clouds;
	}

	public WeatherRain getRain() {
		return rain;
	}

	public WeatherSnow getSnow() {
		return snow;
	}
}
