package Models.DTO;

import java.util.List;

import Models.Business.Topic;

public class TopicsInfoFrontDTO {
	private boolean _Succcess;
	private String _Message;
	private List<Topic> _TopicsInfo;

	TopicsInfoFrontDTO() {

	}

	public TopicsInfoFrontDTO(boolean sucs, String msg, List<Topic> topicInfo) {
		_Succcess = sucs;
		_Message = msg;
		_TopicsInfo = topicInfo;
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
		return _TopicsInfo;
	}

	public void set_Topics(List<Topic> topicInfo) {
		this._TopicsInfo = topicInfo;
	}

}
