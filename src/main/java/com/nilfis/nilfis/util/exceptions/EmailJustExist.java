package com.nilfis.nilfis.util.exceptions;

public class EmailJustExist extends RuntimeException {

    public EmailJustExist() {
        super("This email just exist");
    }
}
