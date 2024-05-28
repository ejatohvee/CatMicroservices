package com.maksim.Lab3.externalInterface.Exceptions;

public class SynchronException extends ControllerException {
    private SynchronException(String message) {
        super(message);
    }

    public static SynchronException kafkaThrowsException() {
        return new SynchronException("Something went wrong during sending request");
    }

    public static <T, R> SynchronException unexpectedTypeReceived(Class<T> awaitedType, Class<R> receivedType) {
        return new SynchronException("Await type: " + awaitedType + " but received " + receivedType);
    }
}