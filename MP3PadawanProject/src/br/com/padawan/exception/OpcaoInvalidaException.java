package br.com.padawan.exception;

public class OpcaoInvalidaException extends RuntimeException {

    public OpcaoInvalidaException(String message, Throwable cause) {
        super(message, cause);
    }
}
