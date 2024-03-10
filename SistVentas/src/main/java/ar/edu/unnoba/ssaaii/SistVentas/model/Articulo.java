package ar.edu.unnoba.ssaaii.SistVentas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "Articulos")
public class Articulo implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "precio")
    @JsonIgnore
    private float precio;

    @Column(name = "stock")
    private int stock;

    @Column(name = "stock_min")
    @JsonIgnore
    private int stock_min;
    
    @Column(name = "fecha_ingreso")
    private Date fechaIngreso;

    @ManyToOne
    @JoinColumn(name = "proveedor_id")
    @JsonIgnore
    private Proveedor proveedor;

    @OneToMany(mappedBy = "articulo", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Proveedor> proveedores;

    @OneToMany(mappedBy = "articulo", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<VentaArticulo> ventaArticulos;

    public Articulo() {
    }

    public Set<Proveedor> getProveedores() {
        return proveedores;
    }

    public void setProveedores(Set<Proveedor> proveedores) {
        this.proveedores = proveedores;
    }

    public Set<VentaArticulo> getVentaArticulos() {
        return ventaArticulos;
    }

    public void setVentaArticulos(Set<VentaArticulo> ventaArticulos) {
        this.ventaArticulos = ventaArticulos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStock_min() {
        return stock_min;
    }

    public void setStock_min(int stock_min) {
        this.stock_min = stock_min;
    }

    @Override
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

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
}
