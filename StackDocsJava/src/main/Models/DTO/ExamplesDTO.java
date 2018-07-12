package Models.DTO;

import java.util.List;

import Models.DAL.ExamplesDAL;

public class ExamplesDTO {

	private boolean _Success;
	private String _Message;
	private List<ExamplesDAL> _Examples;

	public ExamplesDTO() {
	}

	public ExamplesDTO(boolean success, List<ExamplesDAL> dal, String message) {
		_Success = success;
		_Examples = dal;
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

	public List<ExamplesDAL> getExamples() {
		return _Examples;
	}

	public void setExamples(List<ExamplesDAL> topics) {
		_Examples = topics;
	}

}
