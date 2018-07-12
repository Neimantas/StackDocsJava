package Models.DTO;

import java.util.List;
import Models.Business.Topic;

public class TopicsFrontDTO {
	private boolean _Succcess;
	private String _Message;
	private List<Topic> _Topics;

	TopicsFrontDTO() {
	}

	public TopicsFrontDTO(boolean sucs, String msg, List<Topic> topic) {
		_Succcess = sucs;
		_Message = msg;
		_Topics = topic;
	}

	public boolean is_Succcess() {
		return _Succcess;
	}

	public void set_Succcess(boolean sucs) {
		this._Succcess = sucs;
	}

	public String get_Message() {
		return _Message;
	}

	public void set_Message(String msg) {
		this._Message = msg;
	}

	public List<Topic> get_Topics() {
		return _Topics;
	}

	public void set_Topics(List<Topic> topic) {
		this._Topics = topic;
	}

}
