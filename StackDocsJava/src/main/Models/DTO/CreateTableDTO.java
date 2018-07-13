package Models.DTO;

public class CreateTableDTO {

	public boolean success;
	public String message;
	
	public CreateTableDTO(boolean inputSuccess, String inputMessage) {
		success = inputSuccess;
		message = inputMessage;
	}

}
