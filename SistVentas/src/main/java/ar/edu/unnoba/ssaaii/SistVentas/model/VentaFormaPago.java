package ar.edu.unnoba.ssaaii.SistVentas.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity
@Table(name = "venta_formadepago")
public class VentaFormaPago implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "monto")
    private float monto;

    @ManyToOne
    @JoinColumn(name = "venta_id")
    private Venta venta;

    @Column(name = "cuotas")
    private int cuotas;

    @Column(name="recargo")
    private float recargo;

    @Column(name = "descuento")
    private float descuento;

    @Column(name = "cuotas_chk")
    private boolean cuotas_chk;

    @Column(name="recargo_chk")
    private boolean recargo_chk;

    @Column(name = "descuento_chk")
    private boolean descuento_chk;

    @ManyToOne
    @JoinColumn(name = "forma_pago_id")
    private FormaDePago formaDePago;

    public VentaFormaPago() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public FormaDePago getFormaDePago() {
        return formaDePago;
    }

    public void setFormaDePago(FormaDePago formaDePago) {
        this.formaDePago = formaDePago;
    }

    public int getCuotas() {
        return cuotas;
    }

    public void setCuotas(int cuotas) {
        this.cuotas = cuotas;
    }

    public float getRecargo() {
        return recargo;
    }

    public void setRecargo(float recargo) {
        this.recargo = recargo;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    public boolean isCuotas_chk() {
        return cuotas_chk;
    }

    public void setCuotas_chk(boolean cuotas_chk) {
        this.cuotas_chk = cuotas_chk;
    }

    public boolean isRecargo_chk() {
        return recargo_chk;
    }

    public void setRecargo_chk(boolean recargo_chk) {
        this.recargo_chk = recargo_chk;
    }

    public boolean isDescuento_chk() {
        return descuento_chk;
    }

    public void setDescuento_chk(boolean descuento_chk) {
        this.descuento_chk = descuento_chk;
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
