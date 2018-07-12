package Models.Const;

public enum Error {

	CREATE_ERROR("Create"),
	UPDATE_ERROR("Update"),
	DELETE_ERROR("Delete");

	private String _errorMessage;

	private Error(String name) {
		_errorMessage = "ERROR: wrong parameter given! " + name
				+ " method accepts only instances of Topic, Example and Language classes";
	}

	public String getMessage() {
		return _errorMessage;
	}
}
