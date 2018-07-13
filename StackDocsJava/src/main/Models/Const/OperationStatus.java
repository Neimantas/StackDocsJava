package Models.Const;

public enum OperationStatus {
	
	success("success");
	
	private String _message;
	
	private OperationStatus(String message) {
		_message = message;
	}

	public String getMessage() {
		return _message;
	}
} 
