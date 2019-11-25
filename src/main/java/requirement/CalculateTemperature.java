/*
 * This class maps to the requirement of finding out those days in Sydney city in next 5 days when the temperature is above 20 degrees
 */
package requirement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.Forecast;
import model.ForecastResponse;
import utility.DateConverter;

public class CalculateTemperature {

	/**
	 * Util method to iterate the Forecast response and fetch the data on the basis of requirement. 
	 * Temperature field in Forecast provides the maximum temp information and is used to implement this scenario
	 * @param forecastResponse
	 * @return Set<String> (a dataset containing the dates in String when the weather is sunny)
	 */
	public Set<String> findtempforDays(ForecastResponse forecastResponse) {

		List<Forecast> forecastlist = forecastResponse.getList();
		Set<String> set = new HashSet<String>();

		// Filter out the objects when the temperature is below 20 degrees
		for (int i = 0; i < forecastlist.size(); i++) {
			if (forecastlist.get(i).getMain().getTemp_max() != null && 
					forecastlist.get(i).getMain().getTemp_max() >= 20) {
				set.add(DateConverter.epochToDate(forecastlist.get(i).getDt()));
			}
		}
		if(set.size() == 0) {
			System.out.println("***No days have temperature above 20 degrees!!****");
		}
		return set;
	}
}
