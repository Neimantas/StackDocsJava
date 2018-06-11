package Services.Impl;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Services.IDataBase;
import javax.inject.Singleton;

@Singleton
public class DataBaseImpl implements IDataBase {
	private static DataBaseImpl instance = null;
	private Connection conn = null;

	public DataBaseImpl() {
		instance = this;
	};

	public Connection connect() {

		try {
			// db parameters
			String url = "jdbc:sqlite:stackDocsJava/stack.db";
			Class.forName("org.sqlite.JDBC").newInstance();
			// create a connection to the database
			conn = DriverManager.getConnection(url);

			System.out.println("Connection to SQLite has been established.");
			return conn;

		} catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}

		return null;
	}

	public void close() {

		if (conn != null) {
			try {
				conn.close();
				System.out.println("DB connection was closed");
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}

	}

	public static DataBaseImpl getInstance() {
		// if(instance == null) {
		// instance = new DataBaseImpl();
		// }
		return instance;
	}


}
