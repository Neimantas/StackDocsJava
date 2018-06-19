package Models.DTO;

import java.util.List;

import Models.DAL.LanguageTagsDAL;

public class LanguageTagDTO {
	
	public boolean _Success;
	public String _Message;
	public List<LanguageTagsDAL> _LanguageTag;
	
	public LanguageTagDTO() { }
	
	public LanguageTagDTO(boolean success, List<LanguageTagsDAL> dal, String message) {
		_Success = success;
		_LanguageTag = dal;
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

	public List<LanguageTagsDAL> getLanguageTag() {
		return _LanguageTag;
	}

	public void setLanguageTag(List<LanguageTagsDAL> languageTag) {
		_LanguageTag = languageTag;
	}

	

}
