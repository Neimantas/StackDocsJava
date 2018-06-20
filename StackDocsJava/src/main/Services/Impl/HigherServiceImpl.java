package Services.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Models.Example;
import Models.Language;
import Models.Topic;
import Models.DAL.ExamplesDAL;
import Models.DAL.LanguageTagsDAL;
import Models.DAL.TopicsDAL;
import Models.DTO.CreateTableDTO;
import Models.DTO.DeleteTableDTO;
import Models.DTO.ExamplesDTO;
import Models.DTO.LanguageTagDTO;
import Models.DTO.ReadTableDTO;
import Models.DTO.TopicsDTO;
import Models.DTO.UpdateTableDTO;
import Services.ICache;
import Services.ICrud;
import Services.IHigherService;

public class HigherServiceImpl implements IHigherService {
	
	ICrud crud;
	
	public HigherServiceImpl(CRUD crudImpl) {
		crud = crudImpl;
	}
	

	
	
		
	
//	public HigherServiceImpl() {
//		crud = new CRUD();
//	}
	
//	private LanguageTagDTO readLanguageTag() {
//		
//		ICache cache = CacheImpl.getInstance();
//		if (cache.get("LanguageTag") != null) {
//			return (LanguageTagDTO)cache.get("LanguageTag");
//		}
//		
//		ICrud crud = new CRUD();
//		
//		ReadTableDTO readTableDTO = crud.read(new LanguageTagsDAL());
//		if(readTableDTO.isSuccess()) {
//			List<LanguageTagsDAL> languageList = new ArrayList<>();
//			ResultSet resultSet = readTableDTO.getReadResultSet();
//			try {
//				
//				while(resultSet.next()) {
//					LanguageTagsDAL languageTag = new LanguageTagsDAL();
//					languageTag.languageId = resultSet.getInt("LanguageId");
//					languageTag.tag = resultSet.getInt("Tag");
//					languageTag.title = resultSet.getString("Title");
//					languageTag.helloWorldTopicId = resultSet.getByte("HelloWorldTopicid");
//					
//					languageList.add(languageTag);
//				}
//				LanguageTagDTO ret = new LanguageTagDTO(true, languageList, "success");
//				cache.put("LanguageTag", ret);
//				return ret;
//				
//				
//			} catch (SQLException e) {
//				
//				return new LanguageTagDTO(false, null, e.getMessage());
//				
//			}
//			finally {
//				DataBaseImpl.getInstance().close();
//
//			}
//		}
//			
//		return new LanguageTagDTO(false, null, readTableDTO.getMessage());
//		
//	}
//	private TopicsDTO readTopics() {
//		
//		ICache cache = CacheImpl.getInstance();
//		if (cache.get("Topics") != null) {
//			return (TopicsDTO)cache.get("Topics");
//		}
//		
//		ICrud crud = new CRUD();
//		
//		ReadTableDTO readTableDTO = crud.read(new TopicsDAL());
//		if(readTableDTO.isSuccess()) {
//			ResultSet resultSet = readTableDTO.getReadResultSet();
//			List<TopicsDAL> topics = new ArrayList<>();
//			
//			try {
//				while(resultSet.next()) {
//					
//					TopicsDAL topicsDal = new TopicsDAL();
//					
//					topicsDal.topicId = resultSet.getInt("TopicId");
//					topicsDal.languageId = resultSet.getInt("LanguageId");
//					topicsDal.title = resultSet.getString("Title");
//					topicsDal.isHelloWorldTopic = resultSet.getByte("IsHelloWorldTopic");
//					topicsDal.introductionHtml = resultSet.getString("IntroductionHtml");
//					topicsDal.syntaxHtml = resultSet.getString("SyntaxHtml");
//					topicsDal.parametersHtml = resultSet.getString("ParametersHtml");
//					topicsDal.remarksHtml = resultSet.getString("RemarksHtml");
//					
//					topics.add(topicsDal);
//					
//				}
//				TopicsDTO ret = new TopicsDTO(true, topics, "success");
//				cache.put("Topics", ret);
//				return ret;
//				
//			} catch (SQLException e) {
//				
//				return new TopicsDTO(false, null, e.getMessage());
//				
//			}
//			finally {
//				DataBaseImpl.getInstance().close();
//
//			}
//		}
//		
//	
//		
//		return new TopicsDTO(false, null, readTableDTO.getMessage());
//	}
//	private ExamplesDTO readExamples() {
//	
//		
////		ICrud crud = new CRUD();
//		
//		ReadTableDTO readTableDTO = crud.read(new ExamplesDAL());
//		
//		if(readTableDTO.isSuccess()) {
//			ResultSet resultSet = readTableDTO.getReadResultSet();
//			List<ExamplesDAL> examples = new ArrayList<>();
//			
//			try {
//				while(resultSet.next()) {
//					
//					ExamplesDAL example = new ExamplesDAL();
//					example.exampleId = resultSet.getInt("ExampleId");
//					example.topicId = resultSet.getInt("TopicId");
//					example.title = resultSet.getString("Title");
//					example.bodyHtml = resultSet.getString("BodyHtml");
//					
//					examples.add(example);
//				}
//				
//				return new ExamplesDTO(true, examples, "success");
//			} catch (SQLException e) {
//			
//				return new ExamplesDTO(false, null, e.getMessage());
//			}
//			finally {
//				DataBaseImpl.getInstance().close();
//
//			}
//		}
//		
//		return new ExamplesDTO(false, null, readTableDTO.getMessage());
//	}
	public TopicsDTO getTopicsByLanguageId(int languageId) {

//		TopicsDTO topicsDTO = readTopics();
//		ICrud crud = new CRUD();
		ReadTableDTO dto = crud.read(new TopicsDAL());
		//check if readTopics method ended with errors
		if(dto.isSuccess()) {
			List<TopicsDAL> list = (List<TopicsDAL>)(Object)dto.getReadResultSet();
			List<TopicsDAL> ret = list.stream()
					.filter(e -> e.languageId == languageId).collect(Collectors.toList());
			//add to cache before return
			return new TopicsDTO(true, ret, "success");
		}
		
//		List<Object> ret = topics.stream().filter(e -> ((TopicsDAL)e).languageId == languageId).collect(Collectors.toList());
		
		return new TopicsDTO(false, null, dto.getMessage());
	}
	
	@Override
	public ExamplesDTO getExamplesByTopicId(int topicId) {
		
//		ExamplesDTO examplesDTO = readExamples();
//		ICrud crud = new CRUD();
		ReadTableDTO examplesDTO = crud.read(new ExamplesDAL());
		//check if readTopics method ended with errors
		if(examplesDTO.isSuccess()) {
			List<ExamplesDAL> list = (List<ExamplesDAL>)(Object)examplesDTO.getReadResultSet();
			List<ExamplesDAL> ret = list.stream()
					.filter(e -> e.topicId == topicId).collect(Collectors.toList());
			return new ExamplesDTO(true, ret, "success");
		}
			
		
//		List<Object> ret = examples.stream().filter(e -> ((ExamplesDAL)e).topicId == topicId).collect(Collectors.toList());
		return new ExamplesDTO(false, null, examplesDTO.getMessage());
	}
	@Override
	public LanguageTagDTO getAllLanguages() {
//		ICrud crud = new CRUD();
		ReadTableDTO languageTagsDTO = crud.read(new LanguageTagsDAL());
		if(languageTagsDTO.isSuccess()) {
			List<LanguageTagsDAL> list = (List<LanguageTagsDAL>)(Object) languageTagsDTO.getReadResultSet();
			LanguageTagDTO ret = new LanguageTagDTO();
			ret.setSuccess(true);
			ret.setLanguageTag(list);
			ret.setMessage("success");
			return ret;
		}
		LanguageTagDTO ret = new LanguageTagDTO();
		ret.setSuccess(false);
		ret.setMessage(languageTagsDTO.getMessage());
		return ret;
	}
	@Override
	public TopicsDTO getAllTopics() {
//		ICrud crud = new CRUD();
		ReadTableDTO topicsDTO = crud.read(new TopicsDAL());
		if(topicsDTO.isSuccess()) {
			List<TopicsDAL> list = (List<TopicsDAL>)(Object) topicsDTO.getReadResultSet();
			TopicsDTO ret = new TopicsDTO();
			ret.setSuccess(true);
			ret.setMessage("success");
			ret.setTopics(list);
			return ret;
		}
		TopicsDTO ret = new TopicsDTO();
		ret.setSuccess(false);
		ret.setMessage(topicsDTO.getMessage());
		return ret;
	}
	
	@Override
	public TopicsDTO getTopicInfoByTopicId(int topicId) {
		
//		TopicsDTO topicsDTO = readTopics();
//		ICrud crud = new CRUD();
		ReadTableDTO topicsDTO = crud.read(new TopicsDAL());
		if(topicsDTO.isSuccess()) {
			
			List<TopicsDAL> list = (List<TopicsDAL>)(Object) topicsDTO.getReadResultSet();
			List<TopicsDAL> ret = list.stream()
					.filter(e -> e.topicId == topicId).collect(Collectors.toList());
			
			return new TopicsDTO(true, ret, "success");
		}
		
//		List<Object> ret = topics.stream().filter(e -> ((TopicsDAL)e).languageId == languageId).collect(Collectors.toList());
		
		return new TopicsDTO(false, null, topicsDTO.getMessage());
		
		
	}
	@Override
	public ExamplesDTO getExampleByExampleId(int exampleId) {
		
//		ExamplesDTO examplesDTO = readExamples();
//		ICrud crud = new CRUD();
		ReadTableDTO examplesDTO = crud.read(new ExamplesDAL());
		if(examplesDTO.isSuccess()) {
			List<ExamplesDAL> list = (List<ExamplesDAL>)(Object)examplesDTO.getReadResultSet();
			List<ExamplesDAL> ret = list.stream()
					.filter(e -> e.exampleId == exampleId).collect(Collectors.toList());
			return new ExamplesDTO(true, ret, "success");
		}
			
		
		return new ExamplesDTO(false, null, examplesDTO.getMessage());
		
	}
	@Override
	public LanguageTagDTO getLanguageTagByLanguageId(int languageId) {
		
//		LanguageTagDTO languageTagDTO = readLanguageTag();
//		ICrud crud = new CRUD();
		ReadTableDTO languageTagsDTO = crud.read(new LanguageTagsDAL());
		if(languageTagsDTO.isSuccess()) {
			List<LanguageTagsDAL> list = (List<LanguageTagsDAL>)(Object) languageTagsDTO.getReadResultSet();
			List<LanguageTagsDAL> ret = list.stream()
					.filter(e -> e.languageId == languageId).collect(Collectors.toList());
			
			return new LanguageTagDTO(true, ret, "success");
		}
		
//		List<Object> ret = topics.stream().filter(e -> ((TopicsDAL)e).languageId == languageId).collect(Collectors.toList());
		
		return new LanguageTagDTO(false, null, languageTagsDTO.getMessage());
		
	}






	@Override
	public CreateTableDTO create(Object insertRecord) {
		
		if(insertRecord instanceof Topic) {
			
			return createTopic((Topic)insertRecord);
			
		} else if(insertRecord instanceof Example) {
			
			return createExample((Example)insertRecord);
			
		} else if(insertRecord instanceof Language) {
			
			return createLanguage((Language)insertRecord);
			
		}
		
		CreateTableDTO ret = new CreateTableDTO();
		ret.setSuccess(false);
		ret.setMessage("ERROR: wrong parameter given! Create method accepts only instances of Topic, Example and Language classes");
		
		return ret;
	}

	private CreateTableDTO createTopic(Topic topic) {
		return null;
	}
	
	private CreateTableDTO createExample(Example example) {
		return null;
	}

	private CreateTableDTO createLanguage(Language language) {
		return null;
	}


	@Override
	public UpdateTableDTO update(Object updataRecord) {
		// TODO Auto-generated method stub
		return null;
	}






	@Override
	public DeleteTableDTO delete(Object deleteRecord) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
