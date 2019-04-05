package br.com.padawan.exception;

public class OpcaoInvalidaException extends RuntimeException {

    public OpcaoInvalidaException() {
        super("Opção inválida!");
    }

    public OpcaoInvalidaException(String message) {
        super(message);
    }

    public OpcaoInvalidaException(String message, Throwable cause) {
        super(message, cause);
    }
}
