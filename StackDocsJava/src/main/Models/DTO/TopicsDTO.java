package Models.DTO;

import Models.DAL.TopicsDAL;

public class TopicsDTO {

	public boolean isSuccess() {
		return Success;
	}

	public void setSuccess(boolean success) {
		Success = success;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public TopicsDAL getTopics() {
		return Topics;
	}

	public void setTopics(TopicsDAL topics) {
		Topics = topics;
	}

	public boolean Success;
	public String Message;
	public TopicsDAL Topics;

}
