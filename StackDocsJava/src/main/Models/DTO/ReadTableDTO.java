package Models.DTO;

import java.sql.ResultSet;
import java.util.List;

public class ReadTableDTO<T> {
	private boolean success;
	private List<T> readResultSet;
	private String message;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public List<T> getReadResultSet() {
		return readResultSet;
	}
	public void setReadResultSet(List<T> readResultSet) {
		this.readResultSet = readResultSet;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
