/**
 * 
 */
package persistence.dao;

import java.util.List;

import persistence.entities.Auction;

/**
 * @author Vinicius
 *
 */
public interface AuctionDAO extends BaseDAO<Auction>{

	public List<Auction> getAll() throws DAOException;
}
