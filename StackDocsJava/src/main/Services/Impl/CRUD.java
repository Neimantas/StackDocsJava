package Services.Impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Models.CrudUpdate;
import Models.DTO.CreateTableDTO;
import Models.DTO.ReadTableDTO;
import Models.DTO.UpdateTableDTO;
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

	public CreateTableDTO create(String tableName, String[] values) {
		CreateTableDTO createTableDTO = new CreateTableDTO();
		String fullValuesString = "";
		for (int i = 0; i < values.length; i++) {
			fullValuesString.concat(values[i]);
			fullValuesString.concat(",");
		}

		fullValuesString = fullValuesString.substring(0, fullValuesString.length() - 1);

		String readQuerry = "INSERT INTO " + tableName + "(" + fullValuesString + ");";

		try {
			readResultSet = statements.executeQuery(readQuerry);
			
			createTableDTO.setSuccess(true);
		} catch (SQLException e) {
			
			createTableDTO.setSuccess(false);
			createTableDTO.setMessage(e.getMessage());
			return createTableDTO;
		}
		return createTableDTO;

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


	public UpdateTableDTO update(CrudUpdate params) {
		String readQuerry = "UPDATE " + params.tableName + " SET " + params.changeValueOfColum + "='" 
		+ params.changeValueTO;


		if(params.getIsWhereUsed()) {

			readQuerry += "' WHERE"
					+ params.conditionColumName + "='" + params.conditionChangeWhereValueIsEqual + "';";
		}
	
		
		UpdateTableDTO updateTableDTO = new UpdateTableDTO();
		
		try {
			readResultSet = statements.executeQuery(readQuerry);
			updateTableDTO.setSuccess(true);
			return updateTableDTO;
		} catch (SQLException e) {

			updateTableDTO.setSuccess(false);
			updateTableDTO.setMessage(e.getMessage());
			return updateTableDTO;
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
