package Services.Impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Models.DTO.ReadTableDTO;
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

	public void create(String tableName, String[] values) {

		String fullValuesString = "";
		for (int i = 0; i < values.length; i++) {
			fullValuesString.concat(values[i]);
			fullValuesString.concat(",");
		}

		fullValuesString = fullValuesString.substring(0, fullValuesString.length() - 1);

		String readQuerry = "INSERT INTO " + tableName + "(" + fullValuesString + ");";

		try {
			readResultSet = statements.executeQuery(readQuerry);
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public ReadTableDTO read(String tableName) {

		String readQuerry = "SELECT * FROM " + tableName + ";";
			ReadTableDTO readTableDTO = new ReadTableDTO();
		try {
			
			readTableDTO.setReadResultSet(statements.executeQuery(readQuerry));
			readTableDTO.setSuccess(true);
		} catch (SQLException e) {

			readTableDTO.setSuccess(false);
			readTableDTO.setMessage(e.getMessage());
			return readTableDTO;
		}

		return readTableDTO;
	}

	public void update(String tableName, String changeValueOfColum, String changeValueTO, String conditionColumName,
			String conditionChangeWhereValueIsEqual) {

		String readQuerry = "UPDATE " + tableName + " SET " + changeValueOfColum + "='" + changeValueTO + "' WHERE"
				+ conditionColumName + "='" + conditionChangeWhereValueIsEqual + "';";

		try {
			readResultSet = statements.executeQuery(readQuerry);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void delete(String tableName, String conditionColum, String conditionValue) {
		String readQuerry = "DELETE FROM " + tableName + " WHERE " + conditionColum + "='" + conditionValue + "';";

		try {
			statements.executeQuery(readQuerry);
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

}
