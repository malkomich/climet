package climet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONObject;

public abstract class AbstractRequester {

	private final String API_KEY = "26270deb633eb0d3251817f98eec72c1";
	private final String OWN_COD = "cod";

	protected AbstractRequester(String town) {
		String path = getBaseURL() + "?q=" + town.toLowerCase() + apiKeySuffix();
		doRequest(path);
	}

	protected AbstractRequester(float lat, float lon) {
		String path = getBaseURL() + "?lat=" + lat + "&lon=" + lon + apiKeySuffix();
		doRequest(path);
	}

	/**
	 * Calls the REST API to retrieve the String data, and then calls to the
	 * parser.
	 * 
	 * @param path
	 *            Service URL
	 */
	private void doRequest(String path) {

		StringBuilder output = new StringBuilder();

		try {
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String line;
			while ((line = br.readLine()) != null) {
				output.append(line);
			}

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

		parseOutput(output.toString());

	}

	/**
	 * Parse a String well JSON-formed to create the domain classes of Weather
	 * and Town.
	 * 
	 * @param output
	 *            Service Data result
	 */
	private void parseOutput(String output) {

		JSONObject json = new JSONObject(output);

		int cod = json.getInt(OWN_COD);
		if (cod == 200) {
			init(json);
		}
	}

	/**
	 * Retrieves the portion of the URL to specify the API key
	 * 
	 * @return URL portion
	 */
	private String apiKeySuffix() {
		return "&APPID=" + API_KEY;
	}

	/**
	 * Initialize weather objects and save the information to them.
	 * 
	 * @param json
	 *            JSON data to parse
	 */
	protected abstract void init(JSONObject json);

	/**
	 * Retrieves the root path of the Web Service.
	 * 
	 * @return URL root
	 */
	protected abstract String getBaseURL();

	/**
	 * Check if domain objects has been set.
	 * 
	 * @return Success flag
	 */
	protected abstract boolean apiSuccess();

}
