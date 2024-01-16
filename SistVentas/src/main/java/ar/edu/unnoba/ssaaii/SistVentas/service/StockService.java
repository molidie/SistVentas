package ar.edu.unnoba.ssaaii.SistVentas.service;

import ar.edu.unnoba.ssaaii.SistVentas.model.Articulo;
import ar.edu.unnoba.ssaaii.SistVentas.state.StockState;
import org.springframework.stereotype.Service;

@Service
public class StockService {
    private StockState stockState;

    public void setStockState(StockState stockState) {
        this.stockState = stockState;
    }

    public void verificarStock(Articulo articulo, int cantidad) {
        stockState.verificarStock(articulo, cantidad);
    }
}

