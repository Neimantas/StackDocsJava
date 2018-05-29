package Models;

public class Language {

	private String _language;
	private int _id;

	public Language(String language, int id) {
		_language = language;
		_id = id;
	}

	public String getLanguage() {
		return _language;
	}

	public int getId() {
		return _id;
	}

}
