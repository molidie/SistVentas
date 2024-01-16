package ar.edu.unnoba.ssaaii.SistVentas.state;

import ar.edu.unnoba.ssaaii.SistVentas.exeption.StockInsuficienteException;
import ar.edu.unnoba.ssaaii.SistVentas.model.Articulo;

public class StockSuficiente implements StockState {
    @Override
    public void verificarStock(Articulo articulo, int cantidad) {
        if (articulo.getStock() - cantidad <= articulo.getStock_min()) {
            throw new StockInsuficienteException("¡Advertencia! Stock menor o igual al mínimo.");
        }
    }
}
