package Models.DTO;

public class DeleteTableDTO {
	private boolean success;
	private String message;
	public DeleteTableDTO(){};
	public DeleteTableDTO(boolean successIn, String msgIn) {
		success = successIn;
		message = msgIn;
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
