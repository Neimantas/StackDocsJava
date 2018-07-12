package Services.Impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Services.IDataBase;
import javax.inject.Singleton;

@Singleton
public class DataBaseImpl implements IDataBase {

	private Connection _connection;

	public Connection connect() {

		try {
			// db parameters
			String url = "jdbc:sqlite:stackDocsJava/src/main/recources/stack.db";
			Class.forName("org.sqlite.JDBC").newInstance();
			// create a connection to the database
			_connection = DriverManager.getConnection(url);

			System.out.println("Connection to SQLite has been established.");
			return _connection;

		} catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}

		return null;
	}

	public void close() {

		if (_connection != null) {
			try {
				_connection.close();
				System.out.println("DB connection was closed");
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}

	}

}
