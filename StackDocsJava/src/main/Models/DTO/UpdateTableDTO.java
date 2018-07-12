package Models.DTO;

public class UpdateTableDTO {
	private boolean success;
	private String message;

	UpdateTableDTO() {
	};

	UpdateTableDTO(boolean successIn, String messageIn) {
		success = successIn;
		message = messageIn;
	};

	public boolean isSuccess() {
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
