package Models.DTO;

import java.util.List;

import Models.Business.Language;

public class LanguageTagFrontDTO {

	public boolean success;
	public String message;
	public List<Language> languageTag;

	public LanguageTagFrontDTO() {
	}

	public LanguageTagFrontDTO(boolean inputSuccess, String inputMessage, List<Language> inputLanguageTag) {
		success = inputSuccess;
		languageTag = inputLanguageTag;
		message = inputMessage;
	}

}
