package Models.DTO;

import java.util.List;

import Models.DAL.LanguageTagDAL;

public class LanguageTagDTO {
	
	public boolean _Success;
	public String _Message;
	public List<LanguageTagDAL> _LanguageTag;
	
	public LanguageTagDTO() { }
	
	public LanguageTagDTO(boolean success, List<LanguageTagDAL> dal, String message) {
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

	public List<LanguageTagDAL> getLanguageTag() {
		return _LanguageTag;
	}

	public void setLanguageTag(List<LanguageTagDAL> languageTag) {
		_LanguageTag = languageTag;
	}

	

}
