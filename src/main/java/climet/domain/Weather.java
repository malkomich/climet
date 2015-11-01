package climet.domain;

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
	public static class WeatherState {

		private static final String OWM_ID = "id";
		private static final String OWM_MAIN = "main";
		private static final String OWN_ICON = "icon";
		
		private static final int INVALID = -1;
		
		public static enum StateDescription {
			UNKNOWN                         (INVALID),
			/* Thunderstorm */
			THUNDERSTORM_WITH_LIGHT_RAIN    (200),
			THUNDERSTORM_WITH_RAIN          (201),
			THUNDERSTORM_WITH_HEAVY_RAIN    (202),
			LIGHT_THUNDERSTORM              (210),
			THUNDERSTORM                    (211),
			HEAVY_THUNDERSTORM              (212),
			RAGGED_THUNDERSTORM             (221),
			THUNDERSTORM_WITH_LIGHT_DRIZZLE (230),
			THUNDERSTORM_WITH_DRIZZLE       (231),
			THUNDERSTORM_WITH_HEAVY_DRIZZLE (232),
			/* Drizzle */
			LIGHT_INTENSITY_DRIZZLE         (300),
			DRIZZLE                         (301),
			HEAVY_INTENSITY_DRIZZLE         (302),
			LIGHT_INTENSITY_DRIZZLE_RAIN    (310),
			DRIZZLE_RAIN                    (311),
			HEAVY_INTENSITY_DRIZZLE_RAIN    (312),
			SHOWER_DRIZZLE                  (321),
			/* Rain */
			LIGHT_RAIN                      (500),
			MODERATE_RAIN                   (501),
			HEAVY_INTENSITY_RAIN            (502),
			VERY_HEAVY_RAIN                 (503),
			EXTREME_RAIN                    (504),
			FREEZING_RAIN                   (511),
			LIGHT_INTENSITY_SHOWER_RAIN     (520),
			SHOWER_RAIN                     (521),
			HEAVY_INTENSITY_SHOWER_RAIN     (522),
			/* Snow */
			LIGHT_SNOW                      (600),
			SNOW                            (601),
			HEAVY_SNOW                      (602),
			SLEET                           (611),
			SHOWER_SNOW                     (621),
			/* Atmosphere */
			MIST                            (701),
			SMOKE                           (711),
			HAZE                            (721),
			SAND_OR_DUST_WHIRLS             (731),
			FOG                             (741),
			/* Clouds */
			SKY_IS_CLEAR                    (800),
			FEW_CLOUDS                      (801),
			SCATTERED_CLOUDS                (802),
			BROKEN_CLOUDS                   (803),
			OVERCAST_CLOUDS                 (804),
			/* Extreme */
			TORNADO                         (900),
			TROPICAL_STORM                  (901),
			HURRICANE                       (902),
			COLD                            (903),
			HOT                             (904),
			WINDY                           (905),
			HAIL                            (906);

			private int id;
			private StateDescription (int code) {
				this.id = code;
			}

			static public StateDescription valueof (int id) {
				for (StateDescription condition : StateDescription.values ()) {
					if (condition.id == id)
						return condition;
				}
				return StateDescription.UNKNOWN;
			}

			public int getId () {
				return this.id;
			}
		}

		private StateDescription description;
		private String main;
		private String icon;

		WeatherState(JSONObject json) {
			description = StateDescription.valueof(json.optInt(OWM_ID, INVALID));
			main = json.optString(OWM_MAIN);
			icon = json.optString(OWN_ICON);
		}

		/**
		 * Gets a summary of the weather state
		 * 
		 * @return
		 */
		public StateDescription getCode() {
			return description;
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
	public static class WeatherTemp {

		private static final String OWN_TEMPERATURE = "temp";
		private static final String OWN_MIN_TEMP = "temp_min";
		private static final String OWN_MAX_TEMP = "temp_max";
		private static final String OWN_HUMIDITY = "humidity";
		private static final String OWN_PRESSURE = "pressure";

		private static final float CELSIUS_TO_KELVIN = 273.15f;
		private static final int TEMP_PRECISION = 2;

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
	public static class WeatherWind {

		private float speed;
		private int degrees;

		WeatherWind(JSONObject json) {
			speed = new Float(json.optDouble(OWM.SPEED));
			degrees = json.optInt(OWM.DEG);
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
	public static class WeatherClouds {

		private int cloudiness;

		WeatherClouds(JSONObject json) {
			cloudiness = json.optInt(OWM.ALL);
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
	public static class WeatherRain {

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
	public static class WeatherSnow {

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
