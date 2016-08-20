package climet;

import org.json.JSONObject;

import climet.domain.HourForecastData;

public class HourForecastClient extends AbstractRequester {

	private final String baseUrl = "http://api.openweathermap.org/data/2.5/forecast";

	private HourForecastData forecastData;

	public HourForecastClient(String town, String apiKey) {
		super(town, apiKey);
	}

	public HourForecastClient(float lat, float lon, String apiKey) {
		super(lat, lon, apiKey);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.malkomich.climet.AbstractRequester#init(org.json.JSONObject)
	 */
	@Override
	protected void init(JSONObject json) {
		forecastData = new HourForecastData(json);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.malkomich.climet.AbstractRequester#apiSuccess()
	 */
	@Override
	public boolean apiSuccess() {
		if (forecastData != null)
			return true;
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.malkomich.climet.AbstractRequester#getBaseURL()
	 */
	@Override
	protected String getBaseURL() {
		return baseUrl;
	}

	public HourForecastData getForecast() {
		return forecastData;
	}

}
