package ar.edu.unnoba.ssaaii.SistVentas.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "ventas")
public class Venta implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column (name = "descripcion")
    private String descripcion;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    private Set<VentaArticulo> ventaArticulos;


    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    private Set<VentaFormaPago> ventaFormasPago;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "vendedor_id")
    private Vendedor vendedor;

    public Venta() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<VentaArticulo> getVentaArticulos() {
        return ventaArticulos;
    }

    public void setVentaArticulos(Set<VentaArticulo> ventaArticulos) {
        this.ventaArticulos = ventaArticulos;
    }

    public Set<VentaFormaPago> getVentaFormasPago() {
        return ventaFormasPago;
    }

    public void setVentaFormasPago(Set<VentaFormaPago> ventaFormasPago) {
        this.ventaFormasPago = ventaFormasPago;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
