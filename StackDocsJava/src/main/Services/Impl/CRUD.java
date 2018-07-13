package Services.Impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Models.Business.CrudUpdate;
import Models.Const.OperationStatus;
import Models.DTO.CreateTableDTO;
import Models.DTO.DeleteTableDTO;
import Models.DTO.ReadTableDTO;
import Models.DTO.UpdateTableDTO;
import Services.ICache;
import Services.ICrud;
import Services.IDataBase;

public class CRUD implements ICrud {

	private IDataBase _database;
	private Statement _statement;
	private Connection _connection;
	private ICache _cache;
	private ResultSet _resultSet;

	public CRUD(DataBaseImpl databaseImpl, CacheImpl cacheImpl) {
		_database = databaseImpl;
		_cache = cacheImpl;
	}

	public CreateTableDTO create(Object dal) {
		try {
			setConnection();

			String fullValuesString = "";

			Class classInput = dal.getClass();
			String tableName = classInput.getSimpleName();
			tableName = tableName.replaceAll("DAL", "");

			Field[] classFields = classInput.getFields();

			for (Field column : classFields) {
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

			_statement.executeUpdate(readQuerry);
			_cache.remove(tableName);
		} catch (SQLException e) {
			return new CreateTableDTO(false, e.getMessage());
		} finally {
			if (_database != null)
				_database.close();
		}
		return new CreateTableDTO(true, OperationStatus.success.getMessage());
	}

	public ReadTableDTO read(Object dal) {
		try {
			Class someClass = dal.getClass();
			Field[] objFields = someClass.getFields();
			Class[] fieldTypes = new Class[objFields.length];

			for (int k = 0; k < fieldTypes.length; k++) {
				fieldTypes[k] = objFields[k].getType();
			}
			String tableName = someClass.getSimpleName();
			tableName = tableName.replaceAll("DAL", "");
			if (_cache.get(tableName) != null) {
				return (ReadTableDTO) _cache.get(tableName);
			}
			Object retDAL = null;
			setConnection();
			String readQuerry = "SELECT * FROM " + tableName + ";";
			ResultSet rs = _statement.executeQuery(readQuerry);
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

			ret.success = true;
			ret.message = "success";
			ret.readResultSet = retList;
			_cache.put(tableName, ret);

			// This one is not in Finnaly section, because need to keep open db till job is
			// done.
			// If put this one in Finnay, there will be a lot of db cosing and reopen. DO
			// NOT MOVE TO FINNALY for god sake...
			if (_database != null)
				_database.close();
			return ret;

		} catch (InstantiationException | IllegalAccessException | SQLException e) {
			ReadTableDTO ret = new ReadTableDTO();
			ret.success = false;
			ret.message = e.getMessage();
			return ret;
		}
	}

	public UpdateTableDTO update(CrudUpdate params) {
		try {
			setConnection();
			String readQuerry = "UPDATE " + params.tableName + " SET " + params.changeValueOfColum + "='"
					+ params.changeValueTO;
			if (params.getIsWhereUsed()) {
				readQuerry += "' WHERE " + params.conditionColumName + "='" + params.conditionChangeWhereValueIsEqual
						+ "';";
			}
			_statement.executeUpdate(readQuerry);

		} catch (SQLException e) {
			return new UpdateTableDTO(false, e.getMessage());
		} finally {
			_database.close();
		}
		_cache.remove(params.tableName);
		return new UpdateTableDTO(true, OperationStatus.success.getMessage());
	}

	public DeleteTableDTO delete(String tableName, String conditionColum, String conditionValue) {
		try {
			setConnection();
			String readQuerry = "DELETE FROM " + tableName + " WHERE " + conditionColum + "='" + conditionValue + "';";
			_statement.executeUpdate(readQuerry);
			_cache.remove(tableName);
		} catch (SQLException e) {
			return new DeleteTableDTO(false, e.getMessage());
		} finally {
			_database.close();
		}
		return new DeleteTableDTO(true, OperationStatus.success.getMessage());
	}

	private void setConnection() {
		try {
			_connection = _database.connect();
			_statement = _connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}