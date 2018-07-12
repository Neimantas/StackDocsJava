package Models.DTO;

import java.util.List;
import Models.DAL.TopicsDAL;

public class TopicsDTO {

	private boolean _Success;
	private String _Message;
	private List<TopicsDAL> _Topics;

	public TopicsDTO() {
	}

	public TopicsDTO(boolean success, List<TopicsDAL> dal, String message) {
		_Success = success;
		_Topics = dal;
		_Message = message;
	}

	public boolean isSuccess() {
		return _Success;
	}

	public void setSuccess(boolean success) {
		_Success = success;
	}

	public String getMessage() {
		return _Message;
	}

	public void setMessage(String message) {
		_Message = message;
	}

	public List<TopicsDAL> getTopics() {
		return _Topics;
	}

	public void setTopics(List<TopicsDAL> topics) {
		_Topics = topics;
	}

}
