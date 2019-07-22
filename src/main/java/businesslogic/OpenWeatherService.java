package businesslogic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.Weather;
import model.openweather.OpenWeather;

public class OpenWeatherService implements IWeatherService {

	private static final Logger logger = LoggerFactory.getLogger(OpenWeatherService.class);

	private static final String API_URL = "https://community-open-weather-map.p.rapidapi.com/weather?q=";

	private static final String HOST = "community-open-weather-map.p.rapidapi.com";
	private static final String KEY = "d826ab6949msh2fbd33d2d00a4b1p1c90a2jsn08a37110a2be";

	private ObjectMapper mapper = new ObjectMapper();

	@Override
	public Weather getWeatherInfo(String city) {
		String url = API_URL + city;

		try {

			URL obj = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

			connection.setRequestMethod("GET");
			connection.setRequestProperty("X-RapidAPI-Host", HOST);
			connection.setRequestProperty("X-RapidAPI-Key", KEY);

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			String weatherStr = response.toString();

			OpenWeather weather = mapper.readValue(weatherStr, OpenWeather.class);

			return Factory.getWeather(weather);
		} catch (Exception e) {
			logger.warn("sendGet: can't get weather for city={}, error: ", city, e);
			return null;
		}
	}

}
