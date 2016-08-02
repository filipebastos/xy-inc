package com.xyinc.service;

public class XyIncException extends RuntimeException {

	private static final long serialVersionUID = 821713672826680391L;

	public XyIncException() {
		super();
	}

	public XyIncException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public XyIncException(String message, Throwable cause) {
		super(message, cause);
	}

	public XyIncException(String message) {
		super(message);
	}

	public XyIncException(Throwable cause) {
		super(cause);
	}
}
