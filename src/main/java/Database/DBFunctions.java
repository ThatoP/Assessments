package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import Utils.Props;

public class DBFunctions 
{
	private Props props;
	private Database database;
	
	public DBFunctions(Props props) {
		this.props = props;
	}
	
	private void getDBConnection(){
        try {
            this.database = new Database(props);
        } catch (Exception ex) {
            System.out.println("Database connection failed. Review: SQL Connectivity: ");
            ex.printStackTrace();
        }
    }
	
	/**
	 * A method that inserts rows into the database, given the query
	 * @param query
	 * @return nothing
	 */
	public void RunQuery(String query) 
	{ 
        Statement stmt = null; 
        Connection connection = null; 
        	
        try {
        	props = new Props();
        	database = new Database(props);
            connection = database.getDatabaseConnection();
            stmt = connection.createStatement();
            int rows = stmt.executeUpdate(query);
            //System.out.println(rows);
            if(rows>0) {
            	System.out.println("Query executed successfully");
            	}
            connection.close();
            }catch(Exception ex) {
        		System.out.println("Error in Query Generation:-");
        		ex.printStackTrace();
        	}
     }
}
