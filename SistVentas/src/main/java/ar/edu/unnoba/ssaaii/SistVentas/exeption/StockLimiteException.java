package ar.edu.unnoba.ssaaii.SistVentas.exeption;

public class StockLimiteException extends RuntimeException {
    public StockLimiteException(String message) {
        super(message);
    }
}
