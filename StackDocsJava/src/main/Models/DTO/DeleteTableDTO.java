package Models.DTO;

public class DeleteTableDTO {

	public boolean success;
	public String message;
	
	public DeleteTableDTO(boolean inputSuccess, String inputMessage) {
		success = inputSuccess;
		message = inputMessage;
	}

}
