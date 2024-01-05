package ar.edu.unnoba.ssaaii.SistVentas.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "Formasdepago")
public class FormaDePago implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipos",nullable = false)
    private String tipo; //seguro lo cambio a Enum


    @OneToMany(mappedBy = "formaDePago", cascade = CascadeType.ALL)
    private Set<VentaFormaPago> ventaFormasPago;


    public FormaDePago() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Set<VentaFormaPago> getVentaFormasPago() {
        return ventaFormasPago;
    }

    public void setVentaFormasPago(Set<VentaFormaPago> ventaFormasPago) {
        this.ventaFormasPago = ventaFormasPago;
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

}
