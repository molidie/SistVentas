package ar.edu.unnoba.ssaaii.SistVentas.state;

import ar.edu.unnoba.ssaaii.SistVentas.exeption.StockInsuficienteException;
import ar.edu.unnoba.ssaaii.SistVentas.model.Articulo;

public interface StockState {
    void verificarStock(Articulo articulo, int cantidad) throws StockInsuficienteException;

}
