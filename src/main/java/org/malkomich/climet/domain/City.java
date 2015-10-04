package org.malkomich.climet.domain;

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
		id = json.optInt(OWN.ID);
		name = json.optString(OWN.NAME);
		JSONObject coordJSON = json.optJSONObject(OWN.COORD);
		coordinates = (coordJSON != null) ? new Coordinates(coordJSON) : null;
		countryCode = json.optString(OWN.COUNTRY);
	}

	/**
	 * Geo Location of a city
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
		 * @return
		 */
		public float getLatitude() {
			return latitude;
		}

		/**
		 * Gets the longitude of the geo location
		 * 
		 * @return
		 */
		public float getLongitude() {
			return longitude;
		}
	}

	/**
	 * Gets the city identifier
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * Gets the city name
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the code of the country where the city is from
	 * 
	 * @return
	 */
	public String getCountryCode() {
		return countryCode;
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}
}