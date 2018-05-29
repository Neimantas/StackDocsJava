package Services;

import java.util.List;

import Models.DTO.ExamplesDTO;
import Models.DTO.TopicsDTO;

public interface IHigherService {
	
//	List<Object> readLanguageTag(String languageId);
//	List<Object> readTopics(String topicId);
//	List<Object> readExamples(String exampleId);
	TopicsDTO getTopicsByLanguageId(int languageId);
	ExamplesDTO getExamplesByTopicId(int topicId);
	

}
