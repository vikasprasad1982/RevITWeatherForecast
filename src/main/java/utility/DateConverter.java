/*
 * Util class for converting the epoch format date to human readable format
 */
package utility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {
	
	/**
	 * Util method to convert EPOCH date format to Date string
	 * @param epochDate
	 * @return Date String
	 */
	public static String epochToDate(Integer epochDate) {
		
		Date convertedDate = new Date(Long.valueOf(Integer.toString(epochDate))*1000);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(convertedDate);
		return date;
	}

}
