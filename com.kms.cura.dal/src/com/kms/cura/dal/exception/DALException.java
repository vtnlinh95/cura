package com.kms.cura.dal.exception;

public abstract class DALException extends Exception {
	private static final long serialVersionUID = 1L;

	public DALException(String message) {
		super(message);
	}
}
