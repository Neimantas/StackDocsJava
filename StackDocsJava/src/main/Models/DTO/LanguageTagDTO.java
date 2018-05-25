package Models.DTO;

import Models.DAL.LanguageTagDAL;

public class LanguageTagDTO {
	public boolean isSuccess() {
		return Success;
	}

	public void setSuccess(boolean success) {
		Success = success;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public LanguageTagDAL getLanguageTag() {
		return LanguageTag;
	}

	public void setLanguageTag(LanguageTagDAL languageTag) {
		LanguageTag = languageTag;
	}

	public boolean Success;
	public String Message;
	public LanguageTagDAL LanguageTag;

}
