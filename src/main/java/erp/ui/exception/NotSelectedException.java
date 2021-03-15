package erp.ui.exception;

@SuppressWarnings("serial")
public class NotSelectedException extends RuntimeException {

	public NotSelectedException() {
		super("목록을 선택하세요.");
	}

	public NotSelectedException(Throwable cause) {
		super("목록을 선택하세요.", cause);
	}

}
