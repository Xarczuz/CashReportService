package com.CashReportSystem.exception;

public class NoSuchUserException extends Throwable{
    /**
	 *
	 */
	private static final long serialVersionUID = -5011412654373729773L;

	public NoSuchUserException(String msg) {
        super(msg);
    }
}
