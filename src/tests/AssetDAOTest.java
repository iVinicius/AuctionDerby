/**
 * 
 */
package tests;

import org.junit.Assert;
import org.junit.Test;

import business.Asset;
import business.Category;
import persistence.AssetDAO;
import persistence.AssetDAOImpl;
import persistence.DAOException;

/**
 * @author Vinicius
 *
 */
public class AssetDAOTest extends BaseDAOTest{

	private AssetDAO assetDAO = new AssetDAOImpl();
	
	private Asset entity;
	
	public void generateObject(){
		entity = new Asset("Oculos", "Oculos Rayban", Category.INFORMATICA);
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
		entity = assetDAO.createOrUpdate(entity);
		Assert.assertNotNull(entity);
		Assert.assertNotNull(entity.getId());
	}
	
	public void testFind() throws DAOException{
		entity = assetDAO.findById(entity.getId());
		Assert.assertNotNull(entity);
		Assert.assertNotNull(entity.getId());
	}

	public void testUpdate() throws DAOException{
		entity.setBriefDescription("TesteAlterado");
		entity = assetDAO.createOrUpdate(entity);
		Assert.assertNotNull(entity);
		Assert.assertNotNull(entity.getId());
		Assert.assertEquals(entity.getBriefDescription(), "TesteAlterado");
	}
	
	public void testDelete() throws DAOException{
		assetDAO.delete(entity);
		entity = assetDAO.findById(entity.getId());
		Assert.assertNull(entity);
	}
}
