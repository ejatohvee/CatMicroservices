package com.maksim.Lab3.externalInterface.Exceptions;

public abstract class ControllerException extends RuntimeException {

    protected ControllerException(String message) {
        super(message);
    }
}