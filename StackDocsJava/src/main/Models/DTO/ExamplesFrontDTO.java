package Models.DTO;

import java.util.List;

import Models.Business.Example;

public class ExamplesFrontDTO {

	public boolean success;
	public String message;
	public List<Example> examples;

	ExamplesFrontDTO() {
	}

	public ExamplesFrontDTO(boolean inputSuccess, String inputMessage, List<Example> inputExamples) {
		success = inputSuccess;
		message = inputMessage;
		examples = inputExamples;
	}

}
