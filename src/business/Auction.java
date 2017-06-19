/**
 * 
 */
package business;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * @author Vinicius
 *
 */
public class Auction {
	
	private Long id;

	private boolean byDemand;
	
	private boolean hiddenBids;
	
	private Timestamp startBidding;
	
	private Timestamp endBidding;
	
	private Participant sellerOrBuyer;
	
	private Lot lot;
	
	private ArrayList<Bid> bids;

	public Auction(boolean byDemand, boolean hiddenBids, Timestamp startBidding, Timestamp endBidding, Participant sellerOrBuyer,
			Lot lot, ArrayList<Bid> bids) {
		super();
		this.byDemand = byDemand;
		this.hiddenBids = hiddenBids;
		this.startBidding = startBidding;
		this.endBidding = endBidding;
		this.sellerOrBuyer = sellerOrBuyer;
		this.lot = lot;
		this.bids = bids;
	}
	
	public Auction(boolean byDemand, boolean hiddenBids, Timestamp startBidding, Timestamp endBidding, Participant sellerOrBuyer,
			Lot lot) {
		super();
		this.byDemand = byDemand;
		this.hiddenBids = hiddenBids;
		this.startBidding = startBidding;
		this.endBidding = endBidding;
		this.sellerOrBuyer = sellerOrBuyer;
		this.lot = lot;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isByDemand() {
		return byDemand;
	}

	public void setByDemand(boolean byDemand) {
		this.byDemand = byDemand;
	}

	public boolean isHiddenBids() {
		return hiddenBids;
	}

	public void setHiddenBids(boolean hiddenBids) {
		this.hiddenBids = hiddenBids;
	}

	public Timestamp getStartBidding() {
		return startBidding;
	}

	public void setStartBidding(Timestamp startBidding) {
		this.startBidding = startBidding;
	}

	public Timestamp getEndBidding() {
		return endBidding;
	}

	public void setEndBidding(Timestamp endBidding) {
		this.endBidding = endBidding;
	}

	public Participant getSellerOrBuyer() {
		return sellerOrBuyer;
	}

	public void setSellerOrBuyer(Participant sellerOrBuyer) {
		this.sellerOrBuyer = sellerOrBuyer;
	}

	public Lot getLot() {
		return lot;
	}

	public void setLot(Lot lot) {
		this.lot = lot;
	}

	public ArrayList<Bid> getBids() {
		return bids;
	}

	public void setBids(ArrayList<Bid> bids) {
		this.bids = bids;
	}
	
}