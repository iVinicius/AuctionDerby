/**
 * 
 */
package tests;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import persistence.dao.AssetDAO;
import persistence.dao.DAOException;
import persistence.dao.LotDAO;
import persistence.dao.impl.AssetDAOImpl;
import persistence.dao.impl.LotDAOImpl;
import persistence.entities.Asset;
import persistence.entities.Category;
import persistence.entities.Lot;

/**
 * @author Vinicius
 *
 */
public class LotDAOTest extends BaseDAOTest{
	
	private LotDAO lotDAO = new LotDAOImpl();
	
	private AssetDAO assetDAO = new AssetDAOImpl();
	
	private Lot entity;
	
	public void generateObject(){
		entity = new Lot(1000L, false);
		ArrayList<Asset> assets = new ArrayList<>();
		int i = 0;
		while(i < 2){
			assets.add(new Asset("Oculos " + i, "Oculos Rayban", Category.INFORMATICA));
			i++;
		}
		entity.setAssets(assets);
	}
	
	@Test
	public void testCRUD() throws DAOException{
		generateObject();
		testCreate();
		testFind();
		testUpdate();
		testDelete();
	}

	public void testCreate() throws DAOException{
		entity = lotDAO.createOrUpdate(entity);
		Assert.assertNotNull(entity);
		Assert.assertNotNull(entity.getId());
		for(Asset asset : entity.getAssets()){
			Assert.assertNotNull(asset.getId());
		}
	}
	
	public void testFind() throws DAOException{
		entity = lotDAO.findById(entity.getId());
		Assert.assertNotNull(entity);
		Assert.assertNotNull(entity.getId());
	}

	public void testUpdate() throws DAOException{
		entity.setValue(1500L);
		entity = lotDAO.createOrUpdate(entity);
		Assert.assertNotNull(entity);
		Assert.assertNotNull(entity.getId());
		Assert.assertEquals(entity.getValue().longValue(), 1500L);
	}
	
	public void testDelete() throws DAOException{
		lotDAO.delete(entity);
		for(Asset asset : entity.getAssets()){
			Assert.assertNull(assetDAO.findById(asset.getId()));
		}
		entity = lotDAO.findById(entity.getId());
		Assert.assertNull(entity);
	}
}
