package com.disney.ad.adexchange.exception;

public class ApiException extends RuntimeException {

	private static final long serialVersionUID = -5695626466818673431L;

    public ApiException(final String message) {
        super(message);
    }
}
