package Models.Const;

public enum Errors {

	createError("Create"),
	updateError("Update"),
	deleteError("Delete");

	private String errorMessage;

	private Errors(String name) {
		errorMessage = "ERROR: wrong parameter given! " + name
				+ " method accepts only instances of Topic, Example and Language classes";
	}

	public String getMessage() {
		return errorMessage;
	}
}
