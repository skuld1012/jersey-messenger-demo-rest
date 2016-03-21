package me.tedzhang.demo.java.messenger.exception;

public class DataNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -546904624708675434L;

	public DataNotFoundException(String message) {
		super(message);
	}
}
