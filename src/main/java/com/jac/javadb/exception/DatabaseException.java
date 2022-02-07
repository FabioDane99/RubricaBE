package com.jac.javadb.exception;

public class DatabaseException extends RuntimeException {

	public DatabaseException(String message, Throwable th) {
		super(message, th);
	}
}
