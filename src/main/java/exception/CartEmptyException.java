package exception;

public class CartEmptyException extends RuntimeException {
	// java.lang.RuntimeException : 
	// 클래스를 상속한 예외들은 명시적인 예외처리를 강제하지 않기 때문에 언체크 예외라고 불린다. 또는 대표 클래스 이름을 따서 런타임 예외(RuntimeException)라고도 한다.
    // 에러와 마찬가지로 이 런타임 예외(RuntimeException)는 catch 문으로 잡거나 throws로 선언하지 않아도 된다.

	private String url;

	public CartEmptyException(String msg, String url) {
		super(msg);
		this.url = url;
	}

	public String getUrl() {
		return url;
	}
}
