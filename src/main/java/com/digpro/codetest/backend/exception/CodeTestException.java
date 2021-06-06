package com.digpro.codetest.backend.exception;

public class CodeTestException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CodeTestException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
	
	

}
