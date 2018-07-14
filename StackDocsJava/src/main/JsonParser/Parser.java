package JsonParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import com.google.gson.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Parser {
    /*
     * Run as java application
     */
	public static void main( String[] args ) {
		
		Connection conn = connect();
		createTables(conn);
//		Use fill methods with commit!
		fillExampleTableCommit(conn);
		fillTopicsTableCommit(conn);
		fillLanguageTagsTableCommit(conn);
		
//      Dont use these!!! It takes very long time to fill tables, because each row is commited.  
//		fillLanguageTagsTable();
//		fillTopicsTable();
//		fillExampleTable();
    }
	
	public static Connection connect() {
        String url = "jdbc:sqlite:src/main/resources/stackTestCommit.db";
        try {
        	Connection conn = DriverManager.getConnection(url);
        	System.out.println("Connection to SQLite has been established.");
        	return conn;
        }
        
        catch(SQLException ex) {
        	ex.printStackTrace();
        }
        return null;
	}
	
	public static void createTables(Connection _conn) {
		Connection conn = _conn;
		String table1 = "CREATE TABLE LanguageTags  "
		 		+ "(LanguageId INTEGER PRIMARY KEY, "
		 		 + "Tag Text,"
		 		 + "Title Text, "
		 		 + "HelloWorldTopicId INTEGER"
		 		 + ") WITHOUT ROWID; ";
		 		 
		 String table2 = "CREATE TABLE Topics"
		 		 + "(TopicId INTEGER PRIMARY KEY,"
		 		 + "LanguageId INTEGER,"
		 		 + "Title TEXT,"
		 		 + "IsHelloWorldTopic INTEGER,"
		 		 + "IntroductionHtml TEXT,"
		 		 + "SyntaxHtml TEXT,"
		 		 + "ParametersHtml TEXT,"
		 		 + "RemarksHtml TEXT"
		 		 + ") WITHOUT ROWID;";
		 		
		  String table3 = "CREATE TABLE Examples"
		 		 + "(ExampleId INTEGER PRIMARY KEY,"
		 		 + "TopicId INTEGER,"
		 		 + "Title TEXT,"
		 		 + "BodyHtml TEXT"
		 		 + ") WITHOUT ROWID;";
		 
		 try(Statement stmt = conn.createStatement()) {
			 stmt.execute(table1);
			 stmt.execute(table2);
			 stmt.execute(table3);
			 System.out.println("Tables have been created");
		 } catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void insertDataToLanguageTag(Connection _conn, JsonObject obj) {
		Connection conn = _conn;
		String sql = "INSERT INTO LanguageTags(LanguageId, Tag, Title, HelloWorldTopicId) VALUES(?, ?, ?, ?);";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, obj.get("Id").getAsInt());
			pstmt.setString(2, obj.get("Tag").getAsString());
			pstmt.setString(3, obj.get("Title").getAsString());
			pstmt.setInt(4, obj.get("HelloWorldDocTopicId").getAsInt());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void insertDataToLanguageTagCommit(Connection _conn, List<JsonObject> objs) {
		Connection conn = _conn;
		String sql = "INSERT INTO LanguageTags(LanguageId, Tag, Title, HelloWorldTopicId) VALUES(?, ?, ?, ?);";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			conn.setAutoCommit(false);
			for (JsonObject obj : objs) {
				pstmt.setInt(1, obj.get("Id").getAsInt());
				pstmt.setString(2, obj.get("Tag").getAsString());
				pstmt.setString(3, obj.get("Title").getAsString());
				pstmt.setInt(4, obj.get("HelloWorldDocTopicId").getAsInt());
				pstmt.executeUpdate();
			}
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void insertDataToTopics(Connection _conn, JsonObject obj) {
		Connection conn = _conn;
		String sql = "INSERT INTO Topics(TopicId, LanguageId, Title, "
				+ "IsHelloWorldTopic, IntroductionHtml, SyntaxHtml, "
				+ "ParametersHtml, RemarksHtml) VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, obj.get("Id").getAsInt());
			pstmt.setString(2, obj.get("DocTagId").getAsString());
			pstmt.setString(3, obj.get("Title").getAsString());
			//SQLite doesn't have booleans. Used integers insteead 1 - true, 0 - false
			pstmt.setInt(4, (obj.get("IsHelloWorldTopic").getAsBoolean())?1:0); 
			//not all topics have IntroductionHtml field
			pstmt.setString(5, (obj.get("IntroductionHtml") != null) ? obj.get("IntroductionHtml").getAsString() : "");
			pstmt.setString(6, obj.get("SyntaxHtml").getAsString());
			pstmt.setString(7, obj.get("ParametersHtml").getAsString());
			pstmt.setString(8, obj.get("RemarksHtml").getAsString());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void insertDataToTopicsCommit(Connection _conn, List<JsonObject> objs) {
		Connection conn = _conn;
		String sql = "INSERT INTO Topics(TopicId, LanguageId, Title, "
				+ "IsHelloWorldTopic, IntroductionHtml, SyntaxHtml, "
				+ "ParametersHtml, RemarksHtml) VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			conn.setAutoCommit(false);
			for (JsonObject obj:objs) {
				pstmt.setInt(1, obj.get("Id").getAsInt());
				pstmt.setString(2, obj.get("DocTagId").getAsString());
				pstmt.setString(3, obj.get("Title").getAsString());
				//SQLite doesn't have booleans. Used integers insteead 1 - true, 0 - false
				pstmt.setInt(4, (obj.get("IsHelloWorldTopic").getAsBoolean())?1:0); 
				//not all topics have IntroductionHtml field
				pstmt.setString(5, (obj.get("IntroductionHtml") != null) ? obj.get("IntroductionHtml").getAsString() : "");
				pstmt.setString(6, obj.get("SyntaxHtml").getAsString());
				pstmt.setString(7, obj.get("ParametersHtml").getAsString());
				pstmt.setString(8, obj.get("RemarksHtml").getAsString());
				pstmt.executeUpdate();
			}
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void insertDataToExamples(Connection _conn, JsonObject obj) {
		Connection conn = _conn;
		String sql = "INSERT INTO Examples(ExampleId, TopicId, Title, BodyHtml) VALUES(?, ?, ?, ?);";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, obj.get("Id").getAsInt());
			pstmt.setInt(2, obj.get("DocTopicId").getAsInt());
			pstmt.setString(3, obj.get("Title").getAsString());
			pstmt.setString(4, obj.get("BodyHtml").getAsString());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void insertDataToExamplesCommit(Connection _conn, List<JsonObject> objs) {
		Connection conn = _conn;
		String sql = "INSERT INTO Examples(ExampleId, TopicId, Title, BodyHtml) VALUES(?, ?, ?, ?);";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			conn.setAutoCommit(false);
			for(JsonObject obj:objs) {
				pstmt.setInt(1, obj.get("Id").getAsInt());
				pstmt.setInt(2, obj.get("DocTopicId").getAsInt());
				pstmt.setString(3, obj.get("Title").getAsString());
				pstmt.setString(4, obj.get("BodyHtml").getAsString());
				pstmt.executeUpdate();
			}
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void fillLanguageTagsTable() {
		Connection conn = connect();
		JsonArray array = stringToJsonArray(readJson("src/main/resources/doctags.json"));
		if (array != null) {
			Iterator<JsonElement> itr = array.iterator();
			System.out.println("Filling LanguageTags table...");
			while(itr.hasNext()) {
				insertDataToLanguageTag(conn, itr.next().getAsJsonObject());
			}
			System.out.println("Insertion to LanguageTags completed successfully!");
		} else {
			System.out.println("JsonArray is null!");
		}
		
	}
	
	public static void fillLanguageTagsTableCommit(Connection inConn) {
		Connection conn = inConn;
		JsonArray array = stringToJsonArray(readJson("src/main/resources/doctags.json"));
		if (array != null) {
			Iterator<JsonElement> itr = array.iterator();
			List<JsonObject> jsonObjs = new ArrayList<>();
			
			while(itr.hasNext()) 
				jsonObjs.add(itr.next().getAsJsonObject());
			System.out.println("Filling LanguageTags table...");
			insertDataToLanguageTagCommit(conn, jsonObjs);
			System.out.println("Insertion to LanguageTags completed successfully!");
		} else {
			System.out.println("JsonArray is null!");
		}
		
	}
	
	public static void fillTopicsTable(Connection inConn) {
		Connection conn = inConn;
		JsonArray array = stringToJsonArray(readJson("src/main/resources/topics.json"));
		if (array != null) {
			Iterator<JsonElement> itr = array.iterator();
			System.out.println("Filling Topics table...");
			while(itr.hasNext()) {
				insertDataToTopics(conn, itr.next().getAsJsonObject());
			}
			System.out.println("Insertion to Topics completed successfully!");
		} else {
			System.out.println("JsonArray is null!");
		}
		
	}
	
	public static void fillTopicsTableCommit(Connection inConn) {
		Connection conn = inConn;
		JsonArray array = stringToJsonArray(readJson("src/main/resources/topics.json"));
		List<JsonObject> jsonObjs = new ArrayList<>();
		if (array != null) {
			Iterator<JsonElement> itr = array.iterator();
			while(itr.hasNext()) 
				jsonObjs.add(itr.next().getAsJsonObject());
			System.out.println("Filling Topics table...");
			insertDataToTopicsCommit(conn, jsonObjs);
			System.out.println("Insertion to Topics completed successfully!");
		} else {
			System.out.println("JsonArray is null!");
		}
		
	}
	
	public static void fillExampleTable() {
		Connection conn = connect();
		JsonArray array = stringToJsonArray(readJson("src/main/resources/examples.json"));
		if (array != null) {
			Iterator<JsonElement> itr = array.iterator();
			System.out.println("Filling Examples table...");
			while(itr.hasNext()) {
				insertDataToExamples(conn, itr.next().getAsJsonObject());
			}
			System.out.println("Insertion to Examples completed successfully!");
		} else {
			System.out.println("JsonArray is null!");
		}
		
	}
	
	public static void fillExampleTableCommit(Connection inConn) {
		Connection conn = inConn;
		JsonArray array = stringToJsonArray(readJson("src/main/resources/examples.json"));
		List<JsonObject> jsonObjs = new ArrayList<>();
		if (array != null) {
			Iterator<JsonElement> itr = array.iterator();
			//filling list of json objects
			while(itr.hasNext()) 
				jsonObjs.add(itr.next().getAsJsonObject());
			System.out.println("Filling Examples table...");
			insertDataToExamplesCommit(conn, jsonObjs);
			System.out.println("Insertion to Examples completed successfully!");
		} else {
			System.out.println("JsonArray is null!");
		}
		
	}
	
	public static JsonArray stringToJsonArray(String json) {
		JsonParser parser = new JsonParser();
		JsonElement jsonTree = parser.parse(json);
		if(jsonTree.isJsonArray()) return jsonTree.getAsJsonArray();
		return null;
	}
	
	public static String readJson(String path) {
		File file = new File(path);
		StringBuilder sb = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line = "";
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return sb.toString();
	}
}
