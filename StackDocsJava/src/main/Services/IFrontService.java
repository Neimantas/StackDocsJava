package Services;

import Models.DTO.TopicsFrontDTO;

public interface IFrontService {
	
	TopicsFrontDTO getTopicsByLanguageId(int languageId);
	

	
}
