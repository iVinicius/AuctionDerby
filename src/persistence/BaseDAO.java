/**
 * 
 */
package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Vinicius
 * @param <E>
 *
 */
public interface BaseDAO<E>{
	
	public E findById(Long id) throws DAOException;
	
	public E createOrUpdate(E entity) throws DAOException;
	
	public E delete(E entity) throws DAOException;
	
	public static Connection getConnection() throws SQLException {
        //derbyDB sera o nome do diretorio criado localmente
        return DriverManager.getConnection("jdbc:derby:derbyDB");
    }
	
	public static void isRunning() throws DAOException{
		try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
       } catch (ClassNotFoundException ex) {
           throw new DAOException("JdbcOdbDriver not found!!");
       }
	}
	
	public static void createDerbyDB() throws DAOException{
		try {
            Connection con = DriverManager.getConnection("jdbc:derby:derbyDB;create=true");
            con.close();
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage());
        }
		System.out.println("DerbyDB created successfuly");
	}
}