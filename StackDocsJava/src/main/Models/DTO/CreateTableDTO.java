package Models.DTO;

public class CreateTableDTO {
	private boolean success;
	private String message;

	public CreateTableDTO(boolean successIn, String msgIn) {
		success = successIn;
		message = msgIn;
	}

	public CreateTableDTO() {
	}

	public boolean getIsSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
