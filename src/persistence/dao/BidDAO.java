/**
 * 
 */
package persistence.dao;

import java.util.List;

import persistence.entities.Bid;

/**
 * @author 14108849
 *
 */
public interface BidDAO extends BaseDAO<Bid>{
	
	public List<Bid> findByLotId(Long lotId) throws DAOException;

}
