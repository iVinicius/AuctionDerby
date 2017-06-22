/**
 * 
 */
package tests;

import org.junit.Test;

import business.utils.TimestampConverter;

/**
 * @author Vinicius
 *
 */
public class TimestampConverterTest {

	@Test
	public void testConvertion() throws Exception{
		String dateConvert = "22/6/2017:13:01";
		TimestampConverter.getInstance().convertFromString(dateConvert);
	}
}
