package com.gbis.Bank.exceptions;

public class InsufficientFunds extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InsufficientFunds(String message) {
		super(message);
	}
}
