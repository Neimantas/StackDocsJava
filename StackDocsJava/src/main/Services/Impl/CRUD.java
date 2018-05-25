package Services.Impl;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Models.DAL.LanguageTagDAL;
import Services.ICrud;
import Services.IDataBase;


public class CRUD implements ICrud {
	private Statement statements;
	private ResultSet readResultSet;
	private Connection conn;
	
	public CRUD() {
		
		 IDataBase db = new DataBaseImpl();
		 conn = db.connect();
		 try {
			statements = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void createDB(){
		
	}	
	
	//CRUD
	//READ
	
	public ResultSet create(String tableName) {
		
		return readResultSet;
	}
	
	public ResultSet read(String tableName) {
		
		
		
		//mano db. = "Select * from " + table name
		//read while read lialiala
		String readQuerry = "SELECT * FROM " + tableName +";";

		try {
			readResultSet = statements.executeQuery(readQuerry);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// List<Object> dbList = new ArrayList<Object>();
		return readResultSet;
	}

	public ResultSet update(String tableName) {
		// TODO Auto-generated method stub
		return readResultSet;
	}

	public ResultSet delete(String tableName) {
		// TODO Auto-generated method stub
		return readResultSet;
	}


	
}
