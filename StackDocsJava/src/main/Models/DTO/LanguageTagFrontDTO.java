package Models.DTO;

import java.util.List;

import Models.Business.Language;

public class LanguageTagFrontDTO {

	public boolean _Success;
	public String _Message;
	public List<Language> _LanguageTag;

	public LanguageTagFrontDTO() {
	}

	public LanguageTagFrontDTO(boolean success, String message, List<Language> tag) {
		_Success = success;
		_LanguageTag = tag;
		_Message = message;
	}

	public boolean isSuccess() {
		return _Success;
	}

	public void setSuccess(boolean success) {
		_Success = success;
	}

	public String getMessage() {
		return _Message;
	}

	public void setMessage(String message) {
		_Message = message;
	}

	public List<Language> getLanguageTag() {
		return _LanguageTag;
	}

	public void setLanguageTag(List<Language> languageTag) {
		_LanguageTag = languageTag;
	}

}
