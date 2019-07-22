package model;

public class Weather {

	private double temperature;
	private double humidity;
	private double pressure;

	public Weather() {
	}

	public Weather(double temperature, double humidity, double pressure) {
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
	}

	public double getTemperature() {
		return this.temperature;
	}

	public double getHumidity() {
		return this.humidity;
	}

	public double getPressure() {
		return this.pressure;
	}

	@Override
	public String toString() {
		return "Weather [temperature=" + temperature + ", humidity=" + humidity + ", pressure=" + pressure + "]";
	}

}
