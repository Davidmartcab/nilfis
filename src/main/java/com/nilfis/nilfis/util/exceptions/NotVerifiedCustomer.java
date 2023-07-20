package com.nilfis.nilfis.util.exceptions;

public class NotVerifiedCustomer extends RuntimeException {
    private static final String ERROR_MESSAGE = "This user is not verified";

    public NotVerifiedCustomer() {
        super(ERROR_MESSAGE);
    }
}
