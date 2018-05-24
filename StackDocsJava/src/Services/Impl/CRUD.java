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

public class CRUD implements ICrud{
	private Statement statements;
	private ResultSet readResultSet;
	private Connection conn;
	
	public CRUD() {
		
		 IDataBase db = new DataBaseImpl();
		 conn = db.connect();
	}

	public void createDB(){
		
	}	
	public List readLanguageDB() { //ideti i parametrus ir sukonfiginti kintamuosius: String language, String title
		String readQuerry = "SELECT LanguageId, Tag, Title, HelloWorldTopicid FROM LanguageTags;";
		List<LanguageTagDAL> languageList = new ArrayList();
		try {
			readResultSet = statements.executeQuery(readQuerry);
			
			while(readResultSet.next()) {
				//ideti i objekta ir sukrauti i Lista (irgi dar reikia sukurti)
				LanguageTagDAL languageTag = new LanguageTagDAL();
				languageTag.languageId = readResultSet.getInt("LanguageId");
				languageTag.tag = readResultSet.getInt("Tag");
				languageTag.title = readResultSet.getString("Title");
				languageTag.helloWorldTopicId = readResultSet.getInt("HelloWorldTopicid");
				
				languageList.add(languageTag);
		
			}
			
			
		} catch (SQLException e) {

			// Sukurti tuscia objekta, siusti atgal
			e.printStackTrace();
		}
		
		return languageList;
	
	}
	public void readTitleDB(int titleID) {
		
	}
	public void updateDB() {
		
	}
	public void deleteDB() {
		
	}


	
}
