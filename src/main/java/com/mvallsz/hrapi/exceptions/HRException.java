package com.mvallsz.hrapi.exceptions;

public class HRException extends Exception{

    public HRException() {
    }

    public HRException(String message) {
        super(message);
    }

    public HRException(String message, Throwable cause) {
        super(message, cause);
    }

    public HRException(Throwable cause) {
        super(cause);
    }
}
