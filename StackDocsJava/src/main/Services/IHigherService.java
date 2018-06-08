package Services;

import java.util.List;

import Models.DTO.ExamplesDTO;
import Models.DTO.LanguageTagDTO;
import Models.DTO.TopicsDTO;

public interface IHigherService {
	
//	List<Object> readLanguageTag(String languageId);
//	List<Object> readTopics(String topicId);
//	List<Object> readExamples(String exampleId);
	LanguageTagDTO getLanguageTagByLanguageId(int languageId);
	LanguageTagDTO getAllLanguages();
	TopicsDTO getAllTopics();
	TopicsDTO getTopicsByLanguageId(int languageId);
	TopicsDTO getTopicInfoByTopicId(int topicId);
	ExamplesDTO getExampleByExampleId(int exampleId);
	ExamplesDTO getExamplesByTopicId(int topicId);
}
