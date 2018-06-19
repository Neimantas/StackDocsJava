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
	private ResultSet readResultSet;
	private Connection conn;
	private int count;
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
		} finally {
			db.close();
		}

		return createTableDTO; 

	}

//	public ReadTableDTO read2(Object dal) {
//		
//		Class classInput = dal.getClass();
//		String tableName = classInput.getSimpleName();
//		tableName = tableName.replaceAll("DAL", "");
//		
//		
//				
//		String readQuerry = "SELECT * FROM " + tableName + ";";
//		System.out.println(readQuerry);
//		ReadTableDTO readTableDTO = new ReadTableDTO();
//		try {
//
//			readTableDTO.setReadResultSet(statements.executeQuery(readQuerry));
//			readTableDTO.setSuccess(true);
//		} catch (SQLException e) {
//
//			readTableDTO.setSuccess(false);
//			readTableDTO.setMessage(e.getMessage());
//			return readTableDTO;
//		}
//
//		// if(tablename == Examples)(
//		//readExamples
//		//}
//		
//		return readTableDTO;
//	}
//	
	public ReadTableDTO read(Object dal) {
		
//		long start = System.currentTimeMillis();
		Class someClass = dal.getClass();
		Field[] objFields = someClass.getFields();
		Class[] fieldTypes = new Class[objFields.length];
		
		for(int k = 0; k<fieldTypes.length; k++) {
			fieldTypes[k] = objFields[k].getType();
		}
		
		String tableName = someClass.getSimpleName();
		tableName = tableName.replaceAll("DAL", "");

//		ICache cache = CacheImpl.getInstance();
		if( cache.get(tableName) != null) {
			return (ReadTableDTO)cache.get(tableName);
		}
		Object retDAL = null;
		//if there is no info in cache, connect to db
		setConnection();
		
		String readQuerry = "SELECT * FROM " + tableName + ";";
		someClass.getSimpleName();
		try {
		
			ResultSet rs = statements.executeQuery(readQuerry);

			
			ReadTableDTO ret = new ReadTableDTO();
			List<Object> retList = new ArrayList<>(); 
			while(rs.next()) {
				retDAL = someClass.newInstance();
				Class retDALClass = retDAL.getClass();
//				Field[] retDALFields = retDALClass.getFields();
				
				for(int i = 0; i<objFields.length; i++) {
					
					if(fieldTypes[i] == String.class) {
						objFields[i].set(retDAL, rs.getString(i+1));
					}
					else if(fieldTypes[i] == int.class) {
						
						objFields[i].set(retDAL, rs.getInt(i+1));
					}
					else if(fieldTypes[i] == byte.class) {
						
						objFields[i].set(retDAL, rs.getByte(i+1));
					}
				}
				retList.add(retDAL);
				
			}
			
			//creating instance of object that we next return.
			ret.setSuccess(true);
			ret.setMessage("success");
			ret.setReadResultSet(retList);
			cache.put(tableName, ret);
			
//			long finish = System.currentTimeMillis();
//			System.out.println(finish - start);
			if (db != null) db.close();
			return ret;
			
			
		} catch (InstantiationException | IllegalAccessException | SQLException e) {
			
			
			ReadTableDTO ret = new ReadTableDTO();
			ret.setSuccess(false);
			ret.setMessage(e.getMessage());
			if (db != null) db.close();
			return ret;
		} 
		
		
		
	}

//	public ReadTableDTO read3(Object dal) {
//		long start = System.currentTimeMillis();
//		Class someClass = dal.getClass();
//		
//		String tableName = someClass.getSimpleName();
//		tableName = tableName.replaceAll("DAL", "");
//		Object retDAL = null;
//		
//		String readQuerry = "SELECT * FROM " + tableName + ";";
//		someClass.getSimpleName();
//		try {
//		
//			ResultSet rs = statements.executeQuery(readQuerry);
//			
//			ReadTableDTO ret = new ReadTableDTO();
//			List<Object> retList = new ArrayList<>(); 
//			while(rs.next()) {
//				
//				retDAL = someClass.newInstance();
//				Class retDALClass = retDAL.getClass();
//				Field[] retDALFields = retDALClass.getFields();
//				
////				for(int i = 0; i<retDALFields.length; i++) {
////					
////					if(retDALFields[i].getType() == String.class) {
////						retDALFields[i].set(retDAL, rs.getString(i+1));
////					}
////					else if(retDALFields[i].getType() == int.class) {
////						
////						retDALFields[i].set(retDAL, rs.getInt(i+1));
////					}
////					else if(retDALFields[i].getType() == byte.class) {
////						
////						retDALFields[i].set(retDAL, rs.getByte(i+1));
////					}
////				}
//				retDALFields[0].set(retDAL, rs.getInt(1));
//				retDALFields[1].set(retDAL, rs.getInt(2));
//				retDALFields[2].set(retDAL, rs.getString(3));
//				retDALFields[3].set(retDAL, rs.getByte(4));
//				retDALFields[4].set(retDAL, rs.getString(5));
//				retDALFields[5].set(retDAL, rs.getString(6));
//				retDALFields[6].set(retDAL, rs.getString(7));
//				retDALFields[7].set(retDAL, rs.getString(8));
//				retList.add(retDAL);
//				
//			}
//			
//			//creating instance of object that we next return.
//			ret.setSuccess(true);
//			ret.setMessage("success");
//			ret.setReadResultSet(retList);
//			long finish = System.currentTimeMillis();
//			System.out.println(finish - start);
//			return ret;
//			
//			
//		} catch (InstantiationException | IllegalAccessException | SQLException e) {
//			
//			
//			ReadTableDTO ret = new ReadTableDTO();
//			ret.setSuccess(false);
//			ret.setMessage(e.getMessage());
//			
//			return ret;
//		}
//		
//		
//		
//	}
	
	public UpdateTableDTO update(CrudUpdate params) {
		setConnection();
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
		} finally {
			db.close();
		}

		return updateTableDTO;
	}

	public DeleteTableDTO delete(String tableName, String conditionColum, String conditionValue) {
		setConnection();
		DeleteTableDTO deleteTableDTO = new DeleteTableDTO();
		String readQuerry = "DELETE FROM " + tableName + " WHERE " + conditionColum + "='" + conditionValue + "';";

		try {
			statements.executeQuery(readQuerry);
			deleteTableDTO.setSuccess(true);

		} catch (SQLException e) {

			deleteTableDTO.setSuccess(false);
			deleteTableDTO.setMessage(e.getMessage());
			return deleteTableDTO;
		} finally {
			db.close();
		}

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
