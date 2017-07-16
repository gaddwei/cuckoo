package cn.cuckoo.core.service;

public class GeneralException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2856013959469415753L;

	public GeneralException() {
		super();
	}

	public GeneralException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public GeneralException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public GeneralException(String arg0) {
		super(arg0);
	}

	public GeneralException(Throwable arg0) {
		super(arg0);
	}

}
