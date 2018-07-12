package Models.DTO;

import java.util.List;
import Models.Business.Example;

public class ExamplesFrontDTO {
	private boolean _Succcess;
	private String _Message;
	private List<Example> _Examples;

	public ExamplesFrontDTO (){}
	public ExamplesFrontDTO(boolean sucs, String msg, List<Example> examples) {
		_Succcess = sucs;
		_Message = msg;
		_Examples = examples;
	}
	public boolean is_Succcess() {
		return _Succcess;
	}

	public void set_Succcess(boolean _Succcess) {
		this._Succcess = _Succcess;
	}

	public String get_Message() {
		return _Message;
	}

	public void set_Message(String _Message) {
		this._Message = _Message;
	}

	public List<Example> get_Examples() {
		return _Examples;
	}

	public void set_Examples(List<Example> _Examples) {
		this._Examples = _Examples;
	}

}
