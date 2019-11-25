/*
 * This class acts as the Client which connects to the API.
 * It captures the HTTPResponse and parses it to a String.
 */

package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import utility.LoadProperties;

@SuppressWarnings("deprecation")
public class APIClient {

	private HttpClient httpClient;

	public APIClient() {
		this.httpClient = new DefaultHttpClient();
	}

	/**
	 * Helper method to create HTTPGet request
	 * @return HttpGet
	 */
	private HttpGet createHttpRequest() {
		LoadProperties propertiesLoad = new LoadProperties();
		HttpGet httpGetRequest = new HttpGet((propertiesLoad.getPropertyFileValues().getProperty("URL")) + "?id="
				+ (propertiesLoad.getPropertyFileValues().getProperty("city_id")) + "&units="
				+ (propertiesLoad.getPropertyFileValues().getProperty("unit")) + "&appid="
				+ (propertiesLoad.getPropertyFileValues().getProperty("app_id")));

		return httpGetRequest;
	}
	
	/**
	 * This method initialize the connection to the API and check for HTTPResponse
	 * status.
	 * @return HTTPResponse content in String
	 */
	public String initConnection() {

		String response = null;

		try {
			HttpResponse httpGetResponse = httpClient.execute(createHttpRequest());
			checkResponseStatus(httpGetResponse.getStatusLine());
			response = parseHTTPResponse(httpGetResponse);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return response;

	}

	/**
	 * Helper method to check status of the HTTPResponse and throw exception if the
	 * result is not as expected
	 * @throws IOException
	 */
	public void checkResponseStatus(StatusLine status) throws IOException {

		if (status == null) {
			throw new IOException(String.format("******** Unable to get the response from the server ********"));
		}

		int statusCode = status.getStatusCode();

		if (statusCode < 200 || statusCode >= 300) {
			throw new IOException(
					String.format("******** Error response from server with status code %d ********", statusCode));
		}

	}

	/**
	 * This method parses the HTTPResponse using the java.io library.
	 * @param httpResponse
	 * @return Content returned in HTTPResponse in String format
	 * @throws IOException
	 */
	@SuppressWarnings("null")
	public String parseHTTPResponse(HttpResponse httpResponse) throws IOException {

		String responseBody = null;
		HttpEntity entity = httpResponse.getEntity();
		InputStream inputStream = null;
		try {
			inputStream = entity.getContent();

			Reader reader = new InputStreamReader(inputStream);
			int sizeOfContent = (int) entity.getContentLength();
			if (sizeOfContent < 0) {
				sizeOfContent = 8 * 1024;
			}
			StringWriter stringWriter = new StringWriter(sizeOfContent);

			char[] buffer = new char[8 * 1024];
			int n = 0;
			while ((n = reader.read(buffer)) != -1) {
				stringWriter.write(buffer, 0, n);
			}

			responseBody = stringWriter.toString();
			inputStream.close();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (RuntimeException rt) {

			rt.printStackTrace();
		} finally {
			if (inputStream == null) {
				inputStream.close();
			}
		}
//		System.out.println(responseBody);
		return responseBody;

	}
}
