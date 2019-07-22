package businesslogic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.Weather;
import model.apixu.ApixuWeather;

public class ApixuWeatherService implements IWeatherService {

	private static final Logger logger = LoggerFactory.getLogger(ApixuWeatherService.class);

	private static final String KEY = "19590ac2dd2a4610b06103101191907";

	private static final String API_URL = "http://api.apixu.com/v1/current.json?key=" + KEY + "&q=";

	private ObjectMapper mapper = new ObjectMapper();

	@Override
	public Weather getWeatherInfo(String city) {
		String url = API_URL + city;

		try {

			URL obj = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

			connection.setRequestMethod("GET");

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			String weatherStr = response.toString();

			ApixuWeather weather = mapper.readValue(weatherStr, ApixuWeather.class);

			return Factory.getWeather(weather);
		} catch (Exception e) {
			logger.warn("sendGet: can't get weather for city={}, error: ", city, e);
			return null;
		}
	}
}
