/**
 * 
 */
package business.utils;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author Vinicius
 *
 */
public class TimestampConverter {

	private static TimestampConverter ref;
	
	public static TimestampConverter getInstance(){
		if(ref == null){
			ref = new TimestampConverter();
		}
		return ref;
	}
	
	//expected format dd/MM/yy:HH:mm
	public Timestamp convertFromString(String stringTimestamp) throws Exception{
		String[] splitDate = stringTimestamp.split("/");
		String[] splitTime = stringTimestamp.split(":");
		int day = Integer.parseInt(splitDate[0]);
		int month = Integer.parseInt(splitDate[1]) - 1;
		//splitDate[2].substring(0, 3);
		int year = Integer.parseInt(splitDate[2].substring(0, 4));
		year -= 1900;
		
		int hour = Integer.parseInt(splitTime[1]);
		int min = Integer.parseInt(splitTime[2]);
		
		Date date = new Date(year, month , day, hour, min);
		
		Timestamp stmp = new Timestamp(date.getTime());
		
		return stmp;
	}
	
	//expected format dd/MM/yy:HH:mm
	public String convertFromTimestamp(Timestamp timestamp){
		Date dateAux = new Date(timestamp.getTime());
		
		String parsed = "";
		parsed += dateAux.getDate() + "/" + (dateAux.getMonth()+1) + "/" + (dateAux.getYear()+1900);
		parsed += ":" + dateAux.getHours() + ":" + dateAux.getMinutes();
		
		return parsed;
	}
}