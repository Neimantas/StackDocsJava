package Services.Impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Models.CrudUpdate;
import Models.DAL.ExamplesDAL;
import Models.DTO.CreateTableDTO;
import Models.DTO.DeleteTableDTO;
import Models.DTO.ReadTableDTO;
import Models.DTO.UpdateTableDTO;
import Services.ICache;
import Services.ICrud;
import Services.IDataBase;

public class CRUD implements ICrud {
	private IDataBase db;
	private Statement statements;
	private Connection conn;
	ICache cache;

	public CRUD(DataBaseImpl databaseImpl, CacheImpl cacheImpl) {
		db = databaseImpl;
		cache = cacheImpl;
	}

	public CreateTableDTO create(Object dal) {
		setConnection();
		CreateTableDTO createTableDTO = new CreateTableDTO();
		String fullValuesString = "";

		Class classInput = dal.getClass();
		String tableName = classInput.getSimpleName();
		tableName = tableName.replaceAll("DAL", "");

		Field[] classFields = classInput.getFields();

		for (Field column : classFields) {
			// for x -> fullValueString += columnNama + " " + columnvalue
			try {
				if (column.getType() == String.class) {
					fullValuesString += ("\"");
					fullValuesString += (column.get(dal));
					fullValuesString += ("\",");
				} else {

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
			int rowsAffected = statements.executeUpdate(readQuerry);
			createTableDTO.setSuccess(true);
			createTableDTO.setMessage("Insertion. New " + tableName + " created. " + rowsAffected + " rows affected. ");
		} catch (SQLException e) {

			createTableDTO.setSuccess(false);
			createTableDTO.setMessage(e.getMessage());
			return createTableDTO;
		} finally {
			db.close();
		}
		cache.remove(tableName);
		return createTableDTO;

	}

	public ReadTableDTO read(Object dal) {
		Class someClass = dal.getClass();
		String tableName = someClass.getSimpleName();
		tableName = tableName.replaceAll("DAL", "");
		if (cache.get(tableName) != null) {
			return (ReadTableDTO) cache.get(tableName);
		}
		Object retDAL = null;
		Field[] objFields = someClass.getFields();
		Class[] fieldTypes = new Class[objFields.length];
		for (int k = 0; k < fieldTypes.length; k++) {
			fieldTypes[k] = objFields[k].getType();
		}
		setConnection();
		String readQuerry = "SELECT * FROM " + tableName + ";";
		try {
			ResultSet rs = statements.executeQuery(readQuerry);
			ReadTableDTO ret = new ReadTableDTO();
			List<Object> retList = new ArrayList<>();
			while (rs.next()) {
				retDAL = someClass.newInstance();
				for (int i = 0; i < objFields.length; i++) {

					if (fieldTypes[i] == String.class) {
						objFields[i].set(retDAL, rs.getString(i + 1));
					} else if (fieldTypes[i] == int.class) {

						objFields[i].set(retDAL, rs.getInt(i + 1));
					} else if (fieldTypes[i] == byte.class) {

						objFields[i].set(retDAL, rs.getByte(i + 1));
					}
				}
				retList.add(retDAL);

			}

			// creating instance of object that we next return.
			ret.setSuccess(true);
			ret.setMessage("success");
			ret.setReadResultSet(retList);
			cache.put(tableName, ret);

			// long finish = System.currentTimeMillis();
			// System.out.println(finish - start);
			if (db != null)
				db.close();
			return ret;

		} catch (InstantiationException | IllegalAccessException | SQLException e) {

			ReadTableDTO ret = new ReadTableDTO();
			ret.setSuccess(false);
			ret.setMessage(e.getMessage());
			if (db != null)
				db.close();
			return ret;
		}

	}

	public UpdateTableDTO update(CrudUpdate params) {

		setConnection();
		String readQuerry = "UPDATE " + params.tableName + " SET " + params.changeValueOfColum + "='"
				+ params.changeValueTO;

		if (params.getIsWhereUsed()) {

			readQuerry += "' WHERE " + params.conditionColumName + "='" + params.conditionChangeWhereValueIsEqual + "';";
		}

		UpdateTableDTO updateTableDTO = new UpdateTableDTO();

		try {
			System.out.println(readQuerry);
			statements.executeUpdate(readQuerry);
			updateTableDTO.setSuccess(true);
			updateTableDTO.setMessage("Update Successful");
		} catch (SQLException e) {

			updateTableDTO.setSuccess(false);
			updateTableDTO.setMessage(e.getMessage());
			return updateTableDTO;
		} finally {
			db.close();
		}
		cache.remove(params.tableName);
		return updateTableDTO;
	}

	public DeleteTableDTO delete(String tableName, String conditionColum, String conditionValue) {
		setConnection();
		DeleteTableDTO deleteTableDTO = new DeleteTableDTO();
		String readQuerry = "DELETE FROM " + tableName + " WHERE " + conditionColum + "='" + conditionValue + "';";
		try {
			statements.executeUpdate(readQuerry);
			deleteTableDTO.setSuccess(true);
			deleteTableDTO.setMessage("Record deleted successfully.");
		} catch (SQLException e) {

			deleteTableDTO.setSuccess(false);
			deleteTableDTO.setMessage(e.getMessage());
			return deleteTableDTO;
		} finally {
			db.close();
		}
		cache.remove(tableName);
		return deleteTableDTO;
	}

	private void setConnection() {
		conn = db.connect();
		try {
			statements = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}