package Models;

public class Topic {
	private int _TopicId;
	private int _LanguageId;
	private String _TopicTitle;
	private String _IntroductionHtml;
	private String _SyntaxHtml;
	private String _ParametersHtml;
	private String _RemarksHtml;

	public int get_LanguageId() {
		return _LanguageId;
	}

	public void set_LanguageId(int _LanguageId) {
		this._LanguageId = _LanguageId;
	}

	public int get_TopicId() {
		return _TopicId;
	}

	public void set_TopicId(int _TopicId) {
		this._TopicId = _TopicId;
	}

	public String get_TopicTitle() {
		return _TopicTitle;
	}

	public void set_TopicTitle(String _TopicTitle) {
		this._TopicTitle = _TopicTitle;
	}

	public String get_IntroductionHtml() {
		return _IntroductionHtml;
	}

	public void set_IntroductionHtml(String _IntroductionHtml) {
		this._IntroductionHtml = _IntroductionHtml;
	}

	public String get_SyntaxHtml() {
		return _SyntaxHtml;
	}

	public void set_SyntaxHtml(String _SyntaxHtml) {
		this._SyntaxHtml = _SyntaxHtml;
	}

	public String get_ParametersHtml() {
		return _ParametersHtml;
	}

	public void set_ParametersHtml(String _ParametersHtml) {
		this._ParametersHtml = _ParametersHtml;
	}

	public String get_RemarksHtml() {
		return _RemarksHtml;
	}

	public void set_RemarksHtml(String _RemarksHtml) {
		this._RemarksHtml = _RemarksHtml;
	}

}
