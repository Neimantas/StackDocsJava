package Services.Impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Services.IDataBase;

public class DataBaseImpl implements IDataBase {

	private Connection conn = null;
	
	public Connection connect() {
		
		
        try {
            // db parameters
            String url = "jdbc:sqlite:src/main/resources/stack.db";
            
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            
            System.out.println("Connection to SQLite has been established.");
            return conn;
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
		
        return null;
	}

	public void close() {
		
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
			
		
	}

}
