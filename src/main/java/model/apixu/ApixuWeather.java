package model.apixu;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApixuWeather {

	private CurrentInfo current;

	public CurrentInfo getCurrent() {
		return current;
	}

	public void setCurrent(CurrentInfo current) {
		this.current = current;
	}

}
