package br.com.bv.business.exception;

public class ArquivoBusinessException extends DefaultException {
	
	private static final long serialVersionUID = 1L;

	private static String errorMessage = "Erro em Arquivo.";
	
	public ArquivoBusinessException() {
		super(errorMessage);
	}
	
	public ArquivoBusinessException(Exception e) {
		super(errorMessage, e);
	}

	public ArquivoBusinessException(String errorDetailed) {
		super(errorMessage, errorDetailed);
	}

	public ArquivoBusinessException(String errorDetailed, Exception e) {
		super(errorMessage, errorDetailed, e);
	}

}
