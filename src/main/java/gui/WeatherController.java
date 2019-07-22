package gui;

import org.springframework.web.bind.annotation.RestController;

import businesslogic.WeatherManager;
import model.Weather;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class WeatherController {

	private static final Logger logger = LoggerFactory.getLogger(WeatherController.class);

	private WeatherManager mng = new WeatherManager();

	@RequestMapping("/weather")
	public ResponseEntity<Weather> getWeather(String cityId, String service) {
		if (!mng.isCityValid(cityId)) {
			logger.warn("getWeather: city id={} is invalid -> return BAD_REQUEST", cityId);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}

		if (!mng.isKeyValid(service)) {
			logger.warn("getWeather: service={} is invalid -> return BAD_REQUEST", service);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}

		Weather weather = mng.getWeather(service, cityId);
		logger.debug("getWeather: return weather={} for service={} and city id={}", weather, service, cityId);
		return new ResponseEntity(weather, HttpStatus.ACCEPTED);

	}

}
