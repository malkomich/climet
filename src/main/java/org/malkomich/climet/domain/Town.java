package org.malkomich.climet.domain;

import org.json.JSONObject;

public class Town {
	
	private int id;
	private String name;
	private String countryCode;
	private Coordinates coordinates;
	
	public Town(int id, String name, String country, JSONObject coordJSON) {
		this.id = id;
		this.name = name;
		countryCode = country;
		coordinates = (coordJSON != null) ? new Coordinates(coordJSON) : null;
	}

	public Town(JSONObject json) {
		id = json.optInt(OWN.ID);
		name = json.optString(OWN.NAME);
		JSONObject coordJSON = json.optJSONObject(OWN.COORD);
		coordinates = (coordJSON != null) ? new Coordinates(coordJSON) : null;
		countryCode = json.optString(OWN.COUNTRY);
	}

	public class Coordinates {
		private static final String OWN_LAT = "lat";
		private static final String OWN_LON = "lon";

		private float lat;
		private float lon;

		Coordinates(JSONObject json) {
			lat = new Float(json.optDouble(Coordinates.OWN_LAT));
			lon = new Float(json.optDouble(Coordinates.OWN_LON));
		}

		public float getLat() {
			return lat;
		}

		public float getLon() {
			return lon;
		}
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}
}