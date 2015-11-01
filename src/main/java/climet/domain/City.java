package climet.domain;

import org.json.JSONObject;

public class City {

	private int id;
	private String name;
	private String countryCode;
	private Coordinates coordinates;

	public City(int id, String name, String country, JSONObject coordJSON) {
		this.id = id;
		this.name = name;
		countryCode = country;
		coordinates = (coordJSON != null) ? new Coordinates(coordJSON) : null;
	}

	public City(JSONObject json) {
		id = json.optInt(OWM.ID);
		name = json.optString(OWM.NAME);
		JSONObject coordJSON = json.optJSONObject(OWM.COORD);
		coordinates = (coordJSON != null) ? new Coordinates(coordJSON) : null;
		countryCode = json.optString(OWM.COUNTRY);
	}

	/**
	 * Geo Location of a city wich wraps the latitude and longitude values.
	 * 
	 * @author malkomich
	 *
	 */
	public class Coordinates {
		private static final String OWN_LAT = "lat";
		private static final String OWN_LON = "lon";

		private float latitude;
		private float longitude;

		Coordinates(JSONObject json) {
			latitude = new Float(json.optDouble(Coordinates.OWN_LAT));
			longitude = new Float(json.optDouble(Coordinates.OWN_LON));
		}

		/**
		 * Gets the latitude of the geo location
		 * 
		 * @return Latitude
		 */
		public float getLatitude() {
			return latitude;
		}

		/**
		 * Gets the longitude of the geo location
		 * 
		 * @return Longitude
		 */
		public float getLongitude() {
			return longitude;
		}
	}

	/**
	 * Gets the city identifier
	 * 
	 * @return City global ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * Gets the city name
	 * 
	 * @return City name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the code of the country where the city is from
	 * 
	 * @return Code ID for the Country
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * Retrieves a wrapper for the Latitude and Longitude params of the city
	 * 
	 * @return Coordinates POJO
	 */
	public Coordinates getCoordinates() {
		return coordinates;
	}
}