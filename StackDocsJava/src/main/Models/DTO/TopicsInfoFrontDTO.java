package Models.DTO;

import java.util.List;

import Models.Business.Topic;

public class TopicsInfoFrontDTO {

	public boolean succcess;
	public String message;
	public List<Topic> topicsInfo;

	TopicsInfoFrontDTO() {

	}

	public TopicsInfoFrontDTO(boolean inputSuccess, String inputMessage, List<Topic> inputTopicInfo) {
		succcess = inputSuccess;
		message = inputMessage;
		topicsInfo = inputTopicInfo;
	}

}
