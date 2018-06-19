package Services;

import Models.DTO.ExamplesFrontDTO;
import Models.DTO.TopicsFrontDTO;
import Models.DTO.TopicsInfoFrontDTO;

public interface IFrontService {
	
	TopicsFrontDTO getTopicsByLanguageId(int languageId, String topicWord);
	TopicsInfoFrontDTO getTopicInfoByTopicId(int topicId);
	ExamplesFrontDTO getExamplesByID(int exmpleId);
	
}
