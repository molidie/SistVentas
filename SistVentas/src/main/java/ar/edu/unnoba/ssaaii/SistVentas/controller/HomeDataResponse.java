package ar.edu.unnoba.ssaaii.SistVentas.controller;

import ar.edu.unnoba.ssaaii.SistVentas.model.Articulo;
import ar.edu.unnoba.ssaaii.SistVentas.model.Venta;

import java.util.List;

public class HomeDataResponse {
    private List<Articulo> articulosMasVendidos;
    private List<Venta> ventasTotalesPorMes;

    public HomeDataResponse() {
        this.articulosMasVendidos = articulosMasVendidos;
        this.ventasTotalesPorMes = ventasTotalesPorMes;
    }

    public List<Articulo> getArticulosMasVendidos() {
        return articulosMasVendidos;
    }

    public void setArticulosMasVendidos(List<Articulo> articulosMasVendidos) {
        this.articulosMasVendidos = articulosMasVendidos;
    }

    public List<Venta> getVentasTotalesPorMes() {
        return ventasTotalesPorMes;
    }

    public void setVentasTotalesPorMes(List<Venta> ventasTotalesPorMes) {
        this.ventasTotalesPorMes = ventasTotalesPorMes;
    }
}
