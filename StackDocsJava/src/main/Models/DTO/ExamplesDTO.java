package Models.DTO;

import java.util.List;

import Models.DAL.ExamplesDAL;

public class ExamplesDTO {

	public boolean success;
	public String message;
	public List<ExamplesDAL> examples;

	public ExamplesDTO() {
	}

	public ExamplesDTO(boolean inputSuccess, List<ExamplesDAL> inputDAL, String inputMessage) {
		success = inputSuccess;
		examples = inputDAL;
		message = inputMessage;
	}

}
