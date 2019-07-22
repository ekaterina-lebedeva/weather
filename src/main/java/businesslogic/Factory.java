package businesslogic;

import org.apache.commons.math3.util.Precision;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Weather;
import model.apixu.ApixuWeather;
import model.apixu.CurrentInfo;
import model.openweather.Main;
import model.openweather.OpenWeather;

public class Factory {

	private static final Logger logger = LoggerFactory.getLogger(Factory.class);

	public static Weather getWeather(OpenWeather weather) {
		if (weather == null) {
			logger.warn("getWeather: openWeather is null -> return empty");
			return new Weather();
		}
		Main main = weather.getMain();
		return new Weather(Precision.round(kelvinToCelsius(main.getTemp()), 2), Precision.round(main.getHumidity(), 2),
				Precision.round(main.getPressure(), 2));
	}

	public static Weather getWeather(ApixuWeather weather) {
		if (weather == null) {
			logger.warn("getWeather: apixuWeather is null -> return empty");
			return new Weather();
		}
		CurrentInfo current = weather.getCurrent();

		return new Weather(Precision.round(current.getTemperature(), 2), Precision.round(current.getHumidity(), 2),
				Precision.round(current.getPressure(), 2));
	}

	private static double kelvinToCelsius(double kelvin) {
		return kelvin - 273.15;
	}

}
