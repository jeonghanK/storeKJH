package exception;

public class CartEmptyException extends RuntimeException {
	// java.lang.RuntimeException : 
	// Ŭ������ ����� ���ܵ��� ������� ����ó���� �������� �ʱ� ������ ��üũ ���ܶ�� �Ҹ���. �Ǵ� ��ǥ Ŭ���� �̸��� ���� ��Ÿ�� ����(RuntimeException)��� �Ѵ�.
    // ������ ���������� �� ��Ÿ�� ����(RuntimeException)�� catch ������ ��ų� throws�� �������� �ʾƵ� �ȴ�.

	private String url;

	public CartEmptyException(String msg, String url) {
		super(msg);
		this.url = url;
	}

	public String getUrl() {
		return url;
	}
}
