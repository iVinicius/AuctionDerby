/**
 * 
 */
package business;

import java.sql.Date;
import java.util.ArrayList;

/**
 * @author Vinicius
 *
 */
public class Auction {
	
	private Long id;

	private boolean byDemand;
	
	private boolean hiddenBids;
	
	private Date startBidding;
	
	private Date endBidding;
	
	private Participant sellerOrBuyer;
	
	private Lot lot;
	
	private ArrayList<Bid> bids;
}