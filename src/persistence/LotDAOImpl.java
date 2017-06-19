/**
 * 
 */
package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import business.Asset;
import business.Lot;

/**
 * @author 14108849
 *
 */
public class LotDAOImpl implements LotDAO{
	
	private AssetDAO assetDAO = new AssetDAOImpl();

	@Override
	public Lot findById(Long id) throws DAOException {
		try {
			if(id == null){
				return null;
			}
			
			Connection con = BaseDAO.getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM LOT WHERE ID=?");
			stmt.setLong(1, id);
			ResultSet resultado = stmt.executeQuery();
			Lot l = null;
			if (resultado.next()) {
				Long lotId = resultado.getLong("ID");
				Long value = resultado.getLong("VALUE");
				String isValueMax = resultado.getString("ISVALUEMAX");
				l = new Lot(value, isValueMax.equalsIgnoreCase("Y") ? true : false);
				l.setId(lotId);			
			}
			stmt.close();
			
			stmt = con.prepareStatement("SELECT * FROM LOT_ASSET WHERE LOT_ID=?");
			stmt.setLong(1, id);
			resultado = stmt.executeQuery();
			ArrayList<Asset> assets = new ArrayList<>();
			while (resultado.next()) {
				Long assetId = resultado.getLong("ASSET_ID");
				Asset asset = assetDAO.findById(assetId);				
				assets.add(asset);
			}		
			if(l != null){
				l.setAssets(assets);
			}			
			
			stmt.close();
			con.close();
			return l;
		} catch (SQLException ex) {
			throw new DAOException("Falha ao buscar lote.", ex);
		}
	}

	@Override
	public Lot createOrUpdate(Lot entity) throws DAOException {
		try {
			Connection con = BaseDAO.getConnection();

			if (entity == null) {
				return null;
			}

			if (entity.getId() != null && this.findById(entity.getId()) != null) {
				// update
				PreparedStatement stmt = con.prepareStatement(
						"UPDATE LOT " + " SET VALUE = ?, ISVALUEMAX = ?" + " WHERE ID = ?");
				stmt.setLong(1, entity.getValue());
				stmt.setString(2, entity.isValueMax() ? "Y" : "N");
				stmt.setLong(3, entity.getId());
				
				stmt.executeUpdate();
				stmt.close();
				
				for(Asset asset : entity.getAssets()){
					assetDAO.createOrUpdate(asset);
				}
				
				con.close();
				return entity;
			} else {		
				// create 
				PreparedStatement stmt = con
						.prepareStatement("values (NEXT VALUE FOR lot_seq)");
				
				Long nextValue = 0L;
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					nextValue = rs.getLong("1");
				}			
				
				stmt.close();
				stmt = con
						.prepareStatement("INSERT INTO LOT (ID, VALUE, ISVALUEMAX) VALUES (?,?,?)");
				stmt.setLong(1, nextValue);
				stmt.setLong(2, entity.getValue());
				stmt.setString(3, entity.isValueMax() ? "Y" : "N");

				stmt.executeUpdate();
				entity.setId(nextValue);
				stmt.close();
				
				for(Asset asset : entity.getAssets()){
					asset = assetDAO.createOrUpdate(asset);
					
					stmt = con
							.prepareStatement("INSERT INTO LOT_ASSET (LOT_ID, ASSET_ID) VALUES (?,?)");
					stmt.setLong(1, entity.getId());
					stmt.setLong(2, asset.getId());
					stmt.executeUpdate();
					stmt.close();
				}						
				
				con.close();
				return entity;
			}
		} catch (SQLException ex) {
			throw new DAOException("Falha ao criar ou atualizar lote.", ex);
		}
	}

	@Override
	public Lot delete(Lot entity) throws DAOException {
		try {
			if(entity == null){
				return null;
			}
			
			Connection con = BaseDAO.getConnection();
			PreparedStatement stmt = con.
					prepareStatement("DELETE FROM LOT WHERE ID=?");
			stmt.setLong(1, entity.getId());
			stmt.executeUpdate();
			stmt.close();
			
			for(Asset asset : entity.getAssets()){
				assetDAO.delete(asset);						
			}
			
			stmt = con.
					prepareStatement("DELETE FROM LOT_ASSET WHERE LOT_ID=?");
			stmt.setLong(1, entity.getId());
			stmt.executeUpdate();
			stmt.close();
			
			con.close();
			return entity;
		} catch (SQLException ex) {
			throw new DAOException("Falha ao deletar lote.", ex);
		}
	}

}