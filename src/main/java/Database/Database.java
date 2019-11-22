package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import Utils.Props;

public class Database 
{
	private Props props;
	private DataSource datasource;
	private InitialContext initContext;
	private Connection conn = null;
	Statement stmt = null;
	private String sqlOp;
	
	/**
	 * this constructor calls the initializeDatabase method when called
	 * @param props
	 */
	public Database(Props props) {
		this.props = props;
		initializeDatabase();
	}
	
	public Connection getDatabaseConnection() throws Exception{
		return this.conn;
	}
	
	/**
	 * creates connection to the database
	 * uses the JNDI name supplied in the properties file through the Props class
	 * @param no parameters
	 */
	private void initializeDatabase() {
		try {
			props = new Props();
			initContext = new InitialContext();
			datasource = (DataSource) initContext.lookup(props.getUrl());
			conn = datasource.getConnection();
		} catch (NamingException e) {
			System.out.println("DATABASE INITIALIZATION FAILED. \nPOSSIBLE ISSUE: CONFIGURED CONTEXT URL OR DATASOURCE");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DATABASE INITIALIZATION FAILED. \nPOSSIBLE CAUSE: Datasource connection");
			e.printStackTrace();
		}
	}
}
