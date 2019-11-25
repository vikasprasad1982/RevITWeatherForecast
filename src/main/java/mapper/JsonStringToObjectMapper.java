package mapper;

/*
 * This is a mapper class which maps the returned JSONString response to the respective POJO
 * Only those properties are mapped which are required as per the requirement, the POJO's will need extension as per the growing functionality
 */
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.ForecastResponse;

public class JsonStringToObjectMapper {
	
	/**
	 * This method will map the JSONString response to the respective POJO using the Jackson API. Selective fields from the JSONString response are mapped to the POJO's, others are ignored.
	 * @param jsonStringResponse
	 * @return ForecastResponse (populated parent POJO)
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static ForecastResponse getForcast(String jsonStringResponse) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper  = new ObjectMapper();
		ForecastResponse mappedReponseObject = objectMapper.readValue(jsonStringResponse, ForecastResponse.class);
		return mappedReponseObject;
	}

}
