package br.com.springautomotors.exception;

public class PlacaUnicaViolationException extends RuntimeException {
    public PlacaUnicaViolationException() {
        super("Placa já exite cadastrada");
    }

    public PlacaUnicaViolationException(String message) {
        super(message);
    }
}
