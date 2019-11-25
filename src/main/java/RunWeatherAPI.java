
/*
 * Main class for running the project. The class needs to be run as a Java application and will require compilation using Java 1.8
 * 
 */
import java.io.IOException;
import java.util.Set;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import client.APIClient;
import mapper.JsonStringToObjectMapper;
import model.ForecastResponse;
import requirement.CalculateSunnyDays;
import requirement.CalculateTemperature;

public class RunWeatherAPI {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {

		RunWeatherAPI runnerClass = new RunWeatherAPI();

		// Initialize the client and parse the response
		String jsonResponse = runnerClass.initializeClient();

		// Initialize the mapper to map the JSONString to respective POJO
		ForecastResponse forecastResponse = runnerClass.initializeMapper(jsonResponse);

		// Calculate the sunnyDays from the API response
		runnerClass.calculateSunnyDays(forecastResponse);

		// Calculate the temperature from the API response
		runnerClass.calculateTemperature(forecastResponse);

	}

	/**
	 * Initialize the client to connect to the API and receive response
	 * @return JSONString (String format)
	 */
	public String initializeClient() {
		APIClient apiClient = new APIClient();
		return apiClient.initConnection();
	}

	/**
	 * Initialize mapper to map JSONString to POJO
	 * @param jsonResponse
	 * @return mapped ForecastResponse (POJO)
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public ForecastResponse initializeMapper(String jsonResponse)
			throws JsonParseException, JsonMappingException, IOException {
		JsonStringToObjectMapper mapper = new JsonStringToObjectMapper();
		return mapper.getForcast(jsonResponse);
	}

	/**
	 * calculate the temperature
	 * @param forecastResponse
	 */
	public void calculateTemperature(ForecastResponse forecastResponse) {
		CalculateTemperature calcTemp = new CalculateTemperature();
		Set<String> daysTempMoreThanTwenty = calcTemp.findtempforDays(forecastResponse);
		System.out.println("Days when the temperature is above 20 degrees " + daysTempMoreThanTwenty.toString());
	}

	/**
	 * Calculate the sunny dates
	 * @param forecastResponse
	 */
	public void calculateSunnyDays(ForecastResponse forecastResponse) {
		CalculateSunnyDays calcSunnyDays = new CalculateSunnyDays();
		Set<String> sunnyDays = calcSunnyDays.findSunnyDays(forecastResponse);
		System.out.println("Days when the weather is sunny " + sunnyDays.toString());
	}

}
