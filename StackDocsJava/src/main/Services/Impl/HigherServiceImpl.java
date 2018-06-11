package Services.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Models.DAL.ExamplesDAL;
import Models.DAL.LanguageTagsDAL;
import Models.DAL.TopicsDAL;
import Models.DTO.ExamplesDTO;
import Models.DTO.LanguageTagDTO;
import Models.DTO.ReadTableDTO;
import Models.DTO.TopicsDTO;
import Services.ICache;
import Services.ICrud;
import Services.IHigherService;

public class HigherServiceImpl implements IHigherService {
	
	private LanguageTagDTO readLanguageTag() {
		
		ICache cache = CacheImpl.getInstance();
		if (cache.get("LanguageTag") != null) {
			return (LanguageTagDTO)cache.get("LanguageTag");
		}
		
		ICrud crud = new CRUD();
		
		ReadTableDTO readTableDTO = crud.read("LanguageTags");
		if(readTableDTO.isSuccess()) {
			List<LanguageTagsDAL> languageList = new ArrayList<>();
			ResultSet resultSet = readTableDTO.getReadResultSet();
			try {
				
				while(resultSet.next()) {
					LanguageTagsDAL languageTag = new LanguageTagsDAL();
					languageTag.languageId = resultSet.getInt("LanguageId");
					languageTag.tag = resultSet.getInt("Tag");
					languageTag.title = resultSet.getString("Title");
					languageTag.helloWorldTopicId = resultSet.getByte("HelloWorldTopicid");
					
					languageList.add(languageTag);
				}
				LanguageTagDTO ret = new LanguageTagDTO(true, languageList, "success");
				cache.put("LanguageTag", ret);
				return ret;
				
				
			} catch (SQLException e) {
				
				return new LanguageTagDTO(false, null, e.getMessage());
				
			}
			finally {
				DataBaseImpl.getInstance().close();

			}
		}
			
		return new LanguageTagDTO(false, null, readTableDTO.getMessage());
		
	}
	private TopicsDTO readTopics() {
		
		ICache cache = CacheImpl.getInstance();
		if (cache.get("Topics") != null) {
			return (TopicsDTO)cache.get("Topics");
		}
		
		ICrud crud = new CRUD();
		
		ReadTableDTO readTableDTO = crud.read("Topics");
		if(readTableDTO.isSuccess()) {
			ResultSet resultSet = readTableDTO.getReadResultSet();
			List<TopicsDAL> topics = new ArrayList<>();
			
			try {
				while(resultSet.next()) {
					
					TopicsDAL topicsDal = new TopicsDAL();
					
					topicsDal.topicId = resultSet.getInt("TopicId");
					topicsDal.languageId = resultSet.getInt("LanguageId");
					topicsDal.title = resultSet.getString("Title");
					topicsDal.isHelloWorldTopic = resultSet.getByte("IsHelloWorldTopic");
					topicsDal.introductionHtml = resultSet.getString("IntroductionHtml");
					topicsDal.syntaxHtml = resultSet.getString("SyntaxHtml");
					topicsDal.parametersHtml = resultSet.getString("ParametersHtml");
					topicsDal.remarksHtml = resultSet.getString("RemarksHtml");
					
					topics.add(topicsDal);
					
				}
				TopicsDTO ret = new TopicsDTO(true, topics, "success");
				cache.put("Topics", ret);
				return ret;
				
			} catch (SQLException e) {
				
				return new TopicsDTO(false, null, e.getMessage());
				
			}
			finally {
				DataBaseImpl.getInstance().close();

			}
		}
		
	
		
		return new TopicsDTO(false, null, readTableDTO.getMessage());
	}
	private ExamplesDTO readExamples() {
	
		
		ICrud crud = new CRUD();
		
		ReadTableDTO readTableDTO = crud.read("Examples");
		
		if(readTableDTO.isSuccess()) {
			ResultSet resultSet = readTableDTO.getReadResultSet();
			List<ExamplesDAL> examples = new ArrayList<>();
			
			try {
				while(resultSet.next()) {
					
					ExamplesDAL example = new ExamplesDAL();
					example.exampleId = resultSet.getInt("ExampleId");
					example.topicId = resultSet.getInt("TopicId");
					example.title = resultSet.getString("Title");
					example.bodyHtml = resultSet.getString("BodyHtml");
					
					examples.add(example);
				}
				
				return new ExamplesDTO(true, examples, "success");
			} catch (SQLException e) {
			
				return new ExamplesDTO(false, null, e.getMessage());
			}
			finally {
				DataBaseImpl.getInstance().close();

			}
		}
		
		return new ExamplesDTO(false, null, readTableDTO.getMessage());
	}
	public TopicsDTO getTopicsByLanguageId(int languageId) {

		TopicsDTO topicsDTO = readTopics();
		
		//check if readTopics method ended with errors
		if(topicsDTO.isSuccess()) {
			List<TopicsDAL> ret = topicsDTO.getTopics().stream()
					.filter(e -> e.languageId == languageId).collect(Collectors.toList());
			//add to cache before return
			return new TopicsDTO(true, ret, "success");
		}
		
//		List<Object> ret = topics.stream().filter(e -> ((TopicsDAL)e).languageId == languageId).collect(Collectors.toList());
		
		return new TopicsDTO(false, null, topicsDTO.getMessage());
	}
	
	@Override
	public ExamplesDTO getExamplesByTopicId(int topicId) {
		
		ExamplesDTO examplesDTO = readExamples();
		//check if readTopics method ended with errors
		if(examplesDTO.isSuccess()) {
			List<ExamplesDAL> ret = examplesDTO.getExamples().stream()
					.filter(e -> e.topicId == topicId).collect(Collectors.toList());
			return new ExamplesDTO(true, ret, "success");
		}
			
		
//		List<Object> ret = examples.stream().filter(e -> ((ExamplesDAL)e).topicId == topicId).collect(Collectors.toList());
		return new ExamplesDTO(false, null, examplesDTO.getMessage());
	}
	@Override
	public LanguageTagDTO getAllLanguages() {

		return readLanguageTag();
	}
	@Override
	public TopicsDTO getAllTopics() {

		return readTopics();
	}
	
	@Override
	public TopicsDTO getTopicInfoByTopicId(int topicId) {
		
		TopicsDTO topicsDTO = readTopics();
		
		if(topicsDTO.isSuccess()) {
			
			List<TopicsDAL> ret = topicsDTO.getTopics().stream()
					.filter(e -> e.topicId == topicId).collect(Collectors.toList());
			
			return new TopicsDTO(true, ret, "success");
		}
		
//		List<Object> ret = topics.stream().filter(e -> ((TopicsDAL)e).languageId == languageId).collect(Collectors.toList());
		
		return new TopicsDTO(false, null, topicsDTO.getMessage());
		
		
	}
	@Override
	public ExamplesDTO getExampleByExampleId(int exampleId) {
		
		ExamplesDTO examplesDTO = readExamples();
		if(examplesDTO.isSuccess()) {
			List<ExamplesDAL> ret = examplesDTO.getExamples().stream()
					.filter(e -> e.exampleId == exampleId).collect(Collectors.toList());
			return new ExamplesDTO(true, ret, "success");
		}
			
		
		return new ExamplesDTO(false, null, examplesDTO.getMessage());
		
	}
	@Override
	public LanguageTagDTO getLanguageTagByLanguageId(int languageId) {
		
		LanguageTagDTO languageTagDTO = readLanguageTag();
		
		if(languageTagDTO.isSuccess()) {
			
			List<LanguageTagsDAL> ret = languageTagDTO.getLanguageTag().stream()
					.filter(e -> e.languageId == languageId).collect(Collectors.toList());
			
			return new LanguageTagDTO(true, ret, "success");
		}
		
//		List<Object> ret = topics.stream().filter(e -> ((TopicsDAL)e).languageId == languageId).collect(Collectors.toList());
		
		return new LanguageTagDTO(false, null, languageTagDTO.getMessage());
		
	}
	

}
