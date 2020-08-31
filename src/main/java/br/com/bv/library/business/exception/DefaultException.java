package br.com.bv.library.business.exception;

public class DefaultException extends Exception {

	private static final long serialVersionUID = 1L;

	public DefaultException(String errorMessage) {
		super(errorMessage);
	}

	public DefaultException(String errorMessage, Exception e) {
		super(errorMessage, e);
	}

	public DefaultException(String errorMessage, String errorDetailed) {
		super(errorMessage + " " + errorDetailed);
	}

	public DefaultException(String errorMessage, String errorDetailed, Exception e) {
		super(errorMessage + " " + errorDetailed, e);
	}

}
