package Services.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Models.DAL.LanguageTagDAL;
import Models.DAL.TopicsDAL;
import Services.ICrud;
import Services.IHigherService;

public class HigherServiceImpl implements IHigherService {
	
//	public List readLanguageDB() { //ideti i parametrus ir sukonfiginti kintamuosius: String language, String title
//		String readQuerry = "SELECT LanguageId, Tag, Title, HelloWorldTopicid FROM LanguageTags;";
//		List<LanguageTagDAL> languageList = new ArrayList();
//		try {
//			readResultSet = statements.executeQuery(readQuerry);
//			
//			while(readResultSet.next()) {
//				//ideti i objekta ir sukrauti i Lista (irgi dar reikia sukurti)
//				
//		
//			}
//			
//			
//		} catch (SQLException e) {
//
//			// Sukurti tuscia objekta, siusti atgal
//			e.printStackTrace();
//		}
//		
//		return languageList;
//	
//	}
//	public void readTitleDB(int titleID) {
//		
//	}
	public List<Object> readLanguageTag(String languageId) {
		
		ICrud crud = new CRUD();
		
		ResultSet resultSet = crud.read("LanguageTags");
		
		List<Object> languageList = new ArrayList<Object>();
		try {
			
			while(resultSet.next()) {
				LanguageTagDAL languageTag = new LanguageTagDAL();
				languageTag.languageId = resultSet.getInt("LanguageId");
				languageTag.tag = resultSet.getInt("Tag");
				languageTag.title = resultSet.getString("Title");
				languageTag.helloWorldTopicId = resultSet.getByte("HelloWorldTopicid");
				
				languageList.add(languageTag);
			}
			
			return languageList;
			
		} catch (SQLException e) {
			
			String error = e.getMessage();
			List<Object> retE = new ArrayList<Object>();
			retE.add(error);
			return retE;
			
		}
		
	}
	public List<Object> readTopics(String topicId) {
		
		ICrud crud = new CRUD();
		
		ResultSet resultSet = crud.read("Topics");
		
		List<Object> topics = new ArrayList<Object>();
		
		try {
			while(resultSet.next()) {
				
				TopicsDAL topicsDal = new TopicsDAL();
				
				topicsDal.topicId = resultSet.getInt("TopicId");
				topicsDal.languageId = resultSet.getInt("LanguageId");
				topicsDal.title = resultSet.getString("Title");
				topicsDal.isHelloWorldTopic = resultSet.getByte("IsHelloWorldTopic");
				topicsDal.syntaxHtml = resultSet.getString("SyntaxHtml");
				topicsDal.parametersHtml = resultSet.getString("ParametersHtml");
				topicsDal.remarksHtml = resultSet.getString("RemarksHtml");
				
				topics.add(topicsDal);
				
			}
			
			return topics;
		} catch (SQLException e) {
			
			String m = e.getMessage();
			List<Object> ret = new ArrayList<Object>();
			ret.add(m);
			return ret;
			
		}
		
	}
	public List<Object> readExamples(String exampleId) {
		// TODO Auto-generated method stub
		return null;
	}

}
