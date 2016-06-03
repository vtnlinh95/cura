package com.kms.cura.dal.exception;

public class DuplicatedUserEmailException extends DALException {
	private static final long serialVersionUID = -3371338460012790388L;

	public DuplicatedUserEmailException(String email) {
		super("Duplicated user email: " + email);
	}

}
