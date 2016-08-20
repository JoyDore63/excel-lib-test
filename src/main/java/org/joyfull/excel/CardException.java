package org.joyfull.excel;

/**
 * Basic exception to allow improved error handling
 */
public class CardException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public CardException(String msg) {
		super(msg);
	}
}
