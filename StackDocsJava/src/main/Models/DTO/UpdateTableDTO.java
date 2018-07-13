package Models.DTO;

public class UpdateTableDTO {

	public boolean success;
	public String message;
	
	public UpdateTableDTO(boolean inputSuccess, String inputMessage) {
		success = inputSuccess;
		message = inputMessage;
	}
	
}
