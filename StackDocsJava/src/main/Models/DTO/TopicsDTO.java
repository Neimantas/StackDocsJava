package Models.DTO;

import java.util.List;

import Models.DAL.TopicsDAL;

public class TopicsDTO {

	public boolean success;
	public String message;
	public List<TopicsDAL> topics;

	public TopicsDTO() {
	}

	public TopicsDTO(boolean inputSuccess, List<TopicsDAL> inputDAL, String inputMessage) {
		success = inputSuccess;
		topics = inputDAL;
		message = inputMessage;
	}

}
