package org.malkomich.climet;

import org.json.JSONObject;
import org.malkomich.climet.domain.HourForecastData;

public class HourForecastClient extends AbstractRequester {

	private final String baseUrl = "http://api.openweathermap.org/data/2.5/forecast";

	private HourForecastData forecastData;

	public HourForecastClient(String town) {
		super(town);
	}

	public HourForecastClient(float lat, float lon) {
		super(lat, lon);
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
