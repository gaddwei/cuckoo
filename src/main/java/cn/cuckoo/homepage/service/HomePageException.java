package cn.cuckoo.homepage.service;

public class HomePageException extends RuntimeException {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -8776481907731132659L;

	public HomePageException() {
		super();
	}

	public HomePageException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public HomePageException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public HomePageException(String arg0) {
		super(arg0);
	}

	public HomePageException(Throwable arg0) {
		super(arg0);
	}
	
}
