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
	}

	public void createDB(){
		
	}	
	
	//CRUD
	//READ
	
	public List<Object> create(String tableName) {
		
		return new ArrayList<Object>();
	}
	
	public List<Object> read(String tableName) {
		// TODO Auto-generated method stub
		//mano db. = "Select * from " + table name
		//read while read lialiala
		List<Object> dbList = new ArrayList<Object>();
		return dbList;
	}

	public List<Object> update(String tableName) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Object> delete(String tableName) {
		// TODO Auto-generated method stub
		return null;
	}


	
}
