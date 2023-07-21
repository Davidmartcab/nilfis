package com.nilfis.nilfis.util.exceptions;

public class BadRole extends RuntimeException {
    public BadRole() {
        super("This rol cant be used");
    }
}
