package businesslogic;

import model.Weather;

public interface IWeatherService {

	public Weather getWeatherInfo(String city);

}
