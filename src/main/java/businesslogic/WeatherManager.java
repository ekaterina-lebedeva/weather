package businesslogic;

import java.util.HashMap;
import java.util.Map;

import model.Weather;

public class WeatherManager {

	private Map<String, IWeatherService> map = new HashMap<String, IWeatherService>() {
		{
			put(OpenWeatherService.class.getSimpleName(), new OpenWeatherService());
			put(ApixuWeatherService.class.getSimpleName(), new ApixuWeatherService());
		}
	};

	public Weather getWeather(String key, String cityId) {
		IWeatherService getter = map.get(key);
		return getter.getWeatherInfo(Cities.getCities().get(cityId));
	}

	public boolean isCityValid(String cityId) {
		return Cities.getCities().get(cityId) != null;
	}

	public boolean isKeyValid(String key) {
		return map.get(key) != null;
	}
}
