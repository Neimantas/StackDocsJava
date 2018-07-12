package Services;

import java.util.List;

import Models.DTO.CreateTableDTO;
import Models.DTO.DeleteTableDTO;
import Models.DTO.ExamplesFrontDTO;
import Models.DTO.LanguageTagFrontDTO;
import Models.DTO.TopicsFrontDTO;
import Models.DTO.TopicsInfoFrontDTO;
import Models.DTO.UpdateTableDTO;

public interface IFrontService {
	TopicsFrontDTO getTopicsByLanguageId(int languageId, String topicWord);
	TopicsInfoFrontDTO getTopicInfoByTopicId(int topicId);
	ExamplesFrontDTO getExamplesByID(int exmpleId);
	LanguageTagFrontDTO languageTagByLanguageId(int languageId);
	CreateTableDTO createTopic(List<String> params);
	UpdateTableDTO updateTopic(List<String> params);
	DeleteTableDTO deleteLanguage(int languageId);
	DeleteTableDTO deleteTopic(int topicId);
	DeleteTableDTO deleteExample(int exampleId);
}
