package Services;

import java.util.List;

import Models.DTO.ExamplesDTO;
import Models.DTO.LanguageTagDTO;
import Models.DTO.TopicsDTO;

public interface IHigherService {
	
//	List<Object> readLanguageTag(String languageId);
//	List<Object> readTopics(String topicId);
//	List<Object> readExamples(String exampleId);
	LanguageTagDTO getAllLanguages();
	TopicsDTO getAllTopics();
	TopicsDTO getTopicsByLanguageId(int languageId);
	TopicsDTO getTopicInfoByTopicId(int topicId);
	ExamplesDTO getExampleByExampleId();
	ExamplesDTO getExamplesByTopicId(int topicId);
}
