package cn.cuckoo.account.service;

public class LoginErrorException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5300089844032640714L;

	public LoginErrorException() {
	}

	public LoginErrorException(String arg0) {
		super(arg0);
	}

	public LoginErrorException(Throwable arg0) {
		super(arg0);
	}

	public LoginErrorException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public LoginErrorException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
