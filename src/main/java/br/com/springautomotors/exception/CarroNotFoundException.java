package br.com.springautomotors.exception;

public class CarroNotFoundException extends RuntimeException {
    public CarroNotFoundException(String message) {
        super(message);
    }
}
