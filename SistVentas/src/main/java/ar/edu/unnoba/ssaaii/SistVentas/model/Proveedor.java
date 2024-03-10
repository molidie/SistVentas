package ar.edu.unnoba.ssaaii.SistVentas.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity
@Table(name = "proveedores")
public class Proveedor implements UserDetails{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_empresa")
    private String nombreEmpresa;

    @Column(name = "nombre_contacto")
    private String nombreContacto;

    @Column(name = "apellido_contacto")
    private String apellidoContacto;
    
    @Column(name = "telefono")
    private String telefono;

    @Column(name = "mail")
    private String mail;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "rubro")
    private String rubro;

    @Column(name = "comentarios")
    private String comentarios;

    @ManyToOne
    @JoinColumn(name = "articuloP_id")
    private Articulo articulo;

    public Proveedor() {
    }

    public Long getId() {
        return id;
    }


    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getNombreEmpresa() {
        return nombreEmpresa;
    }


    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }


    public String getNombreContacto() {
        return nombreContacto;
    }


    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }


    public String getApellidoContacto() {
        return apellidoContacto;
    }


    public void setApellidoContacto(String apellidoContacto) {
        this.apellidoContacto = apellidoContacto;
    }


    public String getMail() {
        return mail;
    }
    
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }



    public void setMail(String mail) {
        this.mail = mail;
    }


    public String getDireccion() {
        return direccion;
    }


    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


    public String getComentarios() {
        return comentarios;
    }


    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    
    public String getRubro() {
        return rubro;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
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
