package Services;

import Models.DTO.CreateTableDTO;
import Models.DTO.DeleteTableDTO;
import Models.DTO.ExamplesDTO;
import Models.DTO.LanguageTagDTO;
import Models.DTO.TopicsDTO;
import Models.DTO.UpdateTableDTO;

public interface IHigherService {
	LanguageTagDTO getLanguageTagByLanguageId(int languageId);
	LanguageTagDTO getAllLanguages();
	TopicsDTO getAllTopics();
	TopicsDTO getTopicsByLanguageId(int languageId);
	TopicsDTO getTopicInfoByTopicId(int topicId);
	ExamplesDTO getExampleByExampleId(int exampleId);
	ExamplesDTO getExamplesByTopicId(int topicId);
	CreateTableDTO create(Object insertRecord);
	UpdateTableDTO update(Object updataRecord);
	DeleteTableDTO delete(Object deleteRecord);
}
