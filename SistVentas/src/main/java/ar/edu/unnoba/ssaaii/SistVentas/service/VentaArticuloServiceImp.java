package ar.edu.unnoba.ssaaii.SistVentas.service;

import ar.edu.unnoba.ssaaii.SistVentas.exeption.NotFoundException;
import ar.edu.unnoba.ssaaii.SistVentas.model.VentaArticulo;
import ar.edu.unnoba.ssaaii.SistVentas.repository.VentaArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class VentaArticuloServiceImp implements IVentaArticuloService, UserDetailsService {

    private final VentaArticuloRepository ventaArticuloRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public VentaArticuloServiceImp(VentaArticuloRepository ventaArticuloRepository) {
        this.ventaArticuloRepository = ventaArticuloRepository;
    }

    @Override
    public VentaArticulo create(VentaArticulo ventaArticulo) {
        return ventaArticuloRepository.save(ventaArticulo);
    }

    @Override
    public List<VentaArticulo> getAll() {
        return ventaArticuloRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        ventaArticuloRepository.deleteById(id);

    }

    @Override
    public VentaArticulo busquedaPorId(Long id) {
        try {
            return ventaArticuloRepository.findById(id).orElseThrow(() -> new NotFoundException("Vendedor no encontrado con ID: " + id));
        } catch (NotFoundException ex) {
            return null;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }


    @Override
    public List<Map<String, Object>> obtenerMontoYPorcentajePorMes() {
        String sql = "SELECT " +
                "    m1.mes AS mes_actual, " +
                "    m1.monto_total AS monto_actual, " +
                "    m2.mes AS mes_anterior, " +
                "    m2.monto_total AS monto_anterior, " +
                "    ((m1.monto_total - m2.monto_total) / m2.monto_total) * 100 AS porcentaje_cambio, " +
                "    (m1.monto_total - m2.monto_total) AS diferencia " +
                "FROM " +
                "    (SELECT " +
                "        EXTRACT(MONTH FROM v.fecha) AS mes, " +
                "        SUM(va.monto) AS monto_total, " +
                "        ROW_NUMBER() OVER (ORDER BY EXTRACT(MONTH FROM v.fecha)) AS numero_mes " +
                "    FROM " +
                "        ventas v " +
                "    JOIN " +
                "        ventas_articulos va ON v.id = va.venta_id " +
                "    GROUP BY " +
                "        EXTRACT(MONTH FROM v.fecha)) m1 " +
                "JOIN " +
                "    (SELECT " +
                "        EXTRACT(MONTH FROM v.fecha) AS mes, " +
                "        SUM(va.monto) AS monto_total, " +
                "        ROW_NUMBER() OVER (ORDER BY EXTRACT(MONTH FROM v.fecha)) AS numero_mes " +
                "    FROM " +
                "        ventas v " +
                "    JOIN " +
                "        ventas_articulos va ON v.id = va.venta_id " +
                "    GROUP BY " +
                "        EXTRACT(MONTH FROM v.fecha)) m2 ON m1.numero_mes = m2.numero_mes + 1";

        List<Map<String, Object>> resultados = jdbcTemplate.queryForList(sql);

        return resultados;
    }
    @Override
    public List<Map<String, Object>> obtenerMontoPorMes() {
        String sql = "SELECT " +
                "    EXTRACT(MONTH FROM v.fecha) AS mes, " +
                "    SUM(va.monto) AS monto_total " +
                "FROM " +
                "    ventas v " +
                "JOIN " +
                "    ventas_articulos va ON v.id = va.venta_id " +
                "GROUP BY " +
                "    EXTRACT(MONTH FROM v.fecha)";

        List<Map<String, Object>> resultados = jdbcTemplate.queryForList(sql);

        return resultados;
    }

    @Override
    public List<Map<String, Object>> obtenerPorcentajeCambioPorMes() {
        String sql = "SELECT " +
                "    m1.mes AS mes_actual, " +
                "    ((m1.monto_total - LAG(m1.monto_total) OVER (ORDER BY m1.mes)) / LAG(m1.monto_total) OVER (ORDER BY m1.mes)) * 100 AS porcentaje_cambio " +
                "FROM " +
                "    (SELECT " +
                "        EXTRACT(MONTH FROM v.fecha) AS mes, " +
                "        SUM(va.monto) AS monto_total " +
                "    FROM " +
                "        ventas v " +
                "    JOIN " +
                "        ventas_articulos va ON v.id = va.venta_id " +
                "    GROUP BY " +
                "        EXTRACT(MONTH FROM v.fecha)) m1 " +
                "ORDER BY " +
                "    m1.mes";

        return jdbcTemplate.queryForList(sql);
    }

}
