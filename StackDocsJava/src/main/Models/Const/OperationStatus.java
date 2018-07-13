package Models.Const;

public enum OperationStatus {
	
	SUCCESS("success");
	
	private String _message;
	
	private OperationStatus(String message) {
		_message = message;
	}

	public String getMessage() {
		return _message;
	}
} 
