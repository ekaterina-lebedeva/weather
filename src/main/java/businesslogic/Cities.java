package businesslogic;

import java.util.HashMap;
import java.util.Map;

public class Cities {
	private static final String LONDON = "London";
	private static final String MOSCOW = "Moscow";
	private static final String PARIS = "Paris";

	private static Map<String, String> cities = new HashMap<String, String>() {
		{
			put("lon", LONDON);
			put("mos", MOSCOW);
			put("par", PARIS);
		}
	};

	public static Map<String, String> getCities() {
		return cities;
	}

}
