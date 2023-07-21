package com.nilfis.nilfis.util.exceptions;

public class EmailNotExist extends RuntimeException {

    public EmailNotExist() {
        super("This email not exist");
    }
}
