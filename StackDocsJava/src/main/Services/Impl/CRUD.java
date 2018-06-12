package Services.Impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Models.CrudUpdate;
import Models.DAL.ExamplesDAL;
import Models.DTO.CreateTableDTO;
import Models.DTO.DeleteTableDTO;
import Models.DTO.ReadTableDTO;
import Models.DTO.UpdateTableDTO;
import Services.ICrud;
import Services.IDataBase;

public class CRUD implements ICrud {
	private Statement statements;
	private ResultSet readResultSet;
	private Connection conn;

	public CRUD() {

		IDataBase db = DataBaseImpl.getInstance();
		conn = db.connect();
		try {
			statements = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public CreateTableDTO create(Object dal) {
		CreateTableDTO createTableDTO = new CreateTableDTO();
		String fullValuesString = "";
		
		Class classInput = dal.getClass();
		String tableName = classInput.getSimpleName();
		tableName = tableName.replaceAll("DAL", "");
		
		Field[] classFields = classInput.getFields();
		
		for(Field column : classFields) {
			//for x -> fullValueString += columnNama + " " + columnvalue
			try {
				if(column.getType() == String.class) {
					fullValuesString += ("\"");
					fullValuesString += (column.get(dal));			
					fullValuesString += ("\",");
				}else {
				
				fullValuesString += (column.get(dal));			
				fullValuesString += (",");
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}	
		}
		fullValuesString = fullValuesString.substring(0, fullValuesString.length() - 1);
		String readQuerry = "INSERT INTO " + tableName + " VALUES (" + fullValuesString + ");";
			System.out.println(readQuerry);
		try {
			statements.executeQuery(readQuerry);
			createTableDTO.setSuccess(true);
		} catch (SQLException e) {

			createTableDTO.setSuccess(false);
			createTableDTO.setMessage(e.getMessage());
			return createTableDTO;
		} 

		return createTableDTO;

	}

	public ReadTableDTO read(Object dal) {
		
		Class classInput = dal.getClass();
		String tableName = classInput.getSimpleName();
		tableName = tableName.replaceAll("DAL", "");
		
		
				
		String readQuerry = "SELECT * FROM " + tableName + ";";
		System.out.println(readQuerry);
		ReadTableDTO readTableDTO = new ReadTableDTO();
		try {

			readTableDTO.setReadResultSet(statements.executeQuery(readQuerry));
			readTableDTO.setSuccess(true);
		} catch (SQLException e) {

			readTableDTO.setSuccess(false);
			readTableDTO.setMessage(e.getMessage());
			return readTableDTO;
		}

		// if(tablename == Examples)(
		//readExamples
		//}
		
		return readTableDTO;
	}
	
	public Object read2(Object dal) {
		
		Class someClass = dal.getClass();
		String tableName = someClass.getSimpleName();
		tableName = tableName.replaceAll("DAL", "");
		Object retDAL = null;
		
		String readQuerry = "SELECT * FROM " + tableName + ";";
		
		try {
			
			ResultSet rs = statements.executeQuery(readQuerry);
			
			while(rs.next()) {
				retDAL = someClass.newInstance();
				Class retDALClass = retDAL.getClass();
				Field[] retDALFields = retDALClass.getFields();
				
				for(int i = 0; i<retDALFields.length; i++) {
					
					if(retDALFields[i].getType() == String.class) {
						retDALFields[i].set(retDAL, rs.getString(i+1));
					}
					if(retDALFields[i].getType() == int.class) {
						
						retDALFields[i].set(retDAL, rs.getInt(i+1));
					}
					if(retDALFields[i].getType() == byte.class) {
						
						retDALFields[i].set(retDAL, rs.getByte(i+1));
					}
				}
				
			}
			
			//creating instance of object that we next return.
			
			
			
			
		} catch (InstantiationException | IllegalAccessException | SQLException e) {
			e.printStackTrace();
		}
		
		return retDAL;
		
	}

	public UpdateTableDTO update(CrudUpdate params) {
		String readQuerry = "UPDATE " + params.tableName + " SET " + params.changeValueOfColum + "='"
				+ params.changeValueTO;

		if (params.getIsWhereUsed()) {

			readQuerry += "' WHERE" + params.conditionColumName + "='" + params.conditionChangeWhereValueIsEqual + "';";
		}

		UpdateTableDTO updateTableDTO = new UpdateTableDTO();

		try {
			readResultSet = statements.executeQuery(readQuerry);
			updateTableDTO.setSuccess(true);

		} catch (SQLException e) {

			updateTableDTO.setSuccess(false);
			updateTableDTO.setMessage(e.getMessage());
			return updateTableDTO;
		}

		return updateTableDTO;
	}

	public DeleteTableDTO delete(String tableName, String conditionColum, String conditionValue) {

		DeleteTableDTO deleteTableDTO = new DeleteTableDTO();
		String readQuerry = "DELETE FROM " + tableName + " WHERE " + conditionColum + "='" + conditionValue + "';";

		try {
			statements.executeQuery(readQuerry);
			deleteTableDTO.setSuccess(true);

		} catch (SQLException e) {

			deleteTableDTO.setSuccess(false);
			deleteTableDTO.setMessage(e.getMessage());
			return deleteTableDTO;
		}

		return deleteTableDTO;

	}

}
