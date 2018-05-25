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

	public void create(String tableName) {
		String readQuerry = "CREATE TABLE " + tableName + ";";

		try {
			readResultSet = statements.executeQuery(readQuerry);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

	}

	public ResultSet read(String tableName) {

		String readQuerry = "SELECT * FROM " + tableName + ";";

		try {
			readResultSet = statements.executeQuery(readQuerry);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return readResultSet;
	}

	public void update(String originalTableName, String renamedTableName) {
//		String readQuerry = "ALTER TABLE " + originalTableName + " RENAME " + renamedTableName + ";";
//
//		try {
//			readResultSet = statements.executeQuery(readQuerry);
//		} catch (SQLException e) {
//
//			e.printStackTrace();
//		}
	}

	public void delete(String tableName) {
//		String readQuerry = "DROP TABLE " + tableName + ";";
//
//		try {
//			statements.executeQuery(readQuerry);
//		} catch (SQLException e) {
//			
//			e.printStackTrace();
//		}

	}

}
