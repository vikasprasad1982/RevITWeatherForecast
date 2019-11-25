/*
 * This class maps to the requirement of finding out the 'Sunny' days in Sydney city in next 5 days
 */

package requirement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.Forecast;
import model.ForecastResponse;
import utility.DateConverter;

public class CalculateSunnyDays {
	
	/**
	 * Util method to iterate the Forecast response and fetch the data on the basis of requirement. 
	 * 'Clear' weather is considered as a Sunny day.
	 * @param forecastResponse
	 * @return Set<String> (a dataset containing the dates in String when the weather is sunny)
	 */
	public static Set<String> findSunnyDays(ForecastResponse forecastResponse) {
		
		List<Forecast> forecastlist = forecastResponse.getList();
		Set<String> set = new HashSet<String>();

		// Filter out the objects when the weather is Sunny
		for (int i = 0; i < forecastlist.size(); i++) {
			if (forecastlist.get(i).getWeather().get(0).getMain() != null && 
					forecastlist.get(i).getWeather().get(0).getMain().equalsIgnoreCase("Clear")) {
				
				set.add(DateConverter.epochToDate(forecastlist.get(i).getDt()));
			}
		}
		
		if(set.size() == 0) {
			System.out.println("***No days are sunny!!****");
		}
		return set;

	}

}
