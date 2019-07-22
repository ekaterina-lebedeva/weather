const CITY_KEY = "city";
const SERVICE_KEY = "service";

const citySelect = document.querySelector(".city");
const serviceSelect = document.querySelector(".service");
const showButton = document.querySelector(".show");
const loadingEl = document.querySelector(".loading");
const temperatureEl = document.querySelector(".temperature");

let city = (citySelect.value =
  window.localStorage.getItem(CITY_KEY) || citySelect.value);
let service = (serviceSelect.value =
  window.localStorage.getItem(SERVICE_KEY) || serviceSelect.value);

citySelect.addEventListener("change", function(event) {
  city = event.target.value;
  window.localStorage.setItem(CITY_KEY, city);
});

serviceSelect.addEventListener("change", function(event) {
  service = event.target.value;
  window.localStorage.setItem(SERVICE_KEY, service);
});

showButton.addEventListener("click", function() {
  loadingEl.classList.remove("hidden");
  temperatureEl.innerHTML = "";

  getTemperature({ city, service })
    .then(function(response) {
      loadingEl.classList.add("hidden");

      console.log(response.status, response.data);
      temperatureEl.innerHTML = formatTemperature(response.data);
    })
    .catch(function(error) {
      temperatureEl.innerHTML = 'Error';
      console.log(error);
    });
});

function formatTemperature({temperature, humidity, pressure}) {
  return `Temperature: ${temperature}°C <br />
  Humidity: ${humidity}%<br />
  Pressure: ${pressure} hPa`;
}

function getTemperature({ city, service }) {
	  return axios.get("/weather", {
	    params: {
	    	cityId: city,
	    	service
	    }
	  });
}
