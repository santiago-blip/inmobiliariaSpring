/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inmobiliaria.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_casa")
public class Casa implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_casa")
    private Long idCasa;
    
    @ManyToOne()
    @JoinColumn(name = "id_usuario")
    private Usuario idUsuario;
    
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "id_casa")
    private List<ImagenesCasa> imagenesCasa;
    
    @Column(name = "titulo_casa")
    private String titulo;
    
    @Column(name = "descripcion_casa")
    private String descripcion;
    
    @Column(name = "tipo")
    private String tipoInmueble;
    
    @Column(name = "ArriendoOVenta")
    private String arriendoVenta;
    
    private double precio;
    
    @Column(name = "ruta_img")
    private String rutaImg;
    
    public Casa(){
        this.imagenesCasa = new ArrayList<ImagenesCasa>();
    }

    public Long getIdCasa() {
        return idCasa;
    }

    public void setIdCasa(Long idCasa) {
        this.idCasa = idCasa;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<ImagenesCasa> getImagenesCasa() {
        return imagenesCasa;
    }

    public void setImagenesCasa(List<ImagenesCasa> imagenesCasa) {
        this.imagenesCasa = imagenesCasa;
    }

    public void addImagenesCasa(ImagenesCasa imagen){
        this.imagenesCasa.add(imagen);
    }
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoInmueble() {
        return tipoInmueble;
    }

    public void setTipoInmueble(String tipoInmueble) {
        this.tipoInmueble = tipoInmueble;
    }

    public String getArriendoVenta() {
        return arriendoVenta;
    }

    public void setArriendoVenta(String arriendoVenta) {
        this.arriendoVenta = arriendoVenta;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getRutaImg() {
        return rutaImg;
    }

    public void setRutaImg(String rutaImg) {
        this.rutaImg = rutaImg;
    }
    
    
}
