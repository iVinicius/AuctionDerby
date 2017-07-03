/**
 * 
 */
package business;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import business.utils.StringUtils;
import business.utils.TimestampConverter;
import persistence.entities.Auction;
import persistence.entities.Bid;

/**
 * @author Vinicius
 *
 */
public class Facade {
	
    private static Facade ref;
    
    ParticipantBO participantBO = new ParticipantBO();
    
    AuctionBO auctionBO = new AuctionBO();
    
    BidBO bidBO = new BidBO();
    
    public static Facade getInstance() {
        if (ref == null)
            ref = new Facade();
        return ref;
    }
	

	public Long userLogin(String id) throws Exception{
		return participantBO.login(Long.parseLong(id));
	}
	
	public Long userRegister(String name, String cpf, String email) throws Exception{	
		return participantBO.createParticipant(name, cpf, email);
	}
	
	public List<String> getAuctionList() throws Exception{
		try{
			List<Auction> aucList = auctionBO.getAuctionList();
			List<String> strList = new ArrayList<>();
			
			for(Auction a : aucList){
				strList.add(a.getId().toString());
			}
			return strList;
		}catch(Exception e){
			//TODO:
			throw e;
		}
	}
	
	public Auction createAuction(String byDemand, String hiddenBids, String startBidding, String endBidding,
			String sellerOrBuyerId, String lotValue) throws Exception{
		try{
			boolean boolByDemand = byDemand.equalsIgnoreCase("Y");
			boolean boolHiddenBids = hiddenBids.equalsIgnoreCase("Y");
			Timestamp startDate = TimestampConverter.getInstance().convertFromString(startBidding);
			Timestamp endDate = TimestampConverter.getInstance().convertFromString(endBidding);
			Long sellerOrBuyerIdLong = Long.parseLong(sellerOrBuyerId);
			Long lotValueLong = Long.parseLong(lotValue);
			
			Auction newA = auctionBO.createAuction(boolByDemand, boolHiddenBids, startDate, endDate, sellerOrBuyerIdLong, lotValueLong);
			
			return newA;
		}catch(NumberFormatException nE){
			throw new BusinessException("Ocorreu um erro ao utilizar os valores fornecidos, por favor tente novamente.");
		}catch(Exception e){
			throw e;
		}
	}
	
	public List<String> getBidListByAuctionId(String auctionId) throws Exception{
		try{
			List<String> bidListStr = new ArrayList<>();
			
			if(auctionId == null || StringUtils.getInstance().isEmpty(auctionId)){
				return bidListStr;
			}
			
			Long auctionIdLong = Long.parseLong(auctionId);				
			
			List<Bid> bidList = bidBO.getBidsByAuction(auctionIdLong);
			
			for(Bid b : bidList){
				bidListStr.add(b.getId().toString());
			}
			
			return bidListStr;
		}catch(Exception e){
			//TODO:
			throw e;
		}
	}
	
	public Auction getAuctionById(String auctionId){
		try{
			return auctionBO.findAuctionById(Long.parseLong(auctionId));
		}catch(Exception e){
			// do nothing
			return null;
		}
	}
}