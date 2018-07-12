package Models.DTO;

import java.util.List;

import Models.Business.Topic;

public class TopicsFrontDTO {

	public boolean success;
	public String message;
	public List<Topic> topics;

	TopicsFrontDTO() {
	}

	public TopicsFrontDTO(boolean inputSuccess, String inputMessage, List<Topic> inputTopics) {
		success = inputSuccess;
		message = inputMessage;
		topics = inputTopics;
	}

}
