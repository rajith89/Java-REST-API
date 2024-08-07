package com.testbranch.web.error;

public class ResourceAlreadyExistsException extends Exception {

    private static final long serialVersionUID = 5861310537366287163L;

    public ResourceAlreadyExistsException() {
        super();
    }

    public ResourceAlreadyExistsException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ResourceAlreadyExistsException(final String message) {
        super(message);
    }

    public ResourceAlreadyExistsException(final Throwable cause) {
        super(cause);
    }

}
