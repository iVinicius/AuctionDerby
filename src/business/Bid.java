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

	public Bid(Participant bidder, Long value, Lot lot, Date bidTime) {
		super();
		this.bidder = bidder;
		this.value = value;
		this.lot = lot;
		this.bidTime = bidTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}