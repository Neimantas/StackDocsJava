package Services;

import java.util.List;

public interface IHigherService {
	
	List<Object> readLanguageTag(String languageId);
	List<Object> readTopics(String topicId);
	List<Object> readExamples(String exampleId);
	List<Object> getTopicsByLanguageId(int languageId);
	List<Object> getExamplesByTopicId(int topicId);
	

}
