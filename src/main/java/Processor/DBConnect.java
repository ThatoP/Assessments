package Processor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnect 
{
	public static Connection con = null;
	public static Statement stmt = null;
	
	public static void createConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Movies","postgres","Tyler$0ft");
			System.out.println("CONNECTED SUCCESSFULLY TO DATABASE");
		}catch(SQLException e) {
			System.out.println("Could not connect to database:- \n");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Class Not Found:- \n");
			e.printStackTrace();
		}
	}
	
	public static int insertRow(String query) {
		createConnection();
		int rows = 0;
		try {
			stmt = con.createStatement();
			rows = stmt.executeUpdate(query);
			
			if(rows != 0) {
				System.out.println("Insert successful.");
			}
			con.close();
		} catch (SQLException e) {
			System.out.println("Could not edit database:- ");
			e.printStackTrace();
		}
		return rows;
	}
}
