package climet;

import org.json.JSONArray;
import org.json.JSONObject;

import climet.domain.CurrentWeatherData;

public class CurrentWeatherClient extends AbstractRequester{

    private final String baseUrl = "http://api.openweathermap.org/data/2.5/weather";

    private CurrentWeatherData weatherData;

    public CurrentWeatherClient(String town, String apiKey) {
        super(town, apiKey);
    }

    public CurrentWeatherClient(float lat, float lon, String apiKey) {
        super(lat, lon, apiKey);
    }

    /* (non-Javadoc)
     * @see org.malkomich.climet.AbstractRequester#init(org.json.JSONObject)
     */
    protected void init(JSONObject json) {
        weatherData = new CurrentWeatherData(json);
    }

    /* (non-Javadoc)
     * @see org.malkomich.climet.AbstractRequester#apiSuccess()
     */
    @Override
    public boolean apiSuccess() {
        if (weatherData != null)
            return true;
        return false;
    }

    /* (non-Javadoc)
     * @see org.malkomich.climet.AbstractRequester#getBaseURL()
     */
    @Override
    protected String getBaseURL() {
        return baseUrl;
    }

    public CurrentWeatherData getWeather() {
        return weatherData;
    }

    /**
     * Retrieves a stack trace with the types of all the items of a JSON Object
     *
     * @param json
     */
    @SuppressWarnings({ "unused", "rawtypes" })
    private void testJSON(JSONObject json) {
        java.util.Iterator it = json.keys();
        while (it.hasNext()) {
            String key = (String) it.next();
            Class c = json.get(key).getClass();
            if (c.equals(JSONObject.class)) {
                System.out.println("\nJSON Object " + key);
                JSONObject json2 = json.getJSONObject(key);
                java.util.Iterator it2 = json2.keys();
                while (it2.hasNext()) {
                    String key2 = (String) it2.next();
                    String name = json2.get(key2).getClass().getName();
                    System.out.println(key2 + ": " + name);
                }
            } else if (c.equals(JSONArray.class)) {
                System.out.println("\nJSON Array " + key);
                JSONArray array = json.getJSONArray(key);
                for (int i = 0; i < array.length(); i++) {
                    System.out.println("JSON Object " + key);
                    JSONObject json2 = (JSONObject) array.get(i);
                    java.util.Iterator it2 = json2.keys();
                    while (it2.hasNext()) {
                        String key2 = (String) it2.next();
                        String name = json2.get(key2).getClass().getName();
                        System.out.println(key2 + ": " + name);
                    }
                }
            } else {
                String name = c.getName();
                System.out.println(key + ": " + name);
            }
        }
    }

}
