package Models.DTO;

import java.sql.ResultSet;

public class ReadTableDTO {
	private boolean success;
	private ResultSet readResultSet;
	private String message;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public ResultSet getReadResultSet() {
		return readResultSet;
	}
	public void setReadResultSet(ResultSet readResultSet) {
		this.readResultSet = readResultSet;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
