/**
 * 
 */
package business;

import java.sql.Date;

/**
 * @author Vinicius
 *
 */
public class Bid {
	
	private Long id;

	private Participant bidder;
	
	private Long value;
	
	private Lot lot;
	
	private Date bidTime;
}