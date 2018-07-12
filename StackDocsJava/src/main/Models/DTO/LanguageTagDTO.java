package Models.DTO;

import java.util.List;

import Models.DAL.LanguageTagsDAL;

public class LanguageTagDTO {

	public boolean success;
	public String message;
	public List<LanguageTagsDAL> languageTag;

	public LanguageTagDTO() {
	}

	public LanguageTagDTO(boolean inputSuccess, List<LanguageTagsDAL> inputDAL, String inputMessage) {
		success = inputSuccess;
		languageTag = inputDAL;
		message = inputMessage;
	}

}
