package org.malkomich.climet.domain;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Iterator;

import org.json.JSONObject;

public class Weather {

	public static final int CELSIUS = 0;
	public static final int FARENHEIT = 1;
	public static final int KELVIN = 2;

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
		this.state = new WeatherState(state);
		this.temp = new WeatherTemp(temp);
		this.wind = new WeatherWind(wind);
		this.clouds = new WeatherClouds(clouds);
		this.rain = new WeatherRain(rain);
		this.snow = new WeatherSnow(snow);
	}

	/**
	 * Summary of the weather situation
	 * 
	 * @author malkomich
	 *
	 */
	public class WeatherState {

		private final String OWM_MAIN = "main";
		private final String OWN_DESCRIPTION = "description";
		private final String OWN_ICON = "icon";

		private String main;
		private String description;
		private String icon;

		WeatherState(JSONObject json) {
			main = json.optString(OWM_MAIN);
			description = json.optString(OWN_DESCRIPTION);
			icon = json.optString(OWN_ICON);
		}

		/**
		 * Gets the headline of the weather state
		 * 
		 * @return
		 */
		public String getMain() {
			return main;
		}

		/**
		 * Gets a summary of the weather state
		 * 
		 * @return
		 */
		public String getDescription() {
			return description;
		}

		/**
		 * Gets an identifier of the icon for the weather state
		 * (http://openweathermap.org/)
		 * 
		 * @return
		 */
		public String getIcon() {
			return icon;
		}
	}

	/**
	 * Mainly properties of the weather
	 * 
	 * @author malkomich
	 *
	 */
	public class WeatherTemp {

		private final String OWN_TEMPERATURE = "temp";
		private final String OWN_MIN_TEMP = "temp_min";
		private final String OWN_MAX_TEMP = "temp_max";
		private final String OWN_HUMIDITY = "humidity";
		private final String OWN_PRESSURE = "pressure";

		private final float CELSIUS_TO_KELVIN = 273.15f;
		private final int TEMP_PRECISION = 2;

		private float currentTemp;
		private int pressure;
		private int humidity;
		private float minTemp;
		private float maxTemp;

		WeatherTemp(JSONObject json) {
			currentTemp = new Float(json.optDouble(OWN_TEMPERATURE));
			pressure = json.optInt(OWN_PRESSURE);
			humidity = json.optInt(OWN_HUMIDITY);
			minTemp = new Float(json.optDouble(OWN_MIN_TEMP));
			maxTemp = new Float(json.optDouble(OWN_MAX_TEMP));

		}

		/**
		 * Gets the current temperature in Kelvin by default
		 * 
		 * @return
		 */
		public float getCurrentTemp() {
			return getCurrentTemp(KELVIN);
		}

		/**
		 * Gets the atmospheric pressure in hPa
		 * 
		 * @return
		 */
		public int getPressure() {
			return pressure;
		}

		/**
		 * Gets the percentage of humidity
		 * 
		 * @return
		 */
		public int getHumidity() {
			return humidity;
		}

		/**
		 * Gets the minimum temperature in Kelvin by default
		 * 
		 * @return
		 */
		public float getMinTemp() {
			return minTemp;
		}

		/**
		 * Gets the maximum temperature in Kelvin by default
		 * 
		 * @return
		 */
		public float getMaxTemp() {
			return maxTemp;
		}

		/**
		 * Gets the current temperature in a specific measure between Kelvin,
		 * Celsius or Farenheit
		 * 
		 * @param type
		 * @return
		 */
		public float getCurrentTemp(int type) {
			return unitConversion(currentTemp, type);
		}

		/**
		 * Gets the minimum temperature in a specific measure between Kelvin,
		 * Celsius or Farenheit
		 * 
		 * @param type
		 * @return
		 */
		public float getMinTemp(int type) {
			return unitConversion(minTemp, type);
		}

		/**
		 * Gets the maximum temperature in a specific measure between Kelvin,
		 * Celsius or Farenheit
		 * 
		 * @param type
		 * @return
		 */
		public float getMaxTemp(int type) {
			return unitConversion(maxTemp, type);
		}

		/**
		 * Convert a temperature unit to another type of measure
		 * 
		 * @param value
		 * @param type
		 * @return
		 */
		private float unitConversion(float value, int type) {

			float newValue;

			switch (type) {
			case CELSIUS:
				newValue = value - CELSIUS_TO_KELVIN;
				break;
			case KELVIN:
				return value;
			case FARENHEIT:
				newValue = (value - CELSIUS_TO_KELVIN) * 1.8f + 32;
				break;
			default:
				throw new IllegalArgumentException("Invalid measurement");
			}

			BigDecimal bd = new BigDecimal(newValue);
			bd = bd.setScale(TEMP_PRECISION, BigDecimal.ROUND_UP);
			return bd.floatValue();
		}
	}

	/**
	 * Wind information
	 * 
	 * @author malkomich
	 *
	 */
	public class WeatherWind {

		private float speed;
		private int degrees;

		WeatherWind(JSONObject json) {
			speed = new Float(json.optDouble(OWN.SPEED));
			degrees = json.optInt(OWN.DEG);
		}

		/**
		 * Gets the wind speed in meter/second
		 * 
		 * @return
		 */
		public float getSpeed() {
			return speed;
		}

		/**
		 * Gets the direction of the wind in degrees
		 * 
		 * @return
		 */
		public int getDegrees() {
			return degrees;
		}
	}

	/**
	 * Cloudiness information
	 * 
	 * @author malkomich
	 *
	 */
	public class WeatherClouds {

		private int cloudiness;

		WeatherClouds(JSONObject json) {
			cloudiness = json.optInt(OWN.ALL);
		}

		/**
		 * Gets the percentage of cloudiness
		 * 
		 * @return
		 */
		public int getCloudiness() {
			return cloudiness;
		}
	}

	/**
	 * Precipitation information
	 * 
	 * @author malkomich
	 *
	 */
	public class WeatherRain {

		private int hours;
		private float value = 0;

		@SuppressWarnings("unchecked")
		WeatherRain(JSONObject json) {
			if (json != null) {
				Iterator<String> it = json.keys();
				if (it.hasNext()) {
					String units = it.next();
					hours = Integer.parseInt(units.split("h")[0]);
					value = new Float(json.getDouble(units));
				}
			}
		}

		/**
		 * Gets the amount of hours since it is rainning
		 * 
		 * @return
		 */
		public int getHours() {
			return hours;
		}

		/**
		 * Gets the volume of precipitation in liters per square meter
		 * 
		 * @return
		 */
		public float getValue() {
			return value;
		}
	}

	/**
	 * Precipitation information
	 * 
	 * @author malkomich
	 *
	 */
	public class WeatherSnow {

		private int hours = 0;
		private float value = 0;

		@SuppressWarnings("unchecked")
		WeatherSnow(JSONObject json) {
			if (json != null) {
				Iterator<String> it = json.keys();
				if (it.hasNext()) {
					String units = it.next();
					hours = Integer.parseInt(units.split("h")[0]);
					value = new Float(json.getDouble(units));
				}
			}
		}

		/**
		 * Gets the amount of hours since it is snowing
		 * 
		 * @return
		 */
		public int getHours() {
			return hours;
		}

		/**
		 * Gets the volume of precipitation in liters per square meter
		 * 
		 * @return
		 */
		public float getValue() {
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
