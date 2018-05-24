package dB_CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CRUD implements ICrud{
	private Statement statements;
	private ResultSet readResultSet;
	public CRUD() {
		
		Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:D:/Programavimas/stackDB-master/stack.db";
            
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            
            System.out.println("Connection to SQLite has been established.");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
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
