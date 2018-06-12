package Models.DTO;

import java.util.List;

public class ReadTableDTO {
	private boolean success;
	private List<Object> readResultSet;
	private String message;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public List<Object> getReadResultSet() {
		return readResultSet;
	}
	public void setReadResultSet(List<Object> readResultSet) {
		this.readResultSet = readResultSet;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
