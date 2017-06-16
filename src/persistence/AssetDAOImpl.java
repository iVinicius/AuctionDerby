/**
 * 
 */
package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import business.Asset;
import business.Category;

/**
 * @author 14108849
 *
 */
public class AssetDAOImpl implements AssetDAO{

	@Override
	public Asset findById(Long id) throws DAOException {
		try {
			if(id == null){
				return null;
			}
			
			Connection con = BaseDAO.getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM ASSET WHERE ID=?");
			stmt.setLong(1, id);
			ResultSet resultado = stmt.executeQuery();
			Asset a = null;
			if (resultado.next()) {
				Long assetId = resultado.getLong("ID");
				String briefD = resultado.getString("BRIEFDESCRIPTION");
				String fullD = resultado.getString("FULLDESCRIPTION");
				Long catId = resultado.getLong("CATEGORY_ID");
				Category cat = Category.getCategory(catId);
				a = new Asset(briefD, fullD, cat);
				a.setId(assetId);
			}
			stmt.close();
			con.close();
			return a;
		} catch (SQLException ex) {
			throw new DAOException("Falha ao buscar participante.", ex);
		}
	}

	@Override
	public Asset createOrUpdate(Asset entity) throws DAOException {
		try {
			Connection con = BaseDAO.getConnection();

			if (entity == null) {
				return null;
			}

			if (entity.getId() != null && this.findById(entity.getId()) != null) {
				// update
				PreparedStatement stmt = con.prepareStatement(
						"UPDATE ASSET " + " SET BRIEFDESCRIPTION = ?, FULLDESCRIPTION = ?, CATEGORY_ID = ?" + " WHERE ID = ?");
				stmt.setString(1, entity.getBriefDescription());
				stmt.setString(2, entity.getFullDescription());
				stmt.setLong(3, entity.getCategory().getId());
				stmt.setLong(4, entity.getId());
				stmt.executeUpdate();
				stmt.close();
				con.close();
				return entity;
			} else {		
				// create 
				PreparedStatement stmt = con
						.prepareStatement("values (NEXT VALUE FOR asset_seq)");
				
				Long nextValue = 0L;
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					nextValue = rs.getLong("1");
				}			
				
				stmt.close();
				stmt = con
						.prepareStatement("INSERT INTO ASSET (ID, BRIEFDESCRIPTION, FULLDESCRIPTION, CATEGORY_ID) VALUES (?,?,?,?)");
				stmt.setLong(1, nextValue);
				stmt.setString(2, entity.getBriefDescription());
				stmt.setString(3, entity.getFullDescription());
				stmt.setLong(4, entity.getCategory().getId());
				stmt.executeUpdate();
				stmt.close();
				con.close();
				entity.setId(nextValue);
				return entity;
			}
		} catch (SQLException ex) {
			throw new DAOException("Falha ao criar ou atualizar participante.", ex);
		}
	}

	@Override
	public Asset delete(Asset entity) throws DAOException {
		try {
			if(entity == null){
				return null;
			}
			
			Connection con = BaseDAO.getConnection();
			PreparedStatement stmt = con.prepareStatement("DELETE FROM ASSET WHERE ID=?");
			stmt.setLong(1, entity.getId());
			stmt.executeUpdate();
			stmt.close();
			con.close();
			return entity;
		} catch (SQLException ex) {
			throw new DAOException("Falha ao deletar participante.", ex);
		}
	}

}
