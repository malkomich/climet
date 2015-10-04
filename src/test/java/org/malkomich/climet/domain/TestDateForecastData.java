package org.malkomich.climet.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.json.JSONObject;
import org.junit.Test;

public class TestDateForecastData {

	@Test
	public void checkData() {
		JSONObject json = new JSONObject("{\"city\":{\"id\":3106672,\"name\":\"Valladolid\",\"coord\":{\"lon\":-4.72372,\"lat\":41.655182},\"country\":\"ES\",\"population\":0},"
				+ "\"cod\":\"200\",\"message\":0.0223,\"cnt\":5,\"list\":[{\"dt\":1443873600,\"temp\":{\"day\":288.53,\"min\":281.72,\"max\":288.53,\"night\":281.72,\"eve\":288.53,\"morn\":288.53},"
				+ "\"pressure\":938.06,\"humidity\":44,\"weather\":[{\"id\":802,\"main\":\"Clouds\",\"description\":\"scattered clouds\",\"icon\":\"03n\"}],\"speed\":2.36,"
				+ "\"deg\":235,\"clouds\":32},{\"dt\":1443960000,\"temp\":{\"day\":293.76,\"min\":283.67,\"max\":293.76,\"night\":291.03,\"eve\":290.69,\"morn\":283.67},"
				+ "\"pressure\":934.97,\"humidity\":68,\"weather\":[{\"id\":501,\"main\":\"Rain\",\"description\":\"moderate rain\",\"icon\":\"10d\"}],\"speed\":11.27,"
				+ "\"deg\":217,\"clouds\":92,\"rain\":9.83},{\"dt\":1444046400,\"temp\":{\"day\":291.92,\"min\":287.72,\"max\":293,\"night\":288.23,\"eve\":289.5,\"morn\":289.87},"
				+ "\"pressure\":935.85,\"humidity\":91,\"weather\":[{\"id\":501,\"main\":\"Rain\",\"description\":\"moderate rain\",\"icon\":\"10d\"}],\"speed\":11.03,\"deg\":213,"
				+ "\"clouds\":24,\"rain\":9.16},{\"dt\":1444132800,\"temp\":{\"day\":288.55,\"min\":280.64,\"max\":288.59,\"night\":280.64,\"eve\":286.6,\"morn\":286.69},"
				+ "\"pressure\":937.31,\"humidity\":90,\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"speed\":8.85,\"deg\":243,"
				+ "\"clouds\":24,\"rain\":0.34},{\"dt\":1444219200,\"temp\":{\"day\":289.08,\"min\":276.66,\"max\":289.08,\"night\":279.65,\"eve\":286.57,\"morn\":276.66},"
				+ "\"pressure\":937.67,\"humidity\":0,\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"speed\":1.72,\"deg\":229,\"clouds\":7}]}");
		
		DateForecastData data = new DateForecastData(json);
		List<Weather> forecast = data.getForecast();
		assertFalse(forecast.isEmpty());
		assertNotNull(data.getCity());
	}
}
