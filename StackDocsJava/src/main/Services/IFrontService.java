package Services;

import Models.LanguageTag;
import Models.DTO.ExamplesFrontDTO;
import Models.DTO.LanguageTagFrontDTO;
import Models.DTO.TopicsFrontDTO;
import Models.DTO.TopicsInfoFrontDTO;

public interface IFrontService {
	
	TopicsFrontDTO getTopicsByLanguageId(int languageId, String topicWord);
	TopicsInfoFrontDTO getTopicInfoByTopicId(int topicId);
	ExamplesFrontDTO getExamplesByID(int exmpleId);
	LanguageTagFrontDTO languageTagByLanguageId(int languageId);
	String deleteTopic(int topicID);
	
}
