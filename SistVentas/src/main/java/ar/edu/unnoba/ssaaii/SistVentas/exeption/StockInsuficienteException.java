package ar.edu.unnoba.ssaaii.SistVentas.exeption;

public class StockInsuficienteException extends RuntimeException {
    public StockInsuficienteException(String message) {
        super(message);
    }
}
