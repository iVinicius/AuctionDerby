/**
 * 
 */
package tests;

import java.sql.Timestamp;
import java.util.Calendar;

import org.junit.Test;

import business.utils.TimestampConverter;

/**
 * @author Vinicius
 *
 */
public class TimestampConverterTest {

	@Test
	public void testConvertionFromString() throws Exception{
		String dateConvert = "22/6/2017:13:01";
		TimestampConverter.getInstance().convertFromString(dateConvert);
	}
	
	@Test
	public void testConvertionFromTimestamp() throws Exception{
		Timestamp stamp = new Timestamp(Calendar.getInstance().getTime().getTime());
		TimestampConverter.getInstance().convertFromTimestamp(stamp);
	}
}
