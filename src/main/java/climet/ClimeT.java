package climet;

import climet.domain.CurrentWeatherData;
import climet.domain.DateForecastData;
import climet.domain.HourForecastData;
import climet.exceptions.CityNotFoundException;

public class ClimeT {

	/**
	 * Gets Current Weather by City.
	 * @param city City or Town to find
	 * @return Current weather information POJO
	 * @throws CityNotFoundException City Not Found
	 */
	public static CurrentWeatherData getCurrentWeather(String city) throws CityNotFoundException {
		CurrentWeatherClient data = new CurrentWeatherClient(city);
		if(data.apiSuccess())
			return data.getWeather();
		throw new CityNotFoundException("Error");
	}
	
	/**
	 * Gets Current Weather by coordinates of the geo location.
	 * @param lat Latitude
	 * @param lon Longitude
	 * @return Current weather information POJO
	 * @throws CityNotFoundException City Not Found
	 */
	public static CurrentWeatherData getCurrentWeather(float lat, float lon) throws CityNotFoundException {
		CurrentWeatherClient data = new CurrentWeatherClient(lat, lon);
		if(data.apiSuccess())
			return data.getWeather();
		throw new CityNotFoundException("Error");
	}
	
	/**
	 * Gets Current Weather by coordinates of the geo location.
	 * @param lat Latitude
	 * @param lon Longitude
	 * @return Current weather information POJO
	 * @throws CityNotFoundException City Not Found
	 */
	public static CurrentWeatherData getCurrentWeather(double lat, double lon) throws CityNotFoundException {
		return getCurrentWeather(new Float(lat), new Float(lon));
	}
	
	/**
	 * Gets Hour Forecast by City.
	 * @param city City or Town to find
	 * @return Forecast information POJO
	 * @throws CityNotFoundException City Not Found
	 */
	public static HourForecastData getHourForecast(String city) throws CityNotFoundException {
		HourForecastClient data = new HourForecastClient(city);
		if(data.apiSuccess())
			return data.getForecast();
		throw new CityNotFoundException("Error");
	}
	
	/**
	 * Gets Hour Forecast by coordinates of the geo location.
	 * @param lat Latitude
	 * @param lon Longitude
	 * @return Forecast information POJO
	 * @throws CityNotFoundException City Not Found
	 */
	public static HourForecastData getHourForecast(float lat, float lon) throws CityNotFoundException {
		HourForecastClient data = new HourForecastClient(lat, lon);
		if(data.apiSuccess())
			return data.getForecast();
		throw new CityNotFoundException("Error");
	}
	
	/**
	 * Gets Hour Forecast by coordinates of the geo location.
	 * @param lat Latitude
	 * @param lon Longitude
	 * @return Forecast information POJO
	 * @throws CityNotFoundException City Not Found
	 */
	public static HourForecastData getHourForecast(double lat, double lon) throws CityNotFoundException {
		return getHourForecast(new Float(lat), new Float(lon));
	}
	
	/**
	 * Gets Date Forecast by City.
	 * @param city City or Town to find
	 * @return Forecast information POJO
	 * @throws CityNotFoundException City Not Found
	 */
	public static DateForecastData getDateForecast(String city) throws CityNotFoundException {
		DateForecastClient data = new DateForecastClient(city);
		if(data.apiSuccess())
			return data.getForecast();
		throw new CityNotFoundException("Error");
	}
	
	/**
	 * Gets Date Forecast by coordinates of the geo location.
	 * @param lat Latitude
	 * @param lon Longitude
	 * @return Forecast information POJO
	 * @throws CityNotFoundException City Not Found
	 */
	public static DateForecastData getHourForgetDateForecastcast(float lat, float lon) throws CityNotFoundException {
		DateForecastClient data = new DateForecastClient(lat, lon);
		if(data.apiSuccess())
			return data.getForecast();
		throw new CityNotFoundException("Error");
	}
	
	/**
	 * Gets Date Forecast by coordinates of the geo location.
	 * @param lat Latitude
	 * @param lon Longitude
	 * @return Forecast information POJO
	 * @throws CityNotFoundException City Not Found
	 */
	public static DateForecastData getHourForgetDateForecastcast(double lat, double lon) throws CityNotFoundException {
		return getHourForgetDateForecastcast(new Float(lat), new Float(lon));
	}
}
