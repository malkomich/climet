package org.malkomich.climet;

import org.json.JSONObject;
import org.malkomich.climet.domain.DateForecastData;

public class DateForecastClient extends AbstractRequester {

	private final String baseUrl = "http://api.openweathermap.org/data/2.5/forecast/daily";

	private DateForecastData forecastData;

	public DateForecastClient(String town) {
		super(town);
	}

	public DateForecastClient(float lat, float lon) {
		super(lat, lon);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.malkomich.climet.AbstractRequester#init(org.json.JSONObject)
	 */
	@Override
	protected void init(JSONObject json) {
		forecastData = new DateForecastData(json);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.malkomich.climet.AbstractRequester#apiSuccess()
	 */
	@Override
	protected boolean apiSuccess() {
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

	public DateForecastData getForecast() {
		return forecastData;
	}

}
